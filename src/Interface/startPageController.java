package Interface;

import java.io.IOException;
import java.net.*;
import java.util.*;

import Security.*;
import DataManagement.*;

import javafx.fxml.*;
import com.jfoenix.controls.*;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class startPageController implements Initializable {

    @FXML
    private JFXButton createButton;

    @FXML
    private JFXButton loginButton;

    @FXML
    private JFXTextField usernameField;

    @FXML
    private JFXPasswordField passwordField;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        createButton.setOnAction(event -> {
            Salt salt = new Security.Salt();
            Hash hash = new Security.Hash();

            String uniqueString = salt.getUniqueString();
            String hashedPassword = hash.getSha512(passwordField.getText(), uniqueString);

            System.out.println("Hash: " + hash.getSha512(passwordField.getText(), uniqueString) + "\n" +
                    "Salted With: " + uniqueString);

            Manager dataManager = new Manager();
            dataManager.addUser(usernameField.getText(), hashedPassword, uniqueString, passwordField.getText());
        });

        loginButton.setOnAction(event -> {
            Manager manager = new Manager();
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (manager.login(username, password)) {
                System.out.println("Login Successful");

                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("VaultPage.fxml"));
                    Parent root = (Parent) loader.load();
                    Stage stage = new Stage();
                    stage.setTitle("Vault");
                    stage.setScene(new Scene(root));
                    VaultPageController controller = loader.getController();
                    controller.setStageAndSetupListeners(stage);
                    stage.show();

                    ((Node) (event.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}