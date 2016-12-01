package DataManagement;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.Iterator;

/**
 * Created by Fabian on 29.11.16.
 */
public class Manager {
    public Boolean login(String username, String password) {
        Json json = new Json();
        JSONObject obj = json.readFile();
        try {
            Iterator<?> keys = obj.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (obj.get(key) != null) {
                    if (key.matches(username)) {
                        String passwordInDatabase = obj.getJSONArray(username).getJSONObject(0).getString("password");
                        String salt = obj.getJSONArray(username).getJSONObject(1).getString("salt");
                        Date date = new Date();
                        Security.Login login = new Security.Login(username, password, passwordInDatabase, salt, date);
                        if(login.logUserIn()) {
                            return true;
                        }
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Boolean addUser(String username, String password, String salt) {
        Json json = new Json();
        return json.addUser(username, password, salt);
    }
}
