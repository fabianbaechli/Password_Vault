package DataManagement;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;

/**
 * Created by Fabian on 29.11.16.
 */
public class Manager {
    public void login(String username, String password) {
        Json json = new Json();
        JSONObject obj = json.readFile();
        try {
            Iterator<?> keys = obj.keys();

            while(keys.hasNext()) {
                String key = (String)keys.next();
                if (obj.get(key) instanceof JSONObject ) {
                    if (key.matches(username)) {
                        String passwordInDatabase = obj.getJSONObject(username).getString("password");
                        String salt = obj.getJSONObject(username).getString("salt");
                        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
                        Date date = new Date();
                        Security.Login login = new Security.Login(username, password, passwordInDatabase, salt, date);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
