package triangle;

import datamodel.Triangle;
import io.restassured.RestAssured;
import io.restassured.config.JsonConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.path.json.config.JsonPathConfig;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.junit.jupiter.api.BeforeEach;
import utils.TestUtils;

import java.util.List;

import static datamodel.Triangle.BASE_SEPARATOR;
import static io.restassured.RestAssured.expect;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.anything;

public class BaseTriangle {
    static {
        JsonConfig jsonNumbersConfig = new JsonConfig(JsonPathConfig.NumberReturnType.BIG_DECIMAL);
        RestAssured.config = new RestAssuredConfig().set().jsonConfig(jsonNumbersConfig);
    }

    public RequestSpecification baseRequestSpec = given()
            .baseUri(TestUtils.properties.getProperty("base_url"))
            .header("X-User", TestUtils.properties.getProperty("user_id"))
            .header("Content-Type", "application/json;charset=UTF-8");

    public ResponseSpecification correctResponseHeaders = expect()
            .header("Content-Type", "application/json;charset=UTF-8")
            .header("Transfer-Encoding", "chunked")
            .header("Connection", "keep-alive")
            .header("Date", anything());

    @BeforeEach
    void clearState() {
        removeAllTriangles();
    }

    public void removeAllTriangles() {

        List<Triangle> triangleForRemove = getAllTriangles();
        triangleForRemove.forEach(this::deleteTriangle);
    }

    public Triangle postTriangle(Triangle triangle) {
        return postTriangle(triangle.createTriangleRequest(BASE_SEPARATOR));
    }

    public Triangle postTriangle(Triangle triangle, String separator) {
        return postTriangle(triangle.createTriangleRequest(separator));
    }

    public Triangle postTriangle(String triangleBody) {
        return given()
                .spec(baseRequestSpec)
                .body(triangleBody)
                .when()
                .post("/triangle")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .extract()
                .as(Triangle.class);
    }

    public Triangle getTriangle(String triangleId) {
        return given()
                .spec(baseRequestSpec)
                .when()
                .get("/triangle/" + triangleId)
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .extract()
                .as(Triangle.class);
    }

    @SuppressWarnings(value = "unchecked")
    public List<Triangle> getAllTriangles() {
        return given()
                .spec(baseRequestSpec)
                .when()
                .get("/triangle/all")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .extract()
                .body()
                .jsonPath()
                .getList(".", Triangle.class);
    }

    public void deleteTriangle(Triangle triangle) {
        deleteTriangle(triangle.getId());
    }

    public void deleteTriangle(String triangleId) {
        given()
                .spec(baseRequestSpec)
                .when()
                .delete("/triangle/" + triangleId)
                .then()
                .assertThat()
                .statusCode(200)
                .header("Content-Length", "0")
                .header("Connection", "keep-alive")
                .header("Date", anything());
    }

    public Double getTrianglePerimeter(Triangle triangle) {
        return getTrianglePerimeter(triangle.getId());
    }

    public Double getTrianglePerimeter(String triangleId) {
        return given()
                .spec(baseRequestSpec)
                .when()
                .get("/triangle/" + triangleId + "/perimeter")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .extract()
                .jsonPath()
                .getDouble("result");
    }

    public Double getTriangleArea(Triangle triangle) {
        return getTriangleArea(triangle.getId());
    }

    public Double getTriangleArea(String triangleId) {
        return given()
                .spec(baseRequestSpec)
                .when()
                .get("/triangle/" + triangleId + "/area")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .extract()
                .jsonPath()
                .getDouble("result");
    }
}
