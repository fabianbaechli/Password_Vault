package Interface;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fabian on 01.12.16
 */

public class VaultPageController implements Initializable {

    @FXML
    JFXButton newNodeButton;

    @FXML
    JFXPopup newNodePopUp;

    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        newNodeButton.setOnAction(event -> {
            newNodePopUp.setVisible(true);
        });
    }
}
