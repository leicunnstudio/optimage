package window;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class windowMain extends Application {

    @Override
    public void start(Stage windowMainStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("window.fxml"));
        windowMainStage.setTitle("OptImage");
        windowMainStage.setScene(new Scene(root, 688, 509));
        windowMainStage.getIcons().add(new Image(Controller.class.getResourceAsStream("../media/cancel.png")));
        windowMainStage.initStyle(StageStyle.UNDECORATED);
        windowMainStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


}
