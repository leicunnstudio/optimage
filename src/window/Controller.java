package window;

import messages.errorSuccess;
import optimise.optimisation;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXCheckBox;
import com.jfoenix.controls.JFXProgressBar;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class Controller {

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
        try {

            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Choose Image To Optimise");

            // filter/restrict the files that can be chosen to .jpeg and .jpg
            fileChooser.getExtensionFilters().addAll(
                    new FileChooser.ExtensionFilter("Image Files", "*.jpeg", "*.jpg"));

            //Show open file dialog
            File selectedImage = fileChooser.showOpenDialog(null);
            folder = selectedImage.getParent();
            System.out.println(folder);

            // Check if the image is empty (null)
            if (selectedImage != null) {
                imagePath = selectedImage.toString();
                txtFilePath.setText(imagePath);
                Image img = new Image(selectedImage.toURI().toURL().toExternalForm());
                imageHolder.setImage(img);
            } else {
                String message = "Choose an image to proceed with optimisation";
                errorOrSuccessMessage(0, message,null);
            }
        } catch (Exception e) {
            errorOrSuccessMessage(2, null, e);
        }

    }


    // Checks if any of the necessary controls are left blank
    @FXML
    void optimise(ActionEvent event) {
        checkNulls();
    }


    //    CHECK TO SEE WHAT OPTIMISATION OPTION HAS BEEN CHOSEN
    private void checkOptimisationOption() {
        if (checkCompress.isSelected()) {
            newIdentity = folder + "/" + txtNewId.getText() + "-compressed.jpg";
            System.out.println(newIdentity);
            optimisation optimisationObj = new optimisation();
            optimisationObj.compress(imagePath, newIdentity);
        } else if (checkWatermark.isSelected()) {
            newIdentity = folder + "/" + txtNewId.getText() + "-watermarked.jpg";
            System.out.println(newIdentity);
            optimisation optimisationObj = new optimisation();
            optimisationObj.watermark(imagePath, newIdentity);
        } else if (checkWatermark.isSelected() && checkCompress.isSelected()) {

        } else {
            String message = "Select an optimisation option: tick 'Compress', 'Watermark' or both.";
            errorOrSuccessMessage(0, message, null);
        }
    }


    //    CHECK TO SEE IF CONTROLS HAVE BEEN FILLED/CHOSEN
    void checkNulls() {
        if (!checkCompress.isSelected() && !checkWatermark.isSelected()) {
            String message = "Ensure you select an optimisation method to use";
            errorOrSuccessMessage(0, message, null);
        } else if (txtFilePath.getText().trim().isEmpty()) {
            String message = "Select an image to optimise";
            errorOrSuccessMessage(0, message, null);
        } else if (txtNewId.getText().trim().isEmpty()) {
            String message = "New identity cannot be left empty!";
            errorOrSuccessMessage(0, message, null);
        } else {
            checkOptimisationOption();
        }
    }


    //    GET THE APPROPRIATE ALERT OR MESSAGE
    void errorOrSuccessMessage(int n, String message, Exception e) {
        errorSuccess errorSuccessObj = new errorSuccess();
        errorSuccessObj.getMessage(n, message, e);
    }


}
