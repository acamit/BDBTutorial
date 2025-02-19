package DB;

import Models.PartKey;
import Models.ShipmentKey;
import com.sleepycat.bind.serial.ClassCatalog;
import com.sleepycat.bind.serial.SerialSerialKeyCreator;

/**
 * @author Amit Chawla
 **/
public class ShipmentByPartKeyCreator
        extends SerialSerialKeyCreator {
    public ShipmentByPartKeyCreator(ClassCatalog catalog,
                                    Class primaryKeyClass,
                                    Class valueClass,
                                    Class indexKeyClass) {
        super(catalog, primaryKeyClass, valueClass, indexKeyClass);
    }

    public Object createSecondaryKey(Object primaryKeyInput,
                                     Object valueInput) {
        ShipmentKey shipmentKey = (ShipmentKey) primaryKeyInput;
        return new PartKey(shipmentKey.getPartNumber());
    }
}
