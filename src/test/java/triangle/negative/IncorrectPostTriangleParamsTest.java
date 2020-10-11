package triangle.negative;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import triangle.BaseTriangle;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;

public class IncorrectPostTriangleParamsTest extends BaseTriangle {
    @ValueSource(strings = {
            "{\n" +
                    "\"separator\": \":\", \n" +
                    "\"input\": \"1;2;3\"\n" +
                    "}",
            "{\n" +
                    "\"separator\": \";\", \n" +
                    "\"input\": \"a;2;3\"\n" +
                    "}",
            "{\n" +
                    "\"separator\": \";\", \n" +
                    "\"input\": \"1;2;c\"\n" +
                    "}",
            "{\n" +
                    "\"separator\": \";\", \n" +
                    "\"input\": \"1;2;3;4\"\n" +
                    "}",
            "{\n" +
                    "\"separator\": \";\", \n" +
                    "\"input\": \"1;2;-3\"\n" +
                    "}",
            "{\n" +
                    "\"input\": \"1;2;3\"\n" +
                    "}",
            "{\n" +
                    "\"separator\": \";\" \n" +
                    "}",

    })
    @ParameterizedTest
    void postTriangle_WithIncorrectBodyFormat_ReturnError(String incorrectBody) {
        given()
                .spec(baseRequestSpec)
                .body(incorrectBody)
                .when()
                .post("/triangle")
                .then()
                .assertThat()
                .statusCode(422)
                .spec(correctResponseHeaders)
                .body("error", equalTo("Unprocessable Entity"))
                .body("exception", containsString("UnprocessableDataException"))
                .body("message", equalTo("Cannot process input"));
    }

    @ValueSource(strings = {
            "{\n" +
                    "\"separator\": \";\", \n" +
                    "\"input\": \"0;0;0\"\n" +
                    "}",
            "{\n" +
                    "\"separator\": \";\", \n" +
                    "\"input\": \"1;2;100\"\n" +
                    "}",
            "{\n" +
                    "\"separator\": \";\", \n" +
                    "\"input\": \"0.1;3.1;3.2\"\n" +
                    "}",
    })
    @ParameterizedTest
    void postTriangle_WithIncorrectTriangleParams_ReturnError(String bodyWithIncorrectTriangleParams) {
        given()
                .spec(baseRequestSpec)
                .body(bodyWithIncorrectTriangleParams)
                .when()
                .post("/triangle")
                .then()
                .assertThat()
                .statusCode(422)
                .spec(correctResponseHeaders)
                .body("error", equalTo("Unprocessable Entity"))
                .body("exception", containsString("UnprocessableDataException"))
                .body("message", equalTo("Cannot process input"));
    }

    @Test
    void postTriangle_WithEmptyBody_ReturnError() {
        given()
                .spec(baseRequestSpec)
                .when()
                .post("/triangle")
                .then()
                .assertThat()
                .statusCode(400)
                .spec(correctResponseHeaders)
                .body("error", equalTo("Bad Request"))
                .body("exception", containsString("HttpMessageNotReadableException"))
                .body("message", equalTo("Required request body is missing"));;
    }

    @Test
    void postTriangle_WithIncorrectBody_ReturnError() {
        given()
                .spec(baseRequestSpec)
                .body("incorrect body")
                .when()
                .post("/triangle")
                .then()
                .assertThat()
                .statusCode(400)
                .spec(correctResponseHeaders)
                .body("error", equalTo("Bad Request"))
                .body("exception", containsString("HttpMessageNotReadableException"))
                .body("message", containsString("Could not read document"));;
    }
}
