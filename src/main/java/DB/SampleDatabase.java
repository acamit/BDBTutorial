package DB;

import Models.SupplierData;
import Models.SupplierKey;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.SecondaryConfig;
import com.sleepycat.je.SecondaryDatabase;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Amit Chawla
 **/
public class SampleDatabase {

    private static final String CLASS_CATALOG = "java_class_catalog";
    private static final String SUPPLIER_STORE = "supplier_store";
    private static final String PART_STORE = "part_store";
    private static final String SHIPMENT_STORE = "shipment_store";
    private static final String SUPLIER_CITY_INDEX = "supplier_city_index";


    private Environment env;
    private StoredClassCatalog javaCatalog;
    private Database supplierDb;
    private Database partDb;
    private Database shipmentDb;

    private SecondaryDatabase supplierByCityDb;

    public SampleDatabase(String homeDirectory) throws DatabaseException, FileNotFoundException {
        System.out.println("Opening Environment in: " + homeDirectory);

        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        envConfig.setTransactional(true);
        env = new Environment(new File(homeDirectory), envConfig);

        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        dbConfig.setTransactional(true);
        Database catalogDb = env.openDatabase(null, CLASS_CATALOG, dbConfig);
        javaCatalog = new StoredClassCatalog(catalogDb);

        partDb = env.openDatabase(null, PART_STORE, dbConfig);
        shipmentDb = env.openDatabase(null, SHIPMENT_STORE, dbConfig);
        supplierDb = env.openDatabase(null, SUPPLIER_STORE, dbConfig);

        SecondaryConfig secConfig = new SecondaryConfig();
        secConfig.setAllowCreate(true);
        secConfig.setTransactional(true);
        secConfig.setSortedDuplicates(true);

        secConfig.setKeyCreator(new SupplierByCityKeyCreateor(javaCatalog, SupplierKey.class, SupplierData.class, String.class));
    }

    public void close() throws DatabaseException {
        partDb.close();
        shipmentDb.close();
        supplierDb.close();
        javaCatalog.close();
        env.close();
    }

    public final StoredClassCatalog getClassCatalog() throws DatabaseException {
        return javaCatalog;
    }

    public final Environment getEnv() {
        return env;
    }

    private final Database getPartDb() {
        return partDb;
    }

    private final Database getShipmentDb() {
        return shipmentDb;
    }

    private final Database getSupplierDb() {
        return supplierDb;
    }
}
