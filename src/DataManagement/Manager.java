package DataManagement;

import org.json.JSONException;
import org.json.JSONObject;
import Security.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;

public class Manager {

    private String loginLocation = Manager.class.getProtectionDomain().getCodeSource().getLocation().getPath()
            + "/DataManagement/Users.json";

    public Boolean login(String username, String password) {
        Json json = new Json();
        JSONObject obj = json.readFile(loginLocation);
        try {
            Iterator<?> keys = obj.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (obj.get(key) != null && key.matches(username)) {
                    String passwordInDatabase = obj.getJSONArray(username).getJSONObject(0).getString("password");
                    String salt = obj.getJSONArray(username).getJSONObject(1).getString("salt");
                    Date date = new Date();
                    Login login = new Login(username, password, passwordInDatabase, salt, date);
                    if (login.logUserIn()) {
                        return true;
                    }

                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean addNewEntry(String title, String username, String password) {
        Json json = new Json();
        return json.addContent(title, username, password, Login.getUsername(), Login.getGivenPassword());
    }

    public Boolean addUser(String username, String password, String salt, String mainPassword) {
        Json json = new Json();
        return json.addUser(username, password, salt, mainPassword);
    }
    public ArrayList getContent() {
        Json json = new Json();
        return json.getContent();
    }
}
