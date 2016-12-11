package Security;

import java.util.*;

/**
 * Created by Fabian on 28.11.16.
 */
public class Login {
    private static String username;
    private static String givenPassword;
    private static String passwordInDatabase;
    private static String salt;
    private static Date loginTime;

    public static String getUsername() {
        return username;
    }

    public static String getGivenPassword() {
        return givenPassword;
    }

    public static String getPasswordInDatabase() {
        return passwordInDatabase;
    }

    public static String getSalt() {
        return salt;
    }

    public static Date getLoginTime() {
        return loginTime;
    }

    public Login(String username, String givenPassword, String passwordInDatabase, String salt, Date loginTime) {
        Login.username = username;
        Login.givenPassword = givenPassword;
        Login.passwordInDatabase = passwordInDatabase;
        Login.salt = salt;
        Login.loginTime = loginTime;

    }

    public Boolean logUserIn() {
        Hash hash = new Hash();
        return hash.getSha512(givenPassword, salt).matches(passwordInDatabase);
    }
}