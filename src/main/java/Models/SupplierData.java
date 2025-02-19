package Models;

import java.io.Serializable;

/**
 * @author Amit Chawla
 **/
public class SupplierData implements Serializable {
    private String name;
    private int status;
    private String city;

    public SupplierData(String name, int status, String city) {
        this.name = name;
        this.status = status;
        this.city = city;
    }

    public final String getName() {
        return name;
    }

    public final int getStatus() {
        return status;
    }

    public final String getCity() {
        return city;
    }

    public String toString() {
        return "[Models.SupplierData: name=" + name +
                " status=" + status +
                " city=" + city + ']';
    }
}
