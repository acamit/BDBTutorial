package Models;

import java.io.Serializable;

/**
 * @author Amit Chawla
 **/
public class ShipmentKey implements Serializable {
    private String partNumber;
    private String supplierNumber;

    public ShipmentKey(String partNumber, String supplierNumber) {
        this.partNumber = partNumber;
        this.supplierNumber = supplierNumber;
    }

    public final String getPartNumber() {
        return partNumber;
    }

    public final String getSupplierNumber() {
        return supplierNumber;
    }

    public String toString() {
        return "[Models.ShipmentKey: supplier=" + supplierNumber +
                " part=" + partNumber + ']';
    }
}
