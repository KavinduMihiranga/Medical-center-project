import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;

public class AppInitializer extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("view/SplashScreen.fxml"));
        Scene scene = new Scene(root);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.setScene(scene);
        primaryStage.show();


//        URL resource = getClass().getResource("view/DashBoard.fxml");
//        Parent load = FXMLLoader.load(resource);
//        Scene scene=new Scene(load);
//        primaryStage.setScene(scene);
//        primaryStage.setTitle("Medical Center");
//        primaryStage.show();
    }
}
