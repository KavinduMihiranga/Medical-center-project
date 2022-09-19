package controller;

import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SplashScreenController implements Initializable {
    public StackPane rootPane;
    public Pane prgProgress;
    public Label lblPercentage;
    public Label lblProgressText;
    public ProgressIndicator prgIndicator;


    @Override
    public void initialize(URL location, ResourceBundle resources) {
      new SplashScreen().start();
        lblProgressText.setText("loading.....");


    }
    class SplashScreen extends Thread{
        public void run() {
            try {
                Thread.sleep(5000);

                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Parent root = null;

                        try {
                            root=FXMLLoader.load(getClass().getResource("../view/DashBoard.fxml"));
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        Scene scene = new Scene(root);
                        Stage stage = new Stage();
                        stage.setScene(scene);
                        stage.show();

                        rootPane.getScene().getWindow().hide();

                    }
                });
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            ProgressIndicator indicator = new ProgressIndicator();

        }
    }
}
