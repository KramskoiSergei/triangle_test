package triangle.positive;

import datamodel.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import triangle.BaseTriangle;

//DO NOT PARALLEL THIS TESTS
public class PostTriangleTest extends BaseTriangle {
    @BeforeEach
    void clearState() {
        removeAllTriangles();
    }

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
}