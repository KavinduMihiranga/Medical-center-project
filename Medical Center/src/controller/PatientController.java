package controller;

import db.DbConnection;
import model.Doctor;
import model.Patient;

import java.sql.*;
import java.util.ArrayList;

public class PatientController implements PatientServices{

    public String getPatientId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT P_Id FROM Patient ORDER BY P_Id DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "P_000" + tempId;
            } else if (tempId <= 99) {
                return "P_00" + tempId;
            } else if (tempId <= 999) {
                return "P_0" + tempId;
            } else {
                return "P_" + tempId;
            }
        }else {
            return "P_0001";
        }
    }

    @Override
    public boolean savePatient(Patient p) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Patient VALUES(?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1,p.getP_Id());
        statement.setObject(2,p.getP_Name());
        statement.setObject(3,p.getP_Nic());
        statement.setObject(4,p.getP_Dob());
        statement.setObject(5,p.getP_Gender());
        statement.setObject(6,p.getP_Address());
        statement.setObject(7,p.getP_PhoneNo());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updatePatient(Patient p) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE Patient SET P_Name=?,P_Nic=?,P_Dob=?,P_Gender=?,P_Address=?,P_PhoneNo=? WHERE P_Id=?");


        statement.setObject(1,p.getP_Name());
        statement.setObject(2,p.getP_Nic());
        statement.setObject(3,p.getP_Dob());
        statement.setObject(4,p.getP_Gender());
        statement.setObject(5,p.getP_Address());
        statement.setObject(6,p.getP_PhoneNo());
        statement.setObject(7,p.getP_Id());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean deletePatient(String id) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Patient WHERE P_Id='"+id+"'")
                .executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Patient getPatient(String Id) throws SQLException, ClassNotFoundException {

        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patient WHERE P_Id=?");
        statement.setObject(1,Id);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    ((Date) resultSet.getObject(4)).toLocalDate(),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Patient> getAllPatient() throws SQLException, ClassNotFoundException {
        ArrayList<Patient> patients = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM Patient";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            patients.add(new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    ((Date) resultSet.getObject(4)).toLocalDate(),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)

            ));
        }
        return patients;
    }

    public Patient getPatientByNIC(String nic) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patient WHERE P_Nic=?");
        statement.setObject(1,nic);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    ((Date) resultSet.getObject(4)).toLocalDate(),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }else {
            return null;
        }
    }

    public Patient getPatientByTelNo(String telNo) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Patient WHERE P_PhoneNo=?");
        statement.setObject(1,telNo);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new Patient(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    ((Date) resultSet.getObject(4)).toLocalDate(),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7)
            );
        }else {
            return null;
        }
    }
}
