package messages;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;

public class errorController implements Initializable {

    private String labelText;

    @FXML
    private Label lblError;

    @FXML
    private JFXButton btnOK;

    @FXML
    private FontAwesomeIcon times;

    @FXML
    void closeThis(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void setLabelText(String message) {
        this.labelText = message;
    }

    @FXML
    public void initialize(URL url, ResourceBundle rb) {

        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                lblError.setText(labelText);
            }
        });

    }

}
