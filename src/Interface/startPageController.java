package Interface;

import java.io.IOException;
import java.net.*;
import java.util.*;

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
            Security.Salt salt = new Security.Salt();
            Security.Hash hash = new Security.Hash();

            String uniqueString = salt.getUniqueString();
            String hashedPassword = hash.getSha512(passwordField.getText(), uniqueString);

            System.out.println("Hash: " + hash.getSha512(passwordField.getText(), uniqueString) + "\n" +
                    "Salted With: " + uniqueString);

            DataManagement.Manager dataManager = new DataManagement.Manager();
            dataManager.addUser(usernameField.getText(), hashedPassword, uniqueString);
        });
        loginButton.setOnAction(event -> {
            DataManagement.Manager manager = new DataManagement.Manager();
            String username = usernameField.getText();
            String password = passwordField.getText();

            if (manager.login(username, password)) {
                System.out.println("Login Successful");
                Parent root;
                try {
                    root = FXMLLoader.load(startPageController.class.getResource("VaultPage.fxml"));
                    Stage stage = new Stage();
                    stage.setTitle("Vault");
                    stage.setScene(new Scene(root));
                    stage.show();

                    //hides this current window
                    ((Node) (event.getSource())).getScene().getWindow().hide();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}