package auth;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Encryptor {
    private Cipher cipher;
    private byte[] cipherText;

    public Encryptor(byte[] clearText, String algorithm, SecretKey key) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.ENCRYPT_MODE, key);
        cipherText = cipher.doFinal(clearText);
    }

    public byte[] getCipherText() {
        return cipherText;
    }
}
