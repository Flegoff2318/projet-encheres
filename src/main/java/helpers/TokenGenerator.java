package helpers;

import java.security.SecureRandom;
import java.util.Base64;
// helper => package
public class TokenGenerator {
    private static final int TOKEN_LENGTH = 64; // Length of the token in bytes
    private static final SecureRandom random = new SecureRandom();

    public static String generateToken() {
        byte[] tokenBytes = new byte[TOKEN_LENGTH];
        random.nextBytes(tokenBytes);
        return Base64.getUrlEncoder().withoutPadding().encodeToString(tokenBytes);
    }
}