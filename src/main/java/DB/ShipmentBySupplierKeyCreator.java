package DB;

import Models.ShipmentKey;
import Models.SupplierKey;
import com.sleepycat.bind.serial.ClassCatalog;
import com.sleepycat.bind.serial.SerialSerialKeyCreator;

/**
 * @author Amit Chawla
 **/
public class ShipmentBySupplierKeyCreator extends SerialSerialKeyCreator {
    public ShipmentBySupplierKeyCreator(ClassCatalog catalog,
                                        Class primaryKeyClass,
                                        Class valueClass,
                                        Class indexKeyClass) {
        super(catalog, primaryKeyClass, valueClass, indexKeyClass);
    }

    public Object createSecondaryKey(Object primaryKeyInput,
                                     Object valueInput) {
        ShipmentKey shipmentKey = (ShipmentKey) primaryKeyInput;
        return new SupplierKey(shipmentKey.getSupplierNumber());
    }
}
