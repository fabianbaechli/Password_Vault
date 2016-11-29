package DataManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import org.json.*;

class Json {
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
    Boolean addUser(String username, String password, String salt) {
        JSONObject obj = readFile();
        try {
            JSONArray arr = new JSONArray();

            JSONObject passwordObject = new JSONObject();
            JSONObject saltObject = new JSONObject();
            passwordObject.put("password", password);
            saltObject.put("salt", salt);

            arr.put(passwordObject);
            arr.put(saltObject);
            obj.put(username, arr);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        try (FileWriter file = new FileWriter(Json.class.getProtectionDomain().getCodeSource().getLocation().getPath() + "/DataManagement/Users.json")) {
            file.write(obj.toString());
            System.out.println("Successfully Copied JSON Object to File");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
