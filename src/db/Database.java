package db;

//import org.sqlite.mc.SQLiteMCConfig;

import org.sqlite.mc.SQLiteMCConfig;

import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.util.ArrayList;

public class Database {

    private static String url = "jdbc:sqlite:database.db";
    private static Connection connection = null;
    private static byte[] salt = null;

    /**
     * Creates a database if it does not exist and connects to it
     * @param password the password to use to encrypt the database
     **/
    private static void createDatabase(char[] password) {
        //check if we already have a database by checking if the salt file exists
        try {
            salt = new byte[16];
            FileInputStream fis = new FileInputStream("src/db/salt.txt");
            fis.read();
            fis.close();
            return;
        } catch (FileNotFoundException e) {
            //database does not exist, create it
        } catch (IOException e) { //TODO: check if this works :) (I don't think it does)
            return; //database exists, return nothing
        }

        //generate a secret key using the registrar's password
        try {
            //generate key salt and fill with random bytes
            salt = new byte[8];
            SecureRandom random = new SecureRandom();
            random.nextBytes(salt);
            //write salt to src/db/salt.txt
            FileOutputStream fos = new FileOutputStream("src/db/salt.txt");
            fos.write(salt);
            fos.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try (Connection conn = DriverManager.getConnection(url)) {
            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                System.out.println("The driver name is " + meta.getDriverName());
                System.out.println("A new database has been created.");

                connect(password);

                String tableStatement = "CREATE TABLE IF NOT EXISTS users (\n"
                        + " id INTEGER PRIMARY KEY AUTOINCREMENT,\n"
                        + " username TEXT NOT NULL,\n" //unique username
                        + " name TEXT,\n"
                        + " email TEXT\n"
                        + "); \n"
                        + "CREATE TABLE IF NOT EXISTS records (\n"
                        + " regID SMALLINT PRIMARY KEY AUTOINCREMENT,\n" //smallint (-32,768 to 32,767)
                        + " familyName TEXT NOT NULL,\n"
                        + " email VARCHAR(254) NOT NULL\n" //254 is max length of email
                        + " name TEXT NOT NULL\n"
                        + " breed TEXT NOT NULL\n"
                        + " age TINYINT NOT NULL\n" //tinyint (0 to 255)
                        + " color TEXT NOT NULL\n"
                        + " markings TEXT NOT NULL\n"
                        + " obedience BLOB NOT NULL\n" //blob to store arrays
                        + " socialization BLOB NOT NULL\n" //blob to store arrays
                        + " grooming BLOB NOT NULL\n" //blob to store arrays
                        + " fetch BLOB NOT NULL\n" //blob to store arrays
                        + " photo BLOB NOT NULL\n" //blob to store dog photo
                        + " year SMALLINT NOT NULL\n"
                        + " current BOOLEAN NOT NULL\n"
                        + ");";
                Statement stmt = connection.createStatement();
                stmt.execute(tableStatement);

                System.out.println("Database tables created.");
            }

        } catch (SQLException e) {
            System.out.println("Error initializing database: " +  e.getMessage());
        }
    }

