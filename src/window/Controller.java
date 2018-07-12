package window;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

public class Controller {

    @FXML
    private ImageView btnClose;

    @FXML
    private ImageView btnDiminish;

    @FXML
    private JFXCheckBox checkCompress;

    @FXML
    private JFXCheckBox checkWatermark;

    @FXML
    private TextField txtFilePath;

    @FXML
    private JFXButton btnChoose;

    @FXML
    private ImageView imageHolder;

    @FXML
    private TextField txtNewId;

    @FXML
    private JFXProgressBar progress;

    @FXML
    private JFXButton compressSave;

}
