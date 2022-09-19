package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import model.Appointment;
import model.Patient;
import model.Session;
import util.LoadPatientEvent;
import util.LoadSessionEvent;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;

public class ReceptionFormController implements Initializable, LoadSessionEvent, LoadPatientEvent {
    public JFXTextField txtDoctorName;
    public JFXComboBox<String> cmbDoctors;
    public JFXComboBox<String> cmbDoctorSpeciality;
    public AnchorPane contextReception;
    public Label lblSessionId;
    public JFXTextField txtNIC;
    public JFXTextField txtTelephoneNo;
    public JFXButton btnSearchByNIC;
    public JFXButton btnSearchByTelNo;
    public JFXButton btnAddPatient;
    public Label lblPatient;


    public void searchForSession(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/DoctorSessions.fxml"));
        Parent parent = loader.load();
        DoctorSessionsController controller = loader.<DoctorSessionsController>getController();
        controller.loadDoctor(txtDoctorName.getText());
        controller.setLoadSessionEvent(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();


    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ArrayList<String> specialityList = null;
        try {
            specialityList = new DoctorController().getAllSpecialities();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<String> doctorSpecialityList = FXCollections.observableArrayList(specialityList);
        cmbDoctorSpeciality.setItems(doctorSpecialityList);

        cmbDoctorSpeciality.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            try {
                loadDoctors(newValue);
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        });

        cmbDoctors.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            txtDoctorName.setText(newValue);
        });

    }

    private void loadDoctors(String newValue) throws SQLException, ClassNotFoundException {
        LinkedHashMap<String, String> doctors = new DoctorController().getDoctorsBySpeciality(newValue);
        ObservableList<String> doctorNamesList = FXCollections.observableArrayList();
        doctors.forEach((key, value) -> doctorNamesList.add(key + "-" + value)
        );
        cmbDoctors.setItems(doctorNamesList);

    }


    @Override
    public void loadSession(String id) {

        lblSessionId.setText(id);
    }

    public void addAppointment(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String sessionId = lblSessionId.getText();
        String patient = lblPatient.getText();
        String patientId = patient.split("-")[0];

        int maxNoPatient = new SessionController().getMaxNoOfPatientBySessionId(sessionId);
        int appointmentNo = new SessionController().getCurrentAppointmentNo(sessionId);
//        System.out.println(appointmentNo);
        appointmentNo=appointmentNo+1;

        if (maxNoPatient >= appointmentNo) {

//            System.out.println(appointmentNo);
        } else {
            new Alert(Alert.AlertType.WARNING, "Appointments Full").show();
            return;
        }

//        System.out.println(sessionId);
        Session session = new SessionController().getSession(sessionId);
        if (session != null) {
            session.setCurrentAppointmentNo(appointmentNo);
            new SessionController().updateSession(session);
        }else {
//            System.out.println(session);
        }
        double appointmentFee = new DoctorController().getDoctorChargeBySessionId(session.getSession_Id());

        Appointment appointment = new Appointment();
        appointment.setAppointmentId(new AppointmentController().getAppointmentId());
        appointment.setPatientId(patientId);
        appointment.setSessionId(sessionId);
        appointment.setAppointmentNo(appointmentNo);
        appointment.setAppointmentFee(appointmentFee);



//        System.out.println(appointmentFee);
        if (new AppointmentController().saveAppointment(appointment)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Appointment Successfully...").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }


    }

    public void searchPatients(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, IOException {
        if (actionEvent.getSource() == btnSearchByNIC) {


            String nic = txtNIC.getText();
            Patient patient = new PatientController().getPatientByNIC(nic);
            if (patient == null) {
                btnAddPatient.setDisable(false);

            } else {
                openPatientDetail(patient);
                lblPatient.setText(patient.getP_Id() + "-" + patient.getP_Name());
            }
        }
        if (actionEvent.getSource() == btnSearchByTelNo) {


            String telNo = txtTelephoneNo.getText();
            Patient patient = new PatientController().getPatientByTelNo(telNo);
            if (patient == null) {
                btnAddPatient.setDisable(false);

            } else {
                openPatientDetail(patient);
                lblPatient.setText(patient.getP_Id() + "-" + patient.getP_Name());
            }
        }
    }

    private void openPatientDetail(Patient patient) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("../view/PatientDetails.fxml"));
        Parent parent = loader.load();
        PatientDetailsController controller = loader.<PatientDetailsController>getController();
        controller.loadPatientDetail(patient);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();

    }

    public void addPatient(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("/view/AddPatient.fxml"));
        Parent parent = loader.load();
        AddPatientController controller = loader.<AddPatientController>getController();
        controller.setPatientEvent(this);
        Stage stage = new Stage();
        stage.setScene(new Scene(parent));
        stage.show();
    }

    @Override
    public void loadPatient(Patient patient) {
        lblPatient.setText(patient.getP_Id() + "-" + patient.getP_Name());
    }
}
