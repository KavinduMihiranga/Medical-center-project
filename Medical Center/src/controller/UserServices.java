package controller;

import model.User;

import java.sql.SQLException;
import java.util.ArrayList;

public interface UserServices {
    boolean saveUser(User u) throws SQLException, ClassNotFoundException;
    boolean updateUser(User u) throws SQLException, ClassNotFoundException;
    boolean deleteUser(String id) throws SQLException, ClassNotFoundException;
    User getUser(String id) throws SQLException, ClassNotFoundException;
    ArrayList<User> getAllUsers() throws SQLException, ClassNotFoundException;

}
