package controller;

import db.DbConnection;
import model.Drug;
import model.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class DrugController implements DrugServices{
    public String getDrugId() throws SQLException, ClassNotFoundException {
    ResultSet resultSet = DbConnection.getInstance()
            .getConnection().prepareStatement(
                    "SELECT Drug_Id FROM Drug ORDER BY Drug_Id DESC LIMIT 1"
            ).executeQuery();
        if (resultSet.next()) {
        int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
        tempId = tempId + 1;
        if (tempId <= 9) {
            return "M_000" + tempId;
        } else if (tempId <= 99) {
            return "M_00" + tempId;
        } else if (tempId <= 999) {
            return "M_0" + tempId;
        } else {
            return "M_" + tempId;
        }
    }else {
            return "M_0001";
        }
    }
    @Override
    public boolean saveDrug(Drug drug) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Drug VALUES(?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setObject(1,drug.getDrugId());
        statement.setObject(2,drug.getDrugName());
        statement.setObject(3,drug.getTradeName());

        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateDrug(Drug drug) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE Drug SET Drug_Name=?,Drug_TradeName=? WHERE Drug_Id=?");


        statement.setObject(1,drug.getDrugName());
        statement.setObject(2,drug.getTradeName());
        statement.setObject(3,drug.getDrugId());
        return statement.executeUpdate()>0;
    }

    @Override
    public boolean deleteDrug(String id) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Drug WHERE Drug_Id='"+id+"'")
                .executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Drug getDrug(String Id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Drug WHERE Drug_Id=?");
        statement.setObject(1,Id);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new Drug(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)

            );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Drug> getAllDrug() throws SQLException, ClassNotFoundException {
        ArrayList<Drug>drugs=new ArrayList<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Drug";
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            drugs.add(new Drug(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3)


            ));

        }
        return drugs;
    }
}
