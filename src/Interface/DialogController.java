package Interface;

import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.layout.StackPane;

import DataManagement.*;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fabian on 01.12.16
 */

public class DialogController implements Initializable {

    @FXML private JFXTextField nameField;

    @FXML private JFXTextField usernameField;

    @FXML private JFXPasswordField passwordField;

    @FXML private JFXPasswordField retypedPasswordField;

    @FXML private JFXButton createNodeButton;

    @FXML private StackPane root;

    @FXML private JFXDialog dialog;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        createNodeButton.setOnMouseClicked((e) -> {
            if ((!nameField.getText().equals("") &&
                    !usernameField.getText().equals("") &&
                    !passwordField.getText().equals("")) &&
                    passwordField.getText().equals(retypedPasswordField.getText())) {
                Manager manager = new Manager();
                if(manager.addNewEntry(nameField.getText(), usernameField.getText(), passwordField.getText())) {
                    System.out.println("Created New Entry");
                }
            } else {
                System.out.println("Fields not correct filled");
            }
        });
    }
}
