package Security;

import java.util.*;

/**
 * Created by Fabian on 28.11.16.
 */
public class Login {
    private String username;
    private String givenPassword;
    private String passwordInDatabase;
    private String salt;
    private Date loginTime;

    public Login (String username, String givenPassword, String passwordInDatabase, String salt ,Date loginTime) {
        this.username = username;
        this.givenPassword = givenPassword;
        this.passwordInDatabase = passwordInDatabase;
        this.salt = salt;
        this.loginTime = loginTime;

        Security.Hash hash = new Security.Hash();
        if (hash.getSha512(givenPassword, salt).matches(passwordInDatabase)) {
            System.out.println("Login S U C C");
        }
    }
}