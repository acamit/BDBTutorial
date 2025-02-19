package DB;

import Models.PartData;
import Models.PartKey;
import Models.ShipmentData;
import Models.ShipmentKey;
import Models.SupplierData;
import Models.SupplierKey;
import com.sleepycat.bind.EntryBinding;
import com.sleepycat.bind.serial.ClassCatalog;
import com.sleepycat.bind.serial.SerialBinding;
import com.sleepycat.collections.StoredEntrySet;
import com.sleepycat.collections.StoredMap;

/**
 * @author Amit Chawla
 **/
public class SampleViews {
    private StoredMap partMap;
    private StoredMap supplierMap;
    private StoredMap shipmentMap;

    public SampleViews(SampleDatabase db) {
        ClassCatalog catalog = db.getClassCatalog();
        EntryBinding partKeyBinding = new SerialBinding(catalog, PartKey.class);
        EntryBinding partValueBinding = new SerialBinding(catalog, PartData.class);
        EntryBinding shipmentKeyBinding = new SerialBinding(catalog, ShipmentKey.class);
        EntryBinding shipmentValueBinding = new SerialBinding(catalog, ShipmentData.class);
        EntryBinding supplierKeyBinding = new SerialBinding(catalog, SupplierKey.class);
        EntryBinding supplierValueBinding = new SerialBinding(catalog, SupplierData.class);

        partMap = new StoredMap(db.getPartDb(), partKeyBinding, partValueBinding, true);
        supplierMap = new StoredMap(db.getSupplierDb(), supplierKeyBinding, supplierValueBinding, true);
        shipmentMap = new StoredMap(db.getShipmentDb(), shipmentKeyBinding, shipmentValueBinding, true);
    }

    public final StoredMap getPartMap() {
        return partMap;
    }

    public final StoredMap getSupplierMap() {
        return supplierMap;
    }

    public final StoredMap getShipmentMap() {
        return shipmentMap;
    }

    public final StoredEntrySet getPartEntrySet() {
        return (StoredEntrySet) partMap.entrySet();
    }

    public final StoredEntrySet getSupplierEntrySet() {
        return (StoredEntrySet) supplierMap.entrySet();
    }

    public final StoredEntrySet getShipmentEntrySet() {
        return (StoredEntrySet) shipmentMap.entrySet();
    }
}
