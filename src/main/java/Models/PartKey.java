package Models;

import java.io.Serializable;

/**
 * @author Amit Chawla
 **/
public class PartKey implements Serializable {
    private String number;
    public PartKey(String number) {
        this.number = number;
    }
    public String getNumber() {
        return number;
    }

    @Override
    public String toString() {
        return "[Models.PartKey: number = " + number + "]";
    }
}
