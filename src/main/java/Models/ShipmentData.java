package Models;

import java.io.Serializable;

/**
 * @author Amit Chawla
 **/
public class ShipmentData implements Serializable {
    private int quantity;

    public ShipmentData(int quantity) {
        this.quantity = quantity;
    }

    public final int getQuantity() {
        return quantity;
    }

    public String toString() {
        return "[Models.ShipmentData: quantity=" + quantity + ']';
    }
}
