package auth;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class Tester {
    public final static Scanner keyboard = new Scanner(System.in);
    public static void main(String[] args) {
        String algorithm = "DES/ECB/PKCS5Padding";
        SecretKeyGenerator keyGen = null;
        SecretKey key = null;

        try {
            keyGen = new SecretKeyGenerator("DES");
            key = keyGen.getKey();
            if (key == null) {
                throw new IllegalStateException("No key for encryption/decryption.");
            }
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }

        System.out.println("Enter clear text:");
        String clearText = keyboard.nextLine();

        //create an encryptor to make cipher text
        Encryptor encr = null;
        try {
            encr = new Encryptor(clearText.getBytes(), algorithm, key);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }

        byte[] cypherTextArray = encr.getCipherText();
        System.out.println("Encrypted byte stream: " + cypherTextArray);

        Decryptor decr = null;

        try {
            decr = new Decryptor(cypherTextArray, algorithm, key);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | IllegalBlockSizeException |
                 BadPaddingException e) {
            throw new RuntimeException(e);
        }

        String recovered = new String(decr.getCleartext());
        System.out.println("Recovered text: " + recovered);


    }
}
