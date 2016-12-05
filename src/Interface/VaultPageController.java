package Interface;

import com.jfoenix.controls.*;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fabian on 01.12.16
 */
public class VaultPageController implements Initializable {

    @FXML
    JFXListView<Label> listView;

    @FXML
    private JFXButton newNodeButton;
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {

    }
}
