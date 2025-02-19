package DB;

import Models.SupplierData;
import Models.SupplierKey;
import com.sleepycat.bind.serial.SerialSerialKeyCreator;
import com.sleepycat.bind.serial.StoredClassCatalog;

/**
 * @author Amit Chawla
 **/
public class SupplierByCityKeyCreateor extends SerialSerialKeyCreator {
    public SupplierByCityKeyCreateor(StoredClassCatalog javaCatalog, Class supplierKeyClass, Class supplierDataClass, Class stringClass) {
        super(javaCatalog, supplierKeyClass, supplierDataClass, stringClass);
    }

    @Override
    public Object createSecondaryKey(Object primaryKeyInput, Object valueInput) {
        SupplierData supplierData = (SupplierData) valueInput;
        return supplierData.getCity();
    }
}
