package triangle.positive;

import datamodel.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import triangle.BaseTriangle;

import static io.restassured.RestAssured.given;

//DO NOT PARALLEL THIS TESTS
public class GetPerimeterTest extends BaseTriangle {
    @BeforeEach
    void clearState() {
        removeAllTriangles();
    }

    @Test
    void getPerimeter_WithDoubleOverflow_ReturnCorrectResultOrError() {
        Triangle triangle = new Triangle(Double.MAX_VALUE, Double.MAX_VALUE, Double.MAX_VALUE);
        triangle = postTriangle(triangle);

        Double actualPerimeter = given()
                .spec(baseRequestSpec)
                .when()
                .get("/triangle/" + triangle.getId() + "/perimeter")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .extract()
                .jsonPath()
                .getDouble("result");

        Assertions.assertNotEquals(Double.POSITIVE_INFINITY, actualPerimeter, "Number overflow is not handle");
    }

    @Test
    void getPerimeter_WithFloatPointValues_ReturnCorrectResult() {
        Triangle triangle = new Triangle(2.99, 3.1, 4.19);
        triangle = postTriangle(triangle);

        Double actualPerimeter = given()
                .spec(baseRequestSpec)
                .when()
                .get("/triangle/" + triangle.getId() + "/perimeter")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .extract()
                .jsonPath()
                .getDouble("result");

        Assertions.assertEquals(10.289, actualPerimeter, "Rounding accuracy errors in floating-point numbers");
    }
}
