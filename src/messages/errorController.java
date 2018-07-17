package messages;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;

public class errorController {

    @FXML
    private JFXButton btnOK;

    @FXML
    private FontAwesomeIcon times;

    @FXML
    void closeThis(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

}
