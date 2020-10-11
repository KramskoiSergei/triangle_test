package datamodel;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public class Triangle {
    public static final String BASE_SEPARATOR = ";";
    private String id;
    private Double firstSide;
    private Double secondSide;
    private Double thirdSide;

    public Triangle() {
        //using for deserialization do not remove
    }

    public Triangle(Double firstSide, Double secondSide, Double thirdSide) {
        this.firstSide = firstSide;
        this.secondSide = secondSide;
        this.thirdSide = thirdSide;
    }

    public Double getPerimeter() {
        return firstSide + secondSide + thirdSide;
    }

    public Double getArea() {
        Double halfPerimeter = getPerimeter() / 2;
        return Math.sqrt(halfPerimeter * (halfPerimeter - firstSide) * (halfPerimeter - secondSide) * (halfPerimeter - thirdSide));
    }

    public String createTriangleRequest(String separator) {
        return "{\n" +
                "\"separator\": \"" + separator + "\", \n" +
                "\"input\": \"" + firstSide + separator + secondSide + separator + thirdSide + "\"\n" +
                "}";
    }
}
