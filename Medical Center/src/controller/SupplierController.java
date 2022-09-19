package controller;

import db.DbConnection;
import model.Patient;
import model.Supplier;

import java.sql.*;
import java.util.ArrayList;

public class SupplierController implements SupplierServices{
    public String getSupplierId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT Supplier_Id FROM Supplier ORDER BY Supplier_Id DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "S_000" + tempId;
            } else if (tempId <= 99) {
                return "S_00" + tempId;
            } else if (tempId <= 999) {
                return "S_0" + tempId;
            } else {
                return "S_" + tempId;
            }
        }else {
            return "S_0001";
        }
    }


    @Override
    public boolean saveSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Supplier VALUES(?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1,s.getSupplierId());
        statement.setObject(2,s.getSupplierName());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateSupplier(Supplier s) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE Supplier SET Supplier_Name=? WHERE Supplier_Id=?");


        statement.setObject(1,s.getSupplierName());
        statement.setObject(2,s.getSupplierId());

        return statement.executeUpdate()>0;
    }

    @Override
    public boolean deleteSupplier(String id) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Supplier WHERE Supplier_Id='"+id+"'")
                .executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Supplier getSupplier(String Id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Supplier WHERE Supplier_Id=?");
        statement.setObject(1,Id);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2)

            );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Supplier> getAllSupplier() throws SQLException, ClassNotFoundException {
        ArrayList<Supplier> suppliers = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM Supplier";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            suppliers.add(new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2)

            ));
        }
        return suppliers;
    }
}
