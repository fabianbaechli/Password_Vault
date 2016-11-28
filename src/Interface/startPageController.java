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
    private JFXTextField usernameTextField;

    @FXML
    private JFXPasswordField passwordTextField;


    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        createButton.setOnAction(event -> {
            //Create User here
            Salt salt = new Salt();
            System.out.println(salt.getUniqueString());
            System.out.println(Hash.getSha512("Hello du kevin", "salt"));
        });
        loginButton.setOnAction(event -> {
            //Login User Here
            Salt salt = new Salt();
            System.out.println(salt.getUniqueString());
            System.out.println(Hash.getSha512("Hello du kevin", "saltt"));
        });
    }
}