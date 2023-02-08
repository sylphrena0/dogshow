package demo_database;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.HashMap;

public class Database implements Serializable{

    private static final long serialVersionUID = 503950840742716129L;
    private static String separator = System.getProperty("file.separator");
    private static String path = System.getProperty("user.dir") + separator + "db" + separator;
    private static String os = System.getProperty("os.name");

    private HashMap<String, Dog> contestDB = new HashMap<>();

    public void put(String name, Dog contestant) {
        contestDB.put(name, contestant);
    }

    public Dog get(String name) {
        return contestDB.get(name);
    }

    public void commitDB(String fileName) {

        FileOutputStream fos = null;
        ObjectOutputStream oos = null;

        try {

            fos = new FileOutputStream(path + fileName);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(this);
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
            // recovery
        }

    }

    public static Database initializeDB(String fileName) {
        Database db = null;
        FileInputStream fis = null;
        ObjectInputStream ois = null;

        try {
            fis = new FileInputStream(path + fileName);
            ois = new ObjectInputStream(fis);
            db = (Database) ois.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return db;
    }

    public static void main(String[] args) {
        Database db = new Database();
        Dog d1 = new Dog("rover", 2);
        Dog d2 = new Dog("barkis", 3);

        db.put(d1.getName(), d1);
        db.put(d2.getName(), d2);

        String fileName =  "database.db";
        db.commitDB(fileName);

        Database bup = Database.initializeDB(fileName);

        Dog d3 = bup.get("barkis");
        System.out.println(d3);
    }
}
