package triangle.positive;

import datamodel.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import triangle.BaseTriangle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

class PostTriangleTest extends BaseTriangle {
    @ValueSource(strings = {
            "-",
            "}",
            "{",
            "(",
            ")",
            "'",
            "%",
            "/",
            "\\",
            "\"",
    })
    @ParameterizedTest
    void postTriangle_withNotDefaultSeparatorData_WillReturnTriangle() {
        Triangle expectedTriangle = new Triangle(3.0, 4.0, 5.0);
        expectedTriangle = postTriangle(expectedTriangle, "-");

        Triangle actualTriangle = getTriangle(expectedTriangle.getId());

        Assertions.assertEquals(expectedTriangle, actualTriangle, "Triangles are not the same");
    }

    @Test
    void postTriangle_limitExceeded_WillReturnError() {
        Triangle trinagle = new Triangle(3.0, 4.0, 5.0);
        int limit = 11;
        for (int i = 0; i < limit; i ++) {
            postTriangle(trinagle);
        }

        given()
                .spec(baseRequestSpec)
                .body(trinagle)
                .when()
                .post("/triangle")
                .then()
                .assertThat()
                .statusCode(422)
                .spec(correctResponseHeaders)
                .body("error", equalTo("Unprocessable Entity"))
                .body("exception", containsString("LimitExceededException"))
                .body("message", containsString("Limit exceeded"));
    }
}
