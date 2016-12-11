package DataManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;

import org.json.*;

class Json {
    private String userFileLocation = Json.class.getProtectionDomain().getCodeSource().getLocation().getPath()
            + "/DataManagement/Users.json";

    private String contentFileLocation = Json.class.getProtectionDomain().getCodeSource().getLocation().getPath()
            + "/DataManagement/Content.json";

    JSONObject readFile(String path) {
        String jsonData = "";
        JSONObject obj = null;
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader(path));
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
        //User file
        JSONObject obj = readFile(userFileLocation);
        JSONObject contentObject = readFile(contentFileLocation);

        try {
            Iterator<?> keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (obj.get(key) != null && key.matches(username)) {
                    System.out.println("User Already Defined");
                    return false;
                }
            }
            JSONArray arr = new JSONArray();

            JSONObject passwordObject = new JSONObject();
            passwordObject.put("password", password);
            JSONObject saltObject = new JSONObject();
            saltObject.put("salt", salt);

            arr.put(passwordObject);
            arr.put(saltObject);
            obj.put(username, arr);
            //Content File

            JSONArray contentArray = new JSONArray();
            JSONObject content = new JSONObject();

            content.put("title", "Your First Entry");
            content.put("username", username);
            content.put("password", "Test Password");

            contentArray.put(content);
            contentObject.put(username, contentArray);
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        try (FileWriter file = new FileWriter(userFileLocation)) {
            file.write(obj.toString());
            System.out.println("Successfully created user in file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileWriter contentFile = new FileWriter(contentFileLocation)){
            contentFile.write(contentObject.toString());
            System.out.println("Created Content Object for User");
        } catch (IOException e) {
            e.printStackTrace();
        }

        return true;
    }

    Boolean addContent(String title, String username, String password, String mainUsername) {
        JSONObject obj = readFile(contentFileLocation);
        try {
            Iterator<?> keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (obj.get(key) != null && key.matches(mainUsername)) {
                    JSONArray arr = new JSONArray();
                    JSONObject content = new JSONObject();

                    content.put("title", title);
                    content.put("username", username);
                    content.put("password", password);

                    arr.put(content);
                    obj.append(mainUsername, arr);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
            return false;
        }
        try (FileWriter file = new FileWriter(contentFileLocation)) {
            file.write(obj.toString());
            System.out.println("Successfully wrote content to file");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }
}
