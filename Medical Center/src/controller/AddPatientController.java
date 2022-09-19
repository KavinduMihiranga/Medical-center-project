package controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXDatePicker;
import com.jfoenix.controls.JFXTextField;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import model.Patient;
import util.LoadPatientEvent;
import util.Validation;

import java.net.URL;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AddPatientController implements Initializable {
    public JFXTextField txtPatientName;
    public Label lblPatientId;
    public JFXTextField txtPatientNIC;
    public JFXDatePicker dpPatientDob;
    public JFXComboBox cmbPatientGender;
    public JFXTextField txtPatientAddress;
    public JFXTextField txtPatientTelNo;
    public JFXButton btnAddPatient;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap<>();
    Pattern patientNamePattern = Pattern.compile("^[A-z\\s]{1,40}$");
    Pattern patientNIC = Pattern.compile("^[0-9]{9,15}[V]?$");
    Pattern patientAddress = Pattern.compile("^[A-z0-9\\.\\-\\s\\,]{1,50}$");
    Pattern patientPhoneNo = Pattern.compile("^[0][0-9]{9}$");
    private LoadPatientEvent patientEvent;

    public void setPatientEvent(LoadPatientEvent patientEvent) {
        this.patientEvent = patientEvent;
    }

    public void addPatient(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String patientId = lblPatientId.getText();
        String patientName = txtPatientName.getText();
        String patientNIC = txtPatientNIC.getText();
        LocalDate patientDob = dpPatientDob.getValue();
        String patientGender = (String) cmbPatientGender.getSelectionModel().getSelectedItem();
        String patientAddress = txtPatientAddress.getText();
        String patientTelNo = txtPatientTelNo.getText();

        Patient patient = new Patient(patientId, patientName, patientNIC, patientDob, patientGender, patientAddress, patientTelNo);
        if (new PatientController().savePatient(patient)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Patient Successfully...").show();

            patientEvent.loadPatient(patient);

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }

    }

    private void storeValidations() {
        map.put(txtPatientName, patientNamePattern);
        map.put(txtPatientNIC, patientNIC);
        map.put(txtPatientAddress, patientAddress);
        map.put(txtPatientTelNo, patientPhoneNo);

    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        btnAddPatient.setDisable(true);
        storeValidations();

        ObservableList<String> observableList = FXCollections.observableArrayList("Male", "Female");
        cmbPatientGender.setItems(observableList);

        try {
            lblPatientId.setText(new PatientController().getPatientId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    public void patientAddJfxButton_KeyReleased(KeyEvent keyEvent) {
        Object response = Validation.validate(map, btnAddPatient);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
//                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }
}
