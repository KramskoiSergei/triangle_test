package triangle.negative;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import triangle.BaseTriangle;

public class IncorrectDeleteTriangleParamsTest extends BaseTriangle {
    @Test
    void deleteTriangle_WithIncorrectTriangleId_WillReturn200() {
        Assertions.assertDoesNotThrow(() -> deleteTriangle("incorrectTriangleId"),
                "delete method is not idempotent");
    }
}
