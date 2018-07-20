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

public class successController implements Initializable {

    private String labelText;

    @FXML
    private Label lblSuccess;

    @FXML
    private FontAwesomeIcon check;

    @FXML
    private JFXButton btnOK;

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
                lblSuccess.setText(labelText);
            }
        });

    }



}
