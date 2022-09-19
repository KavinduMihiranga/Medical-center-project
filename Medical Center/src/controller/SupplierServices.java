package controller;


import model.Supplier;

import java.sql.SQLException;
import java.util.ArrayList;

public interface SupplierServices {
    public boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException;

    public boolean updateSupplier(Supplier s) throws SQLException, ClassNotFoundException;

    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException;

    public Supplier getSupplier(String Id) throws SQLException, ClassNotFoundException;

    public ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException;
}
