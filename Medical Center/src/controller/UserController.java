package controller;

import db.DbConnection;
import model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserController implements UserServices{

    public String getUserId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT User_Id FROM User ORDER BY User_Id DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "U_000" + tempId;
            } else if (tempId <= 99) {
                return "U_00" + tempId;
            } else if (tempId <= 999) {
                return "U_0" + tempId;
            } else {
                return "U_" + tempId;
            }
        }else {
            return "U_0001";
        }
    }
    @Override
    public boolean saveUser(User u) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO User VALUES(?,?,?,?,?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setObject(1,u.getUserId());
//        statement.setObject(2,u.getDoctorId());
        statement.setObject(2,u.getFirstName());
        statement.setObject(3,u.getLastName());
        statement.setObject(4,u.getEmail());
        statement.setObject(5,u.getContact());
        statement.setObject(6,u.getUserName());
        statement.setObject(7,u.getPassword());
        statement.setObject(8,u.getAccessLevel());
        statement.setObject(9,u.getStatus());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateUser(User u) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE User SET F_Name=?,L_Name=?,Email=?,Contact=?,UserName=?,Password=?,AccessLevel=?,Status=? WHERE User_Id=?");

//        statement.setObject(1,u.getDoctorId());
        statement.setObject(1,u.getFirstName());
        statement.setObject(2,u.getLastName());
        statement.setObject(3,u.getEmail());
        statement.setObject(4,u.getContact());
        statement.setObject(5,u.getUserName());
        statement.setObject(6,u.getPassword());
        statement.setObject(7,u.getAccessLevel());
        statement.setObject(8,u.getStatus());
        statement.setObject(9,u.getUserId());


        return statement.executeUpdate()>0;

    }

    @Override
    public boolean deleteUser(String id) throws SQLException, ClassNotFoundException {
//        if(DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM User WHERE user_Id='"+id+"'")
//                .executeUpdate()>0){
//            return true;
//        }else {
//            return false;
//        }
        return false;

    }

    @Override
    public User getUser(String id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User WHERE User_Id=?");
        statement.setObject(1,id);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)
            );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException {
        ArrayList<User>users=new ArrayList<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT * FROM User";
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            users.add(new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)

            ));

        }
        return users;
    }

    public User getUserForLogin(String userName, String password) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM User WHERE UserName=? AND Password=?");
        statement.setObject(1,userName);
        statement.setObject(2,password);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new User(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6),
                    resultSet.getString(7),
                    resultSet.getString(8),
                    resultSet.getString(9)

            );
        }else {
            return null;
        }
    }
}
