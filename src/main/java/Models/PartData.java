package Models;

import java.io.Serializable;

/**
 * @author Amit Chawla
 **/
public class PartData implements Serializable {
    private String name;
    private String color;
    private Weight weight;
    private String city;

    public PartData(String name, String color, Weight weight, String city) {
        this.name = name;
        this.color = color;
        this.weight = weight;
        this.city = city;
    }

    public final String getName() {
        return name;
    }

    public final String getColor() {
        return color;
    }

    public final Weight getWeight() {
        return weight;
    }

    public final String getCity() {
        return city;
    }

    public String toString() {
        return "[Models.PartData: name=" + name +
                " color=" + color +
                " weight=" + weight +
                " city=" + city + ']';
    }
}
