package DB;

import Models.PartKey;
import Models.ShipmentData;
import Models.ShipmentKey;
import Models.SupplierData;
import Models.SupplierKey;
import com.sleepycat.bind.serial.ClassCatalog;
import com.sleepycat.bind.serial.StoredClassCatalog;
import com.sleepycat.je.Database;
import com.sleepycat.je.DatabaseConfig;
import com.sleepycat.je.DatabaseException;
import com.sleepycat.je.Environment;
import com.sleepycat.je.EnvironmentConfig;
import com.sleepycat.je.ForeignKeyDeleteAction;
import com.sleepycat.je.SecondaryConfig;
import com.sleepycat.je.SecondaryDatabase;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * @author Amit Chawla
 * Environm
 **/

public class SampleDatabase {
    public static final String CLASS_CATALOG = "java_class_catalog";
    private static final String SUPPLIER_STORE = "supplier_store";
    private static final String PART_STORE = "part_store";
    private static final String SHIPMENT_STORE = "shipment_store";
    private static final String SUPPLIER_CITY_INDEX = "supplier_city_index";
    private static final String SHIPMENT_PART_INDEX = "shipment_part_index";
    private static final String SHIPMENT_SUPPLIER_INDEX = "shipment_supplier_index";

    private Environment env;
    private StoredClassCatalog javaCatalog;
    private Database supplierDb;
    private Database partDb;
    private Database shipmentDb;
    private SecondaryDatabase supplierbyCityDb;
    private SecondaryDatabase shipmentByPartDb;
    private SecondaryDatabase shipmentBySupplierDb;

    //The home directory is the location of the environment's log files that store all database information.
    public SampleDatabase(String homeDirectory) throws DatabaseException, FileNotFoundException {
        System.out.println(homeDirectory);
        EnvironmentConfig envConfig = new EnvironmentConfig();
        envConfig.setAllowCreate(true);
        envConfig.setTransactional(true);
        env = new Environment(new File(homeDirectory), envConfig);

        DatabaseConfig dbConfig = new DatabaseConfig();
        dbConfig.setAllowCreate(true);
        dbConfig.setTransactional(true);
        Database catalogDb = env.openDatabase(null, CLASS_CATALOG, dbConfig);

        supplierDb = env.openDatabase(null, SUPPLIER_STORE, dbConfig);
        partDb = env.openDatabase(null, PART_STORE, dbConfig);
        shipmentDb = env.openDatabase(null, SHIPMENT_STORE, dbConfig);

        javaCatalog = new StoredClassCatalog(catalogDb);

        SecondaryConfig secConfig = new SecondaryConfig();
        secConfig.setAllowCreate(true);
        secConfig.setTransactional(true);
        secConfig.setSortedDuplicates(true);

        secConfig.setForeignKeyDatabase(partDb);
        secConfig.setForeignKeyDeleteAction(ForeignKeyDeleteAction.CASCADE);
        secConfig.setKeyCreator(new ShipmentByPartKeyCreator(javaCatalog,
                ShipmentKey.class,
                ShipmentData.class,
                PartKey.class));

        shipmentByPartDb = env.openSecondaryDatabase(null,
                SHIPMENT_PART_INDEX,
                shipmentDb,
                secConfig);

        secConfig.setForeignKeyDatabase(supplierDb);
        secConfig.setForeignKeyDeleteAction(ForeignKeyDeleteAction.CASCADE);
        secConfig.setKeyCreator(
                new ShipmentBySupplierKeyCreator(javaCatalog,
                        ShipmentKey.class,
                        ShipmentData.class,
                        SupplierKey.class));

        secConfig.setKeyCreator(new SupplierByCityKeyCreateor(javaCatalog, SupplierKey.class, SupplierData.class, String.class));

        supplierbyCityDb = env.openSecondaryDatabase(null, SUPPLIER_CITY_INDEX, supplierDb, secConfig);
    }

    public void close() throws DatabaseException {
        supplierbyCityDb.close();
        shipmentByPartDb.close();
        shipmentBySupplierDb.close();
        partDb.close();
        supplierDb.close();
        shipmentDb.close();
        javaCatalog.close();
        env.close();
    }

    public final ClassCatalog getClassCatalog() throws DatabaseException {
        return javaCatalog;
    }

    public final Database getSupplierDb() {
        return supplierDb;
    }

    public final Database getPartDb() {
        return partDb;
    }

    public final Database getShipmentDb() {
        return shipmentDb;
    }

    public final Environment getEnvironment() {
        return env;
    }

    public final SecondaryDatabase getSupplierbyCityDb() {
        return supplierbyCityDb;
    }
    public final SecondaryDatabase getShipmentByPartDatabase()
    {
        return shipmentByPartDb;
    }

    public final SecondaryDatabase getShipmentBySupplierDatabase()
    {
        return shipmentBySupplierDb;
    }
}
