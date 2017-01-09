package DataManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.json.*;
import Security.*;

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

    Boolean addUser(String username, String password, String salt, String mainPassword) {
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
            Hash hash = new Hash();
            Encryption encryption = new Encryption();

            String keyword = hash.getSha256(mainPassword).substring(0, 16);
            String firstTitle = encryption.cipher(true, keyword, "First Entry");
            String firstUsername = encryption.cipher(true, keyword, "Test User");
            String firstPassword = encryption.cipher(true, keyword, "Test Password");

            System.out.println("keyword: " + keyword);

            content.put("title", firstTitle);
            content.put("username", firstUsername);
            content.put("password", firstPassword);

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
        try (FileWriter contentFile = new FileWriter(contentFileLocation)) {
            contentFile.write(contentObject.toString());
            System.out.println("Created Content Object for User");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true;
    }

    Boolean addContent(String title, String username, String password, String mainUsername, String mainPassword) {
        JSONObject obj = readFile(contentFileLocation);
        try {
            Iterator<?> keys = obj.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (obj.get(key) != null && key.matches(mainUsername)) {
                    Encryption encryption = new Encryption();
                    JSONArray arr = new JSONArray();
                    JSONObject content = new JSONObject();
                    Hash hash = new Hash();
                    String keyword = hash.getSha256(mainPassword).substring(0, 16);

                    String encryptedTitle = encryption.cipher(true, keyword, title);
                    String encryptedusername = encryption.cipher(true, keyword, username);
                    String encryptedpassword = encryption.cipher(true, keyword, password);

                    content.put("title", encryptedTitle);
                    content.put("username", encryptedusername);
                    content.put("password", encryptedpassword);

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

    ArrayList getContent() {
        Json json = new Json();
        JSONObject obj = json.readFile(contentFileLocation);
        ArrayList<String> returnList = new ArrayList<>();
        try {
            Iterator<?> keys = obj.keys();

            while (keys.hasNext()) {
                String key = (String) keys.next();
                if (obj.get(key) != null && key.equals(Login.getUsername())) {
                    JSONArray temp = obj.getJSONArray(key);
                    for (int i = 0; i < temp.length(); i++) {
                        String password;
                        String username;
                        String title;
                        if (i < 1) {
                            password = temp.getJSONObject(i).getString("password");
                            title = temp.getJSONObject(i).getString("title");
                            username = temp.getJSONObject(i).getString("username");
                        } else {
                            password = temp.getJSONArray(i).getJSONObject(0).getString("password");
                            title = temp.getJSONArray(i).getJSONObject(0).getString("title");
                            username = temp.getJSONArray(i).getJSONObject(0).getString("username");
                        }

                        Hash hash = new Hash();
                        Encryption encryption = new Encryption();
                        String keyword = hash.getSha256(Login.getGivenPassword()).substring(0, 16);

                        username = encryption.cipher(false, keyword, username);
                        password = encryption.cipher(false, keyword, password);
                        title = encryption.cipher(false, keyword, title);

                        returnList.add("username: " + username);
                        returnList.add("password: " + password);
                        returnList.add("title: " + title);
                    }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return returnList;
    }
}
