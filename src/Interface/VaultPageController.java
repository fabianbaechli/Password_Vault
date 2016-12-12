package Interface;

import DataManagement.Manager;
import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by Fabian on 01.12.16
 */

public class VaultPageController implements Initializable {

    @FXML
    JFXButton newNodeButton;

    @FXML
    JFXListView<Label> listView;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        Manager manager = new Manager();
        ArrayList content = manager.getContent();
        for (int i = 0; i < content.size(); i++) {
            System.out.println(content.get(i));
        }
        newNodeButton.setOnAction(event -> {
            Parent root;
            try {
                root = FXMLLoader.load(startPageController.class.getResource("Dialog.fxml"));
                Stage stage = new Stage();
                stage.setTitle("Vault");
                stage.setScene(new Scene(root));
                stage.show();

            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
}
