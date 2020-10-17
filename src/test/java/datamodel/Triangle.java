package datamodel;

import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Triangle {
    public static final String BASE_SEPARATOR = ";";
    @ToString.Exclude
    private String id;
    @NonNull
    private Double firstSide;
    @NonNull
    private Double secondSide;
    @NonNull
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
