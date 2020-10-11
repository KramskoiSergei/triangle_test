package triangle.negative;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import triangle.BaseTriangle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.equalTo;

@Execution(ExecutionMode.CONCURRENT)
public class IncorrectPostTriangleMethodsTest extends BaseTriangle {
    @Test
    void putTriangle_WithIncorrectMethod_ReturnError() {
        given()
                .spec(baseRequestSpec)
                .body("any body")
                .when()
                .put("/triangle")
                .then()
                .assertThat()
                .statusCode(405)
                .spec(correctResponseHeaders)
                .header("Allow", "POST")
                .body("error", equalTo("Method Not Allowed"));
    }

    @Test
    void patchTriangle_WithIncorrectMethod_ReturnError() {
        given()
                .spec(baseRequestSpec)
                .body("any body")
                .when()
                .patch("/triangle")
                .then()
                .assertThat()
                .statusCode(405)
                .spec(correctResponseHeaders)
                .header("Allow", "POST")
                .body("error", equalTo("Method Not Allowed"));
    }

    @Test
    void deleteTriangle_WithIncorrectMethod_ReturnError() {
        given()
                .spec(baseRequestSpec)
                .body("any body")
                .when()
                .delete("/triangle")
                .then()
                .assertThat()
                .statusCode(405)
                .spec(correctResponseHeaders)
                .header("Allow", "POST")
                .body("error", equalTo("Method Not Allowed"));
    }

    @Test
    void getTriangle_WithIncorrectMethod_ReturnError() {
        given()
                .spec(baseRequestSpec)
                .body("any body")
                .when()
                .get("/triangle")
                .then()
                .assertThat()
                .statusCode(405)
                .spec(correctResponseHeaders)
                .header("Allow", "POST")
                .body("error", equalTo("Method Not Allowed"));
    }

    @Test
    void headTriangle_WithIncorrectMethod_ReturnError() {
        given()
                .spec(baseRequestSpec)
                .body("any body")
                .when()
                .head("/triangle")
                .then()
                .assertThat()
                .statusCode(405)
                .spec(correctResponseHeaders)
                .header("Allow", "POST");
    }

    @Test
    void optionsTriangle_WithIncorrectMethod_ReturnCorrectOptionsResponse() {
        given()
                .spec(baseRequestSpec)
                .body("any body")
                .when()
                .options("/triangle")
                .then()
                .assertThat()
                .statusCode(200)
                .spec(correctResponseHeaders)
                .header("Allow", "POST");
    }

}
