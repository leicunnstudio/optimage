package messages;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class errorSuccess {

    public void successMessage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("success.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 452, 217);
            Stage stage = new Stage();
            stage.setTitle("SUCCESS");
            stage.setScene(scene);
            stage.getIcons().add(new Image(successController.class.getResourceAsStream("../media/logo.jpg")));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }

    public void errorMessage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader();
            fxmlLoader.setLocation(getClass().getResource("error.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 452, 217);
            Stage stage = new Stage();
            stage.setTitle("ERROR");
            stage.setScene(scene);
            stage.getIcons().add(new Image(errorController.class.getResourceAsStream("../media/logo.jpg")));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            Logger logger = Logger.getLogger(getClass().getName());
            logger.log(Level.SEVERE, "Failed to create new Window.", e);
        }
    }


}
