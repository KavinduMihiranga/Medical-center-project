package controller;


import model.Test;


import java.sql.SQLException;
import java.util.ArrayList;

public interface TestServices {
    public boolean saveTest(Test t) throws SQLException, ClassNotFoundException;
    public boolean updateTest(Test t) throws SQLException, ClassNotFoundException;
    public boolean deleteTest(String id) throws SQLException, ClassNotFoundException;
    public Test getTest(String Id) throws SQLException, ClassNotFoundException;
    public ArrayList<Test> getAllTest() throws SQLException, ClassNotFoundException;

}
