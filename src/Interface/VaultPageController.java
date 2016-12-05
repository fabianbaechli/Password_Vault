package Interface;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Fabian on 01.12.16.
 */
public class VaultPageController implements Initializable {

    @FXML
    JFXListView<Label> listView;

    @FXML
    private JFXButton newNodeButton;
    @Override
    public void initialize(URL fxmlFileLocation, ResourceBundle resources) {
        newNodeButton.setOnAction(event -> {
            Label aLabel = new Label("Item ");
            listView.getItems().add(aLabel);
        });
    }
}
