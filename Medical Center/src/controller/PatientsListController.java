package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import view.tm.PatientListTM;

import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class PatientsListController implements Initializable {
    public Label lblDoctorSession;
    public TableColumn colAppointmentNo;
    public TableColumn colPatientName;
    public TableView<PatientListTM> tblPatientList;

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        colAppointmentNo.setCellValueFactory(new PropertyValueFactory<>("appointmentNo"));
        colPatientName.setCellValueFactory(new PropertyValueFactory<>("patientName"));


    }

    public void loadIds(String session_id, String d_id) throws SQLException, ClassNotFoundException {

        ArrayList<PatientListTM> patientListTMS = new SessionController().getPatientListBySessionId(session_id);
        ObservableList<PatientListTM>observableList = FXCollections.observableArrayList(patientListTMS);

        tblPatientList.setItems(observableList);
    }
}
