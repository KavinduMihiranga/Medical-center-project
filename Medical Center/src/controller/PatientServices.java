package controller;


import model.Patient;


import java.sql.SQLException;
import java.util.ArrayList;

public interface PatientServices {
    public boolean savePatient(Patient p) throws SQLException, ClassNotFoundException;
    public boolean updatePatient(Patient d) throws SQLException, ClassNotFoundException;
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException;
    public Patient getPatient(String Id) throws SQLException, ClassNotFoundException;
    public ArrayList<Patient> getAllPatient() throws SQLException, ClassNotFoundException;
}
