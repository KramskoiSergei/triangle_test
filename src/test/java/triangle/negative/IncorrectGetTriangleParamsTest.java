package triangle.negative;

import datamodel.Triangle;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import triangle.BaseTriangle;
import utils.TestUtils;

import static io.restassured.RestAssured.given;

@Execution(ExecutionMode.CONCURRENT)
public class IncorrectGetTriangleParamsTest extends BaseTriangle {
    @Test
    void getTriangle_WithIncorrectId_Return404() {
        given()
                .spec(baseRequestSpec)
                .when()
                .get("/triangle/incorrect_triangle_Id")
                .then()
                .assertThat()
                .statusCode(404)
                .spec(correctResponseHeaders);
    }

    @Test
    void getTriangle_WithIncorrectXUserHeader_Return401() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        triangle = postTriangle(triangle, "-");

        given()
                .baseUri(TestUtils.properties.getProperty("base_url"))
                .header("X-User", "incorrect-x-user-header")
                .header("Content-Type", "application/json;charset=UTF-8")
                .when()
                .get("/triangle/" + triangle.getId())
                .then()
                .assertThat()
                .statusCode(401)
                .spec(correctResponseHeaders);
    }
}
