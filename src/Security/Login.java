package Security;

import java.util.*;

/**
 * Created by Fabian on 28.11.16.
 */
public class Login {
    private String username;
    private String password;
    private Date loginTime;

    public Login (String username, String password, Date loginTime) {
        this.username = username;
        this.password = password;
        this.loginTime = loginTime;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Date getLoginTime() {
        return loginTime;
    }
}