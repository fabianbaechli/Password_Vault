package Interface;

import DataManagement.Manager;
import com.jfoenix.controls.*;
import javafx.fxml.*;
import javafx.scene.*;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.awt.datatransfer.*;
import java.awt.Toolkit;

/**
 * Created by Fabian on 01.12.16
 */

public class VaultPageController implements Initializable {

    @FXML
    JFXButton newNodeButton;

    @FXML
    JFXListView<Label> listView;

    @FXML
    AnchorPane groundPane;

    @FXML
    JFXTextField titleField;

    @FXML
    JFXTextField usernameField;

    @FXML
    JFXPasswordField passwordField;

    @FXML
    JFXButton textToClipboard;

    private ArrayList content;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        updateSidePane();
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

    private void drawContent(Label titleLabel) {
        textToClipboard.setVisible(true);
        titleField.setVisible(true);
        usernameField.setVisible(true);
        passwordField.setVisible(true);
        titleField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        for (int i = 0; i < content.size(); i++) {
            if (content.get(i).toString().replace("title: ", "").equals(titleLabel.getText())) {
                String titleFieldText = content.get(i).toString().replace("title: ", "");
                String passwordFieldText = content.get(i - 1).toString().replace("password: ", "");
                String usernameFieldText = content.get(i - 2).toString().replace("username: ", "");

                titleField.setText(titleFieldText);
                usernameField.setText(usernameFieldText);
                passwordField.setText(passwordFieldText);
                textToClipboard.setOnAction(event -> {
                    StringSelection stringSelection = new StringSelection(passwordFieldText);
                    Clipboard textToClipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
                    textToClipBoard.setContents(stringSelection, null);
                });
                return;
            }
        }
    }

    private void updateSidePane() {
        listView.getItems().removeAll(listView.getItems());
        Manager manager = new Manager();
        content = manager.getContent();
        System.out.println("refreshed side panel content");
        for (Object aContent : content) {
            if (aContent.toString().startsWith("title")) {
                Label label = new Label(aContent.toString().replace("title: ", ""));
                listView.getItems().add(label);

                label.setOnMouseClicked(labelClick -> drawContent(label));
            }
        }
    }
}
