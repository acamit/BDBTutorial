package Models;

import java.io.Serializable;

/**
 * @author Amit Chawla
 **/
public class SupplierKey implements Serializable {
    private String number;

    public SupplierKey(String number) {
        this.number = number;
    }

    public final String getNumber() {
        return number;
    }

    public String toString() {
        return "[Models.SupplierKey: number=" + number + ']';
    }
}
