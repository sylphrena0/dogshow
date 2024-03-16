package db;


import lombok.SneakyThrows;
import org.sqlite.mc.SQLiteMCConfig;
import utilities.Constants;
import utilities.Utilities;

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
import java.util.logging.Level;
import java.util.logging.Logger;

public class Database {
    private static final String CLASS_PATH = System.getProperty("java.class.path");
    private static final String CURRENT_PATH = CLASS_PATH.substring(0, CLASS_PATH.lastIndexOf(Constants.PATH_SEPARATOR)); // remove everything after the last path separator
    private static final String DB_PATH = "jdbc:sqlite:%sdatabase.db".formatted(CURRENT_PATH + Constants.PATH_SEPARATOR);
    private static final String SALT_PATH = CURRENT_PATH + Constants.PATH_SEPARATOR + "salt.txt";
    private static Connection connection = null;
    private static byte[] salt = null;
    private static final Logger logger = Logger.getLogger(Database.class.getName());
    private static final String DATABASE_ERROR = "Critical database error! Contact the developer if the issue persists.";

    private Database() {
        throw new IllegalStateException("Database class");
    }
    /**
     * Check if the database exists
     * @return true if the database exists, false otherwise
     **/
    @SneakyThrows
    public static boolean databaseExists() {
        try {
            FileInputStream fis = new FileInputStream(SALT_PATH);
            fis.close();
        } catch (FileNotFoundException e) {
            return false;
        }
        return true;
    }

    /**
     * Creates a database if it does not exist and connects to it
     * @param password the password to use to encrypt the database
     **/
    private static void createDatabase(char[] password) {
        // check if we already have a database by checking if the salt file exists
        if (databaseExists()) {
            Utilities.showError("Attempted to create database when it already exists - check if the salt.txt file was moved or deleted", logger, null);
        }

        // generate a secret key using the registrar's password
        salt = new byte[8]; // generate key salt and fill with random bytes
        SecureRandom random = new SecureRandom();
        random.nextBytes(salt);

        try (Connection conn = DriverManager.getConnection(DB_PATH)) {
            // write salt to src/db/salt.txt
            try (FileOutputStream fos = new FileOutputStream(SALT_PATH)) {
                fos.write(salt);
            }

            if (conn != null) {
                DatabaseMetaData meta = conn.getMetaData();
                logger.log(Level.INFO, "The driver name is {}", meta.getDriverName());
                logger.log(Level.INFO, "A new database has been created.");

                connect(password);

                String userStatement = """
                        CREATE TABLE IF NOT EXISTS users (
                         id INTEGER PRIMARY KEY AUTOINCREMENT,
                         username TEXT NOT NULL,
                         name TEXT,
                         email TEXT
                        );
                        """;
                try (Statement users = connection.createStatement()) {
                    users.execute(userStatement);
                }

                String recordsStatement = """
                        CREATE TABLE IF NOT EXISTS records (
                         regID INTEGER PRIMARY KEY AUTOINCREMENT,
                         familyName TEXT NOT NULL,
                         email VARCHAR(254) NOT NULL,
                         name TEXT NOT NULL,
                         breed TEXT NOT NULL,
                         age TINYINT NOT NULL,
                         color TEXT NOT NULL,
                         markings TEXT,
                         obedience BLOB,
                         socialization BLOB,
                         grooming BLOB,
                         fetch BLOB,
                         photo BLOB NOT NULL,
                         year SMALLINT NOT NULL,
                         current BOOLEAN NOT NULL
                        );
                        """;
                try (Statement records = connection.createStatement()) {
                    records.execute(recordsStatement);
                }

                logger.log(Level.INFO, "Database tables created.");
            }

        } catch (SQLException | IOException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
            System.exit(1);
        }
    }

    /**
     * Sets the salt from the salt file
     **/
    private static void setSalt() throws IOException {
        if (salt == null) {
            try {
                salt = new byte[8];
                FileInputStream fis = new FileInputStream(SALT_PATH);
                salt = fis.readNBytes(8);
                fis.close();
            } catch (FileNotFoundException e) {
                Utilities.showError("Salt file not found. Database may be corrupted.", logger, e);
            }
        }
    }

