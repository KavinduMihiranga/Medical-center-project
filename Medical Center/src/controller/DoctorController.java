package controller;

import db.DbConnection;
import model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class DoctorController implements DoctorServices{

    public String getDoctorId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                "SELECT D_Id FROM Doctor ORDER BY D_Id DESC LIMIT 1"
        ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "D_000" + tempId;
            } else if (tempId <= 99) {
                return "D_00" + tempId;
            } else if (tempId <= 999) {
                return "D_0" + tempId;
            } else {
                return "D_" + tempId;
            }
        }else {
            return "D_0001";
        }
    }
    @Override
    public boolean saveDoctor(Doctor d) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Doctor VALUES(?,?,?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setObject(1,d.getD_Id());
        statement.setObject(2,d.getD_Name());
        statement.setObject(3,d.getD_Address());
        statement.setObject(4,d.getD_Speciality());
        statement.setObject(5,d.getD_PhoneNo());
        statement.setObject(6,d.getD_MBBSNo());
        statement.setObject(7,d.getD_Charge());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateDoctor(Doctor d) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE Doctor SET D_Name=?,D_Address=?,D_Speciality=?,D_PhoneNo=?,D_MBBSNo=?,D_Charge=? WHERE D_Id=?");

        statement.setObject(1,d.getD_Name());
        statement.setObject(2,d.getD_Address());
        statement.setObject(3,d.getD_Speciality());
        statement.setObject(4,d.getD_PhoneNo());
        statement.setObject(5,d.getD_MBBSNo());
        statement.setObject(6,d.getD_Charge());
        statement.setObject(7,d.getD_Id());
        return statement.executeUpdate()>0;

    }

    @Override
    public boolean deleteDoctor(String id) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Doctor WHERE D_Id='"+id+"'")
            .executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Doctor getDoctor(String Id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Doctor WHERE Doctor=?");
        statement.setObject(1,Id);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)
            );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Doctor> getAllDoctor() throws SQLException, ClassNotFoundException {
        ArrayList<Doctor>doctors=new ArrayList<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Doctor";
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            doctors.add(new Doctor(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)

            ));

        }
        return doctors;
    }

    public ArrayList<String> getAllSpecialities() throws SQLException, ClassNotFoundException {
        ArrayList<String>specialities=new ArrayList<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT DISTINCT(D_Speciality) FROM Doctor";
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            specialities.add(
                    resultSet.getString(1)


            );

        }
        return specialities;
    }

    public LinkedHashMap<String,String> getDoctorsBySpeciality(String newValue) throws SQLException, ClassNotFoundException {
       LinkedHashMap<String,String> doctors = new LinkedHashMap<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT D_Id, D_Name FROM Doctor WHERE D_Speciality=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,newValue);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            doctors.put(
                    resultSet.getString(1),
                    resultSet.getString(2)


            );

        }
        return doctors;
    }

    public double getDoctorChargeBySessionId(String sessionId) throws SQLException, ClassNotFoundException {
//        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT D_Charge FROM Doctor d INNER JOIN Session s ON d.D_Id=s.D_Id INNER JOIN Appointment a ON s.Session_Id=a.Session_Id WHERE a.Session_Id=?");
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement(
                "SELECT d.D_Charge FROM Appointment a " +
                "INNER JOIN Session s ON a.Session_Id=s.Session_Id " +
                "INNER JOIN Doctor d ON s.D_Id= d.D_Id WHERE a.Session_Id=?"
        );
        statement.setObject(1,sessionId);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()) {
            return resultSet.getDouble(1);
        }
        return 0.0;
    }

    public Doctor getDoctorByUserId(String userId) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement(
                "SELECT d.D_Id,d.D_Name,d.D_Address,d.D_Speciality,d.D_PhoneNo,d.D_MBBSNo,d.D_Charge FROM User u INNER JOIN Login l ON u.User_Id=l.User_Id INNER JOIN Doctor d ON d.D_Id= l.D_Id WHERE u.User_Id=?"
        );
        statement.setObject(1,userId);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()) {
            return new Doctor(resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getDouble(7)

            );

        }
        return null;

    }


}
