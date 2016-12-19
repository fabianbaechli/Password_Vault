package Interface;

import Security.Login;
import Security.Salt;
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

    @FXML private JFXButton generatePw;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

        createNodeButton.setOnMouseClicked((e) -> {
            if ((!nameField.getText().equals("") &&
                    !usernameField.getText().equals("") &&
                    !passwordField.getText().equals("")) &&
                    passwordField.getText().equals(retypedPasswordField.getText())) {
                Manager manager = new Manager();
                if(manager.addNewEntry(nameField.getText(), usernameField.getText(), passwordField.getText())) {
                    createNodeButton.getScene().getWindow().hide();
                }
            } else {
                System.out.println("Fields not correct filled-in");
            }
        });

        generatePw.setOnMouseClicked(event -> {
            Salt salt = new Salt();
            String uniqueString = salt.getUniqueString();
            passwordField.setText(uniqueString);
            retypedPasswordField.setText(uniqueString);
        });
    }
}
