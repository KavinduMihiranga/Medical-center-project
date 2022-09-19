package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import util.LoadSessionEvent;
import view.tm.DoctorSessionsTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class DoctorSessionsController implements Initializable {
    public TableColumn colSessionId;
    public TableColumn colSessionDate;
    public TableColumn colStartTime;
    public TableColumn colActivePatients;
    public Label lblDoctor;
    public TableColumn colAvailability;
    public TableView<DoctorSessionsTM> tblDoctorSessions;

    public void setLoadSessionEvent(LoadSessionEvent loadSessionEvent) {
        this.loadSessionEvent = loadSessionEvent;
    }

    private LoadSessionEvent loadSessionEvent;

    public void loadDoctor(String text) {
        String[] doctorDetails=text.split("-");
        String doctorId=doctorDetails[0];
        lblDoctor.setText(doctorDetails[1]);

        loadSessionTable(doctorId);
    }

    private void loadSessionTable(String doctorId) {
        ArrayList<DoctorSessionsTM> doctorSessions=null;




        try {
            doctorSessions = new SessionController().getAllSessionsTM(doctorId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        for (DoctorSessionsTM doctorSessionsTM:doctorSessions) {
            doctorSessionsTM.setAvailability(new Button("Available"));
        }

        ObservableList<DoctorSessionsTM> doctorSessionsTMS= FXCollections.observableArrayList(doctorSessions);
        tblDoctorSessions.setItems(doctorSessionsTMS);

        for (DoctorSessionsTM doctorSessionsTM : tblDoctorSessions.getItems()) {
            doctorSessionsTM.getAvailability().setOnAction(new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {

                    loadSessionEvent.loadSession(doctorSessionsTM.getSessionId());

                    ((Stage)(((Button)event.getSource()).getScene().getWindow())).close();
                }
            });
        }
    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colSessionId.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colSessionDate.setCellValueFactory(new PropertyValueFactory<>("date"));
        colStartTime.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        colActivePatients.setCellValueFactory(new PropertyValueFactory<>("activePatients"));
        colAvailability.setCellValueFactory(new PropertyValueFactory<>("availability"));


    }

}
