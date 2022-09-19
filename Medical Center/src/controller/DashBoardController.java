package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;
import model.Doctor;
import model.Login;
import model.User;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.Date;

public class DashBoardController {
    public Label lblDash_Date;
    public Label lblDash_Time;
    public GridPane contextMain;
    public JFXTextField txtUsername;
    public JFXPasswordField txtPassword;
    public JFXButton btnLogin;
    public Label lblLoginMessage;



    public void initialize(){
    loadDateAndTime();
}
    public void login(ActionEvent actionEvent) throws IOException, SQLException, ClassNotFoundException {
        String userName = txtUsername.getText();
        String password = txtPassword.getText();

        User user=new UserController().getUserForLogin(userName,password);
        if(user!=null) {
            if (user.getAccessLevel().equalsIgnoreCase("Admin")) {
                URL resource = getClass().getResource("../view/AdminForm.fxml");
                Parent load = FXMLLoader.load(resource);
                contextMain.getChildren().clear();
                contextMain.getChildren().add(load);

            } else if (user.getAccessLevel().equalsIgnoreCase("Doctor")) {
                Doctor doctor = new DoctorController().getDoctorByUserId(user.getUserId());

//                System.out.println(doctor);
                Login login = new Login();
                login.setLoginId(new LoginController().getLoginId());
                login.setD_Id(doctor.getD_Id());
                login.setUserId(user.getUserId());
                login.setLoginStatus("Active");
                if(new LoginController().saveLogin(login)){

                }
                FXMLLoader loader=new FXMLLoader(this.getClass().getResource("../view/DoctorForm.fxml"));
                Parent load = loader.load();

                DoctorFormController controller=loader.<DoctorFormController>getController();
                controller.lblDoctor.setText(doctor.getD_Id()+"-"+doctor.getD_Name()+"-"+doctor.getD_Speciality()+"-"+doctor.getD_MBBSNo());
                controller.loadData(doctor.getD_Id());
                contextMain.getChildren().clear();
                contextMain.getChildren().add(load);

            } else if (user.getAccessLevel().equalsIgnoreCase("Reception")) {
                URL resource = getClass().getResource("../view/ReceptionForm.fxml");
                Parent load = FXMLLoader.load(resource);
                contextMain.getChildren().clear();
                contextMain.getChildren().add(load);

            } else if (user.getAccessLevel().equalsIgnoreCase("Pharmacy")) {
                URL resource = getClass().getResource("../view/PharmacyForm.fxml");
                Parent load = FXMLLoader.load(resource);
                contextMain.getChildren().clear();
                contextMain.getChildren().add(load);

            } else if (user.getAccessLevel().equalsIgnoreCase("Laboratory")) {
                URL resource = getClass().getResource("../view/LabForm.fxml");
                Parent load = FXMLLoader.load(resource);
                contextMain.getChildren().clear();
                contextMain.getChildren().add(load);
            }
        }else {
            lblLoginMessage.setText("UserName or Password does not match");
            txtUsername.clear();
            txtPassword.clear();
        }
    }
    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDash_Date.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblDash_Time.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

}