package controller;

import javafx.scene.control.Label;
import model.Patient;

import java.text.SimpleDateFormat;
import java.time.Duration;
import java.time.LocalDate;

public class PatientDetailsController {
    public Label lblPatientId;
    public Label lblPatientName;
    public Label lblPatientNIC;
    public Label lblPatientGender;
    public Label lblPatientAddress;
    public Label lblPatientAge;
    public Label lblPatientDob;
    public Label lblPatientTelNo;

    long difference;


    public void loadPatientDetail(Patient patient){
        lblPatientId.setText(patient.getP_Id());
        lblPatientName.setText(patient.getP_Name());
        lblPatientNIC.setText(patient.getP_Nic());
        lblPatientGender.setText(patient.getP_Gender());
        lblPatientAddress.setText(patient.getP_Address());
        lblPatientAge.setText(String.valueOf(difference));
        lblPatientTelNo.setText(patient.getP_PhoneNo());
        lblPatientDob.setText(String.valueOf(patient.getP_Dob()));


    }
    public long calculatePatientAge(){
        LocalDate patientAge= LocalDate.ofEpochDay(Long.parseLong(lblPatientAge.getText()));
        LocalDate today= LocalDate.now();
        difference= Duration.between(today,patientAge).toDays();
//        System.out.println(difference);
        return difference;
    }
}
