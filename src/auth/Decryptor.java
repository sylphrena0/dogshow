package auth;

import javax.crypto.*;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public class Decryptor {
    private byte[] cleartext;

    public Decryptor(byte[] cipherText, String algorithm, SecretKey key) throws
            NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException,
            IllegalBlockSizeException, BadPaddingException {
        Cipher cipher = Cipher.getInstance(algorithm);
        cipher.init(Cipher.DECRYPT_MODE, key);
        cleartext = cipher.doFinal(cipherText);
    }

    public byte[] getCleartext() {
        return cleartext;
    }
}