    /**
        * Connects to the database
        * @param password the password to use to decrypt the database
        * @throws RuntimeException if the database does not exist
     **/
    private static void connect(char[] password) throws SQLException {
        //get salt from salt.txt if it isn't already set
        if (salt == null) {
            try {
                salt = new byte[8];
                FileInputStream fis = new FileInputStream("src/db/salt.txt");
                salt = fis.readNBytes(8);
                fis.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Database does not exist!");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        SecretKey key = null;
        try { //get secret key from password using salt
            key = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(new PBEKeySpec(password, salt, 65536, 256)).getEncoded(), "AES");
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (connection != null) {
            System.out.println("Connection to SQLite already exists.");
            return;
        } // connection already exists

        DriverManager.getConnection(url, new SQLiteMCConfig.Builder().withKey(key.toString()).build().toProperties());
        connection = new SQLiteMCConfig.Builder().withKey(key.toString()).build().createConnection(url);

    }

    /**
        * Disconnects from the database
     **/
    public static void disconnect() {
        if (connection == null) {
            return; //no connection to close
        }

        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    public static void register(String username, char[] password, String name, String email) {
        try {
            createDatabase(password); //create database if it does not exist and connect to the database

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, name, email) VALUES (?, ?, ?)");
            preparedStatement.setString(1, username);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean login(String username, char[] password) {
        try {
            connect(password);
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?");
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return false; //username does not exist (but password is correct)
            }
        } catch (SQLException e) {
            return false; //password is incorrect
        }
        return true;
    }

    public static void registerContestant(String familyName, String email, String name, String breed, int age, String color, String markings, String obedience, String socialization, String grooming, String fetch, String photo, int year) {
        try {
//            connect();
            //TODO: parse obedience, socialization, grooming, fetch arrays into strings
            //TODO: check that inputs are valid

            boolean current = true;

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO records(familyName, email, name, breed, age, color, markings, obedience, socialization, grooming, fetch, photo, year, current) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            preparedStatement.setString(1, familyName);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, name);
            preparedStatement.setString(4, breed);
            preparedStatement.setInt(5, age);
            preparedStatement.setString(6, color);
            preparedStatement.setString(7, markings);
            preparedStatement.setString(8, obedience);
            preparedStatement.setString(9, socialization);
            preparedStatement.setString(10, grooming);
            preparedStatement.setString(11, fetch);
            preparedStatement.setString(12, photo);
            preparedStatement.setInt(13, year);
            preparedStatement.setBoolean(14, current);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean editScore(Integer registrationID, Array obedience, Array socialization, Array grooming, Array fetch) {
        try {
            //check if current is false, if so, return an error
            PreparedStatement currentCheck = connection.prepareStatement("SELECT * FROM records WHERE regID = ?");
            currentCheck.setInt(1, registrationID);
            ResultSet resultSet = currentCheck.executeQuery();
            if (!resultSet.next() || !resultSet.getBoolean("current")) {
                return false; //registrationID does not exist or record is not current
            }

            //TODO: check that scores are valid
            //return false if any scores arrays contain values that are not ints between 0 and 10

            //TODO: parse obedience, socialization, grooming, fetch arrays into strings

            PreparedStatement updateScore = connection.prepareStatement("UPDATE records SET obedience = ?, socialization = ?, grooming = ?, fetch = ? WHERE regID = ?");
            updateScore.setArray(1, obedience);
            updateScore.setArray(2, socialization);
            updateScore.setArray(3, grooming);
            updateScore.setArray(4, fetch);
            updateScore.setInt(5, registrationID);
            updateScore.executeUpdate();
            return true;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void commitScores() {
        try {
            Statement stmt = connection.createStatement();
            stmt.execute("UPDATE records SET current = false WHERE current = true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object[] getContestant(int regID) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM records WHERE regID = ?");
            preparedStatement.setInt(1, regID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                return null; //registrationID does not exist
            }
            Object[] record = new Object[14];
            record[0] = resultSet.getInt("regID");
            record[1] = resultSet.getString("familyName");
            record[2] = resultSet.getString("email");
            record[3] = resultSet.getString("name");
            record[4] = resultSet.getString("breed");
            record[5] = resultSet.getInt("age");
            record[6] = resultSet.getString("color");
            record[7] = resultSet.getString("markings");
            record[8] = resultSet.getString("obedience");
            record[9] = resultSet.getString("socialization");
            record[10] = resultSet.getString("grooming");
            record[11] = resultSet.getString("fetch");
            record[12] = resultSet.getString("photo");
            record[13] = resultSet.getInt("year");
            return record;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Object[][] getScores(boolean current) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement("SELECT regID, name, obedience, socialization, grooming FROM records WHERE current = ?");
            preparedStatement.setBoolean(1, current);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Object[]> records = new ArrayList<>();
            while (resultSet.next()) {
                Object[] record = new Object[14];
                record[0] = resultSet.getInt("regID");
                record[3] = resultSet.getString("name");
                record[8] = resultSet.getString("obedience");
                record[9] = resultSet.getString("socialization");
                record[10] = resultSet.getString("grooming");
                record[11] = resultSet.getString("fetch");
                records.add(record);
            }
            return records.toArray(new Object[0][0]);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}


