package DataManagement;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import org.json.*;

public class Json {

    private static void readFile(){
        String jsonData = "";
        BufferedReader br = null;
        try {
            String line;
            br = new BufferedReader(new FileReader("/Users/Fabian/Desktop/mock_dat.json"));
            while ((line = br.readLine()) != null) {
                jsonData += line + "\n";
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null)
                    br.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        try {
            JSONObject obj = new JSONObject(jsonData);
            Iterator<?> keys = obj.keys();

            while(keys.hasNext()) {
                String key = (String)keys.next();
                if ( obj.get(key) instanceof JSONObject ) {
                    System.out.println("Title: " + obj.getString(key));
                }
            }
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
