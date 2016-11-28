package Security;

import java.security.SecureRandom;
import java.math.BigInteger;

public final class Salt {
    private SecureRandom random = new SecureRandom();

    public String getUniqueString() {
        return new BigInteger(130, random).toString(32);
    }
}