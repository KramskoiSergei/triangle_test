package triangle.positive;

import datamodel.Triangle;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import triangle.BaseTriangle;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class SmokePositiveTest extends BaseTriangle {

    @Test
    void postTriangle_WithCorrectData_ReturnCorrectTriangleResponse() {
        Triangle expectedTriangle = new Triangle(3.0, 4.0, 5.0);
        Triangle actualTriangle = postTriangle(expectedTriangle);

        Assertions.assertAll(
                () -> Assertions.assertEquals(expectedTriangle.getFirstSide(), actualTriangle.getFirstSide(), "Firs side incorrect"),
                () -> Assertions.assertEquals(expectedTriangle.getSecondSide(), actualTriangle.getSecondSide(), "Firs side incorrect"),
                () -> Assertions.assertEquals(expectedTriangle.getThirdSide(), actualTriangle.getThirdSide(), "Firs side incorrect")
        );
    }

    @Test
    void getTriangle_withCorrectData_WillReturnTriangle() {
        Triangle expectedTriangle = new Triangle(3.0, 4.0, 5.0);
        expectedTriangle = postTriangle(expectedTriangle);

        Triangle actualTriangle = getTriangle(expectedTriangle.getId());

        Assertions.assertEquals(expectedTriangle, actualTriangle, "Triangles are not the same");
    }

    @Test
    void getAll_withCorrectData_WillReturnAllTriangles() {
        Triangle triangleOne = new Triangle(3.0, 4.0, 5.0);
        triangleOne = postTriangle(triangleOne);
        Triangle triangleAnother = new Triangle(3.0, 4.0, 5.0);
        triangleAnother = postTriangle(triangleAnother);

        List<Triangle> actualTriangleList = getAllTriangles();

        assertThat(actualTriangleList).withFailMessage("getAll method return incorrect data")
                .containsOnly(triangleOne, triangleAnother);
    }

    @Test
    void deleteTriangle_withCorrectData_WillRemoveTriangle() {
        Triangle triangleToDelete = new Triangle(3.0, 4.0, 5.0);
        triangleToDelete = postTriangle(triangleToDelete);

        deleteTriangle(triangleToDelete);

        Assertions.assertTrue(getAllTriangles().isEmpty(), "Find Triangle, but it was deleted");
    }

    @Test
    void getPerimeter_withCorrectData_WillReturnPerimeter() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        triangle = postTriangle(triangle);
        Double expectedPerimeter = triangle.getPerimeter();

        Double actualPerimeter = getTrianglePerimeter(triangle);

        Assertions.assertEquals(expectedPerimeter, actualPerimeter, "Perimeters are not equals");
    }

    @Test
    void getPerimeter_withCorrectData_WillReturnArea() {
        Triangle triangle = new Triangle(3.0, 4.0, 5.0);
        triangle = postTriangle(triangle);
        Double expectedArea = triangle.getArea();

        Double actualArea = getTriangleArea(triangle);

        Assertions.assertEquals(expectedArea, actualArea, "Perimeters are not equals");
    }
}
