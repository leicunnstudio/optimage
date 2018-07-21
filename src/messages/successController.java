package messages;

import com.jfoenix.controls.JFXButton;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Label;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class successController implements Initializable {

    private String labelText;
    private String filePath;
    private File fd;

    @FXML
    private Label lblSuccess;

    @FXML
    private FontAwesomeIcon check;

    @FXML
    private JFXButton btnOK;

    @FXML
    private JFXButton btnViewFolder;

    @FXML
    private JFXButton btnViewFile;

    @FXML
    void closeThis(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }


    // sets the text to appear on success message dialog
    public void setLabelText(String message) {
        this.labelText = message;
    }


    // sets the filePath variable for where the file optimised is located
    public void setFilePath(String path) {
        this.filePath = path;
    }


    // opens the new file that has been optimised
    @FXML
    void viewFile(ActionEvent event) {
        Desktop desktop = Desktop.getDesktop();
        try {
            fd = new File(filePath);
            System.out.println(fd);
            desktop.open(fd);
        } catch (IllegalArgumentException | IOException iae) {
//            System.out.println("File Not Found");
            String message = "File Not Found";
            errorOrSuccessMessage(0, message, null);
        } finally {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }


    // opens the location of the new file that has been opened
    @FXML
    void viewFolder(ActionEvent event) {
        Desktop desktop = Desktop.getDesktop();
        try {
            fd = new File(filePath).getParentFile();
//            System.out.println(fd.getParentFile());
            desktop.open(fd);
        } catch (IllegalArgumentException | IOException iae) {
//            System.out.println("File Not Found");
            String message = "File Not Found";
            errorOrSuccessMessage(0, message, null);
        } finally {
            ((Node)(event.getSource())).getScene().getWindow().hide();
        }
    }


    //    GET THE APPROPRIATE ALERT OR MESSAGE
    void errorOrSuccessMessage(int n, String message, Exception e) {
        errorSuccess errorSuccessObj = new errorSuccess();
        errorSuccessObj.getMessage(n, message, e, null);
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
