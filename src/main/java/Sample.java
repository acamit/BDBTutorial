import DB.SampleDatabase;
import DB.SampleViews;
import Models.PartData;
import Models.PartKey;
import Models.ShipmentData;
import Models.ShipmentKey;
import Models.SupplierData;
import Models.SupplierKey;
import Models.Weight;
import com.sleepycat.collections.StoredIterator;
import com.sleepycat.collections.TransactionRunner;
import com.sleepycat.collections.TransactionWorker;
import com.sleepycat.je.DatabaseException;

import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;

/**
 * @author Amit Chawla
 **/
public class Sample {
    private SampleViews views;
    private SampleDatabase db;

    public Sample(String homeDir) throws DatabaseException, FileNotFoundException {
        db = new SampleDatabase(homeDir);
        views = new SampleViews(db);
    }

    public static void main(String[] args) {

        String homeDir = "/Users/amitchawla/Code/Repos/BDBTutorial/src/main/data";
        Sample sample = null;
        try {
            sample = new Sample(homeDir);
            sample.run();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (sample != null) {
                try {
                    sample.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private void close() throws DatabaseException {
        db.close();
    }

    private void run() throws Exception {
        TransactionRunner runner = new TransactionRunner(db.getEnvironment());
        runner.run(new PopulateDatabase());
        runner.run(new PrintDatabase());
    }

    private class PopulateDatabase implements TransactionWorker {
        public void doWork()
                throws Exception {
            addSuppliers();
            addParts();
            addShipments();
        }

        private void addSuppliers() {
            Map suppliers = views.getSupplierMap();
            if (suppliers.isEmpty()) {
                System.out.println("Adding Suppliers");
                suppliers.put(new SupplierKey("S1"),
                        new SupplierData("Smith", 20, "London"));
                suppliers.put(new SupplierKey("S2"),
                        new SupplierData("Jones", 10, "Paris"));
                suppliers.put(new SupplierKey("S3"),
                        new SupplierData("Blake", 30, "Paris"));
                suppliers.put(new SupplierKey("S4"),
                        new SupplierData("Clark", 20, "London"));
                suppliers.put(new SupplierKey("S5"),
                        new SupplierData("Adams", 30, "Athens"));
            }
        }

        private void addParts() {
            Map parts = views.getPartMap();
            if (parts.isEmpty()) {
                System.out.println("Adding Parts");
                parts.put(new PartKey("P1"),
                        new PartData("Nut", "Red",
                                new Weight(12.0, Weight.GRAMS),
                                "London"));
                parts.put(new PartKey("P2"),
                        new PartData("Bolt", "Green",
                                new Weight(17.0, Weight.GRAMS),
                                "Paris"));
                parts.put(new PartKey("P3"),
                        new PartData("Screw", "Blue",
                                new Weight(17.0, Weight.GRAMS),
                                "Rome"));
                parts.put(new PartKey("P4"),
                        new PartData("Screw", "Red",
                                new Weight(14.0, Weight.GRAMS),
                                "London"));
                parts.put(new PartKey("P5"),
                        new PartData("Cam", "Blue",
                                new Weight(12.0, Weight.GRAMS),
                                "Paris"));
                parts.put(new PartKey("P6"),
                        new PartData("Cog", "Red",
                                new Weight(19.0, Weight.GRAMS),
                                "London"));
            }
        }

        private void addShipments() {
            Map shipments = views.getShipmentMap();
            if (shipments.isEmpty())
            {
                System.out.println("Adding Shipments");
                shipments.put(new ShipmentKey("P1", "S1"),
                        new ShipmentData(300));
                shipments.put(new ShipmentKey("P2", "S1"),
                        new ShipmentData(200));
                shipments.put(new ShipmentKey("P3", "S1"),
                        new ShipmentData(400));
                shipments.put(new ShipmentKey("P4", "S1"),
                        new ShipmentData(200));
                shipments.put(new ShipmentKey("P5", "S1"),
                        new ShipmentData(100));
                shipments.put(new ShipmentKey("P6", "S1"),
                        new ShipmentData(100));
                shipments.put(new ShipmentKey("P1", "S2"),
                        new ShipmentData(300));
                shipments.put(new ShipmentKey("P2", "S2"),
                        new ShipmentData(400));
                shipments.put(new ShipmentKey("P2", "S3"),
                        new ShipmentData(200));
                shipments.put(new ShipmentKey("P2", "S4"),
                        new ShipmentData(200));
                shipments.put(new ShipmentKey("P4", "S4"),
                        new ShipmentData(300));
                shipments.put(new ShipmentKey("P5", "S4"),
                        new ShipmentData(400));
            }
        }
    }

    private class PrintDatabase implements TransactionWorker {
        public void doWork()
                throws Exception {
            printEntries("Parts", views.getPartEntrySet().iterator());
            printEntries("Shipments", views.getShipmentEntrySet().iterator());
            printEntries("Suppliers", views.getSupplierEntrySet().iterator());

        }
        private void printEntries(String label, Iterator iterator){
            try{
                while(iterator.hasNext()){
                    Map.Entry entry = (Map.Entry)iterator.next();
                    System.out.println(entry.getKey() + ": " + entry.getValue());

                }
            }finally{
                StoredIterator.close(iterator);
            }
        }
    }
}
