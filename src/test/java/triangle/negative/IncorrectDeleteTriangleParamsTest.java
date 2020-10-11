package triangle.negative;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.parallel.Execution;
import org.junit.jupiter.api.parallel.ExecutionMode;
import triangle.BaseTriangle;

@Execution(ExecutionMode.CONCURRENT)
public class IncorrectDeleteTriangleParamsTest extends BaseTriangle {
    @Test
    void deleteTriangle_WithIncorrectTriangleId_WillReturn200() {
        Assertions.assertDoesNotThrow(() -> deleteTriangle("incorrectTriangleId"),
                "delete method is not idempotent");
    }
}
