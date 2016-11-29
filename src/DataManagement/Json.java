package DataManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import org.json.*;

public class Json {
    JSONObject readFile() {
        String jsonData = "";
        JSONObject obj = null;
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(Json.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/DataManagement/Users.json"));
            while ((line = br.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                    obj = new JSONObject(jsonData);
                }
            } catch (IOException | JSONException ex) {
                ex.printStackTrace();
            }
        }
        return obj;
    }
}
