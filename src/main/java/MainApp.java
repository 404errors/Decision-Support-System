import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;


public class MainApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("/fxml/MainApp.fxml"));

        Scene scene = new Scene(root);
        stage.setTitle("Decision Support System");
        stage.setScene(scene);
        stage.show();
        stage.setResizable(false);
        stage.sizeToScene();
        stage.getIcons().add(new Image("icons/dss.png"));
    }

}
