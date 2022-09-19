package controller;

import model.Doctor;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DoctorServices {
    public boolean saveDoctor(Doctor d) throws SQLException, ClassNotFoundException;
    public boolean updateDoctor(Doctor d) throws SQLException, ClassNotFoundException;
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException;
    public Doctor getDoctor(String Id) throws SQLException, ClassNotFoundException;
    public ArrayList<Doctor> getAllDoctor() throws SQLException, ClassNotFoundException;


}
