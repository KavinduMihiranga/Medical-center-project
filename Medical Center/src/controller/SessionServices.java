package controller;

import model.Doctor;
import model.Session;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SessionServices {
    public boolean saveSession(Session s) throws SQLException, ClassNotFoundException;
    public boolean updateSession(Session s) throws SQLException, ClassNotFoundException;
    public boolean deleteSession(String sId) throws SQLException, ClassNotFoundException;
    public Session getSession(String sId) throws SQLException, ClassNotFoundException;
    public ArrayList<Session> getAllSession() throws SQLException, ClassNotFoundException;
}
