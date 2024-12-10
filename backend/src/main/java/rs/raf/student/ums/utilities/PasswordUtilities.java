package rs.raf.student.ums.utilities;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;

import java.security.SecureRandom;
import java.util.Random;

public class PasswordUtilities {

    private static final Random saltGenerator = new SecureRandom();

    public static String generateSalt() {
        byte[] salt = new byte[16];

        saltGenerator.nextBytes(salt);

        return encodeBase64(salt);
    }

        public static String hashPassword(String password, String salt) {
        return encodeBase64(DigestUtils.sha256(password + salt));
    }

    public static String encodeBase64(byte[] bytes) {
        return new String(Base64.encodeBase64(bytes));
    }

}
