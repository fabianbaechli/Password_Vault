package Interface;

import java.net.*;
import java.util.*;

import Security.Hash;
import Security.Salt;
import javafx.fxml.*;
import com.jfoenix.controls.*;

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
            System.out.println(dataManager.addUser(usernameField.getText(), hashedPassword, uniqueString));
        });
        loginButton.setOnAction(event -> {
            DataManagement.Manager manager = new DataManagement.Manager();
            String username = usernameField.getText();
            String password = passwordField.getText();

            manager.login(username, password);
        });
    }
}