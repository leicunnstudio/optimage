package window;

import messages.errorSuccess;
import optimise.optimisation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Controller {

    Image image;
    String imagePath;
    String newIdentity;
    String folder;

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

    @FXML
    void closeThis(MouseEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    @FXML
    void minimizeThis(MouseEvent event) {
        Stage stage=(Stage) btnDiminish.getScene().getWindow();
        stage.setIconified(true);
    }

    @FXML
    void chooseImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        FileChooser.ExtensionFilter imageFilter
                = new FileChooser.ExtensionFilter("Image Files", "*.jpg", "*.jpeg");
        fileChooser.getExtensionFilters().add(imageFilter);
        folder = file.getParent();

        try {
            BufferedImage bufferedImage = ImageIO.read(file);
            image = SwingFXUtils.toFXImage(bufferedImage, null);
            imageHolder.setImage(image);
            imagePath = file.toString();
            txtFilePath.setText(imagePath);
        } catch (IOException ex) {
            Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    void optimise(ActionEvent event) {
        checkNewName();
    }

    //    CHECK TO SEE WHAT OPTIMISATION OPTION HAS BEEN CHOSEN
    void checkOptimisationOption() {
        if (checkCompress.isSelected()) {
            newIdentity = folder + "/" + txtNewId.getText() + "-compressed.jpg";
            System.out.println(newIdentity);
            optimisation optimisationObj = new optimisation();
            optimisationObj.compress(imagePath, newIdentity);
        } else if (checkWatermark.isSelected()) {

        } else if (checkWatermark.isSelected() && checkCompress.isSelected()) {

        } else {
            getMessage(0);
        }
    }

    //    CHECK TO SEE IF NEW NAME HAS BEEN FILLED/CHOSEN
    void checkNewName() {
        if (txtNewId.getText().trim().isEmpty()) {
            getMessage(0);
        } else {
            checkOptimisationOption();
        }
    }

    //    GET THE APPROPRIATE ALERT OR MESSAGE
    void getMessage(int n) {
        if (n == 0) {
            errorSuccess errorSuccessObj = new errorSuccess();
            errorSuccessObj.errorMessage();
        } else {
            errorSuccess errorSuccessObj = new errorSuccess();
            errorSuccessObj.successMessage();
        }
    }


}
