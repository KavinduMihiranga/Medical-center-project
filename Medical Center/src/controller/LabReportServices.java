package controller;


import model.LabReport;


import java.sql.SQLException;
import java.util.ArrayList;

public interface LabReportServices {
    public boolean saveLabReport(LabReport l) throws SQLException, ClassNotFoundException;
    public boolean updateLabReport(LabReport l) throws SQLException, ClassNotFoundException;
    public boolean deleteLabReport(String id) throws SQLException, ClassNotFoundException;
    public LabReport getLabReport(String Id) throws SQLException, ClassNotFoundException;
    public ArrayList<LabReport> getAllLabReport() throws SQLException, ClassNotFoundException;
}
