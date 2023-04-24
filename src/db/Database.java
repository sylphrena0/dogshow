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
import java.security.spec.KeySpec;
import java.sql.*;

public class Database {

    private static String url = "jdbc:sqlite:database.db";
    private static Connection connection = null;
    private static byte[] salt = null;

    /**
     * Creates a database if it does not exist and connects to it
     * @param password the password to use to encrypt the database
     **/
    private static void createDatabase(String password) {
        //check if we already have a database by checking if the salt file exists
        try {
            salt = new byte[16];
            FileInputStream fis = new FileInputStream("src/db/salt.txt");
            fis.read();
            fis.close();
            return;
        } catch (FileNotFoundException e) {
            //database does not exist, create it
        } catch (IOException e) {
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
                        + " id TINYINT PRIMARY KEY AUTOINCREMENT,\n"
                        + " username TEXT NOT NULL UNIQUE,\n" //unique username
                        + " password TEXT NOT NULL\n"
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
    private static void connect(String password) throws SQLException {
        //get salt from salt.txt if it isn't already set
        if (salt == null) {
            try {
                salt = new byte[8];
                FileInputStream fis = new FileInputStream("src/db/salt.txt");
                salt = fis.readNBytes(8);
                fis.close();
            } catch (FileNotFoundException e) {
                throw new RuntimeException("Database does not exist: " + e.getMessage());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

        SecretKey key = null;
        try { //get secret key from password using salt
            key = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(new PBEKeySpec(password.toCharArray(), salt, 65536, 256)).getEncoded(), "AES");
        } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        if (connection != null) {
            return;
        } // connection already exists

        DriverManager.getConnection(url, new SQLiteMCConfig.Builder().withKey(key.toString()).build().toProperties());
        connection = new SQLiteMCConfig.Builder().withKey(key.toString()).build().createConnection(url);

        System.out.println("Connection to SQLite has been established.");
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

    /**
        * Executes a prepared statement
        * @param preparedStatement the prepared statement to execute
        * @throws SQLException if the prepared statement is invalid
     **/
    private static void execute(PreparedStatement preparedStatement) throws SQLException {
        preparedStatement.executeUpdate();
    }

    public static void register(String username, String password) {
        try {
            //TODO: check that inputs are valid

            createDatabase(password); //create database if it does not exist and connect to the database

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(username, password) VALUES(?,?)");
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            execute(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean login(String username, String password) {
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

    public static void insertRecord(String familyName, String email, String name, String breed, int age, String color, String markings, String obedience, String socialization, String grooming, String fetch, String photo, int year) {
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
            execute(preparedStatement);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean editScore(Integer registrationID, String obedience, String socialization, String grooming, String fetch) {
        try {
            //TODO: check if current is false, if so, return an error
            //TODO: check that scores are valid
            //TODO: parse obedience, socialization, grooming, fetch arrays into strings

            PreparedStatement preparedStatement = connection.prepareStatement("UPDATE records SET obedience = ?, socialization = ?, grooming = ?, fetch = ? WHERE regID = ?");
            preparedStatement.setString(1, obedience);
            preparedStatement.setString(2, socialization);
            preparedStatement.setString(3, grooming);
            preparedStatement.setString(4, fetch);
            preparedStatement.setInt(5, registrationID);
            execute(preparedStatement);
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


    public static void main(String[] args) {
    }
}