    /**
     * Connects to the database
     * @param password the password to use to decrypt the database
     **/
    private static void connect(char[] password) throws SQLException {
        SecretKey key;
        try {
            // get salt from salt.txt if it isn't already retrieved
            setSalt();

            // get secret key from password using salt
            key = new SecretKeySpec(SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256").generateSecret(new PBEKeySpec(password, salt, 65536, 256)).getEncoded(), "AES");
        } catch (IOException | NoSuchAlgorithmException | InvalidKeySpecException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
            System.exit(1);
            return;
        }

        if (connection != null) { // connection already exists
            logger.log(Level.INFO, "Connection to SQLite already exists.");
            return;
        }

        DriverManager.getConnection(DB_PATH, new SQLiteMCConfig.Builder().withKey(key.toString()).build().toProperties());
        connection = new SQLiteMCConfig.Builder().withKey(key.toString()).build().createConnection(DB_PATH);
    }

    /**
     * Disconnects from the database
     **/
    @SneakyThrows
    public static void disconnect() {
        if (connection == null) {
            return; // no connection to close
        }

       connection.close();
    }


    public static void register(String username, char[] password, String name, String email) {
        createDatabase(password); // create database if it does not exist and connect to the database

        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users (username, name, email) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, email);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
        }
    }

    public static boolean login(String username, char[] password) {
        try{
            connect(password);
            try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM users WHERE username = ?")) {
                preparedStatement.setString(1, username);
                ResultSet resultSet = preparedStatement.executeQuery();
                if (!resultSet.next()) {
                    return false; // username does not exist (but password is correct)
                }
            }
        } catch (SQLException e) {
            return false; // password is incorrect
        }
        return true;
    }

    public static int registerContestant(String familyName, String email, String name, String breed, int age, String color, String markings, String obedience, String socialization, String grooming, String fetch, byte[] photo, int year) {
        try {
            boolean current = true;

            try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO records(familyName, email, name, breed, age, color, markings, obedience, socialization, grooming, fetch, photo, year, current) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?)")) {
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
                preparedStatement.setBytes(12, photo);
                preparedStatement.setInt(13, year);
                preparedStatement.setBoolean(14, current);
                preparedStatement.executeUpdate();
            }

            // get regId
            try (PreparedStatement getRegID = connection.prepareStatement("SELECT regID FROM records WHERE familyName = ? AND email = ? AND name = ? AND breed = ? AND age = ? AND color = ? AND year = ? AND current = ?")) {
                getRegID.setString(1, familyName);
                getRegID.setString(2, email);
                getRegID.setString(3, name);
                getRegID.setString(4, breed);
                getRegID.setInt(5, age);
                getRegID.setString(6, color);
                getRegID.setInt(7, year);
                getRegID.setBoolean(8, current);
                ResultSet resultSet = getRegID.executeQuery();
                if (!resultSet.next()) {
                    Utilities.showInternalError(DATABASE_ERROR, logger, null);
                }
                return resultSet.getInt("regID");
            }
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
            System.exit(1);
            return -1;
        }
    }
    public static void editScore(Integer registrationID, String obedience, String socialization, String grooming, String fetch) {
        // check if current is false, if so, throw an error
        try (PreparedStatement currentCheck = connection.prepareStatement("SELECT * FROM records WHERE regID = ?")) {
            currentCheck.setInt(1, registrationID);
            ResultSet resultSet = currentCheck.executeQuery();
            if (!resultSet.next() || !resultSet.getBoolean("current")) {
                throw new IllegalStateException("Error: registrationID does not exist or is not current");
            }
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
        }

        try (PreparedStatement updateScore = connection.prepareStatement("UPDATE records SET obedience = ?, socialization = ?, grooming = ?, fetch = ? WHERE regID = ?")) {
            updateScore.setString(1, obedience);
            updateScore.setString(2, socialization);
            updateScore.setString(3, grooming);
            updateScore.setString(4, fetch);
            updateScore.setInt(5, registrationID);
            updateScore.executeUpdate();
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
        }
    }

    public static void commitScores() {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("UPDATE records SET current = false WHERE current = true");
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
        }
    }

    public static Object[] getContestant(int regID) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM records WHERE regID = ?")) {
            preparedStatement.setInt(1, regID);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (!resultSet.next()) {
                throw new IllegalStateException("Registration ID does not exist");
            }

            Object[] dbRecord = new Object[15];
            dbRecord[0] = resultSet.getInt("regID");
            dbRecord[1] = resultSet.getString("familyName");
            dbRecord[2] = resultSet.getString("email");
            dbRecord[3] = resultSet.getString("name");
            dbRecord[4] = resultSet.getString("breed");
            dbRecord[5] = resultSet.getInt("age");
            dbRecord[6] = resultSet.getString("color");
            dbRecord[7] = resultSet.getString("markings");
            dbRecord[8] = resultSet.getString("obedience");
            dbRecord[9] = resultSet.getString("socialization");
            dbRecord[10] = resultSet.getString("grooming");
            dbRecord[11] = resultSet.getString("fetch");
            dbRecord[12] = resultSet.getBytes("photo");
            dbRecord[13] = resultSet.getInt("year");
            dbRecord[14] = resultSet.getBoolean("current");
            return dbRecord;
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
            return new Object[0];
        }
    }

    public static Object[][] getScores(boolean current) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT regID, name, obedience, socialization, grooming, fetch FROM records WHERE current = ?")) {
            preparedStatement.setBoolean(1, current);
            ResultSet resultSet = preparedStatement.executeQuery();
            ArrayList<Object[]> records = new ArrayList<>();
            while (resultSet.next()) {
                Object[] dbRecord = new Object[6];
                dbRecord[0] = resultSet.getInt("regID");
                dbRecord[1] = resultSet.getString("name");
                dbRecord[2] = resultSet.getString("obedience");
                dbRecord[3] = resultSet.getString("socialization");
                dbRecord[4] = resultSet.getString("grooming");
                dbRecord[5] = resultSet.getString("fetch");
                records.add(dbRecord);
            }
            return records.toArray(new Object[0][0]);
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
            return new Object[0][0];
        }
    }

    public static String[] getYears() {
        ArrayList<String> years = new ArrayList<>();
        try (Statement stmt = connection.createStatement()) {
            ResultSet resultSet = stmt.executeQuery("SELECT DISTINCT year FROM records WHERE current = false ORDER BY year DESC");
            while (resultSet.next()) {
                years.add(resultSet.getString("year"));
            }
        } catch (SQLException e) {
            Utilities.showInternalError(DATABASE_ERROR, logger, e);
        }
        return years.toArray(new String[0]);
    }
}



