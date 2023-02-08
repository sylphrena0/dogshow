package auth;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;

public class SecretKeyGenerator {
    private KeyGenerator keyGen;
    private SecretKey key;

    public SecretKeyGenerator(String algorithm)
        throws NoSuchAlgorithmException {
        keyGen = KeyGenerator.getInstance(algorithm);
        key = keyGen.generateKey();
    }

    public SecretKey getKey() {
        return key;
    }
}
