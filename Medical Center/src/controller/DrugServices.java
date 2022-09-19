package controller;

import model.Drug;

import java.sql.SQLException;
import java.util.ArrayList;

public interface DrugServices {
    public boolean saveDrug(Drug drug) throws SQLException, ClassNotFoundException;
    public boolean updateDrug(Drug drug) throws SQLException, ClassNotFoundException;
    public boolean deleteDrug(String id) throws SQLException, ClassNotFoundException;
    public Drug getDrug(String Id) throws SQLException, ClassNotFoundException;
    public ArrayList<Drug> getAllDrug() throws SQLException, ClassNotFoundException;
}
