package messages;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class errorSuccess {

    public void successMessage(String message, String link) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("success.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 452, 217);
            Stage stage = new Stage();
            stage.setTitle("SUCCESS");
            stage.setScene(scene);
            stage.getIcons().add(new Image(successController.class.getResourceAsStream("../media/logo.jpg")));
            stage.initStyle(StageStyle.UNDECORATED);

            successController controller = fxmlLoader.<successController>getController();
            controller.setLabelText(message);
            controller.setFilePath(link);

            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void errorMessage(String message) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("error.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 452, 255);
            Stage stage = new Stage();
            stage.setTitle("ERROR");
            stage.setScene(scene);
            stage.getIcons().add(new Image(errorController.class.getResourceAsStream("../media/logo.jpg")));
            stage.initStyle(StageStyle.UNDECORATED);

            errorController controller = fxmlLoader.<errorController>getController();
            controller.setLabelText(message);

            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void getMessage (int n, String message, Exception e, String link) {
        if (n == 0) {
            errorMessage(message);
        } else if (n == 1) {
            successMessage(message, link);
        } else if (n == 2) {
            convertExceptionToString(e);
        }
    }

    public void convertExceptionToString(Exception ex) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        ex.printStackTrace(pw);
        String sStackTrace = sw.toString(); // stack trace as a string
        errorMessage(sStackTrace);
    }


}
