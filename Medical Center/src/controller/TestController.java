package controller;

import db.DbConnection;
import model.Doctor;
import model.Test;
import model.TestCategory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TestController implements TestServices {
    public String getTestId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT Test_Id FROM Test ORDER BY Test_Id DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "T_000" + tempId;
            } else if (tempId <= 99) {
                return "T_00" + tempId;
            } else if (tempId <= 999) {
                return "T_0" + tempId;
            } else {
                return "T_" + tempId;
            }
        } else {
            return "T_0001";
        }
    }

    @Override
    public boolean saveTest(Test t) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Test VALUES(?,?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setObject(1,t.getTestId());
        statement.setObject(2,t.getTestCategoryId());
        statement.setObject(3,t.getTestType());
        statement.setObject(4,t.getTestFee());
        statement.setObject(5,t.getDuration());

        return statement.executeUpdate()>0;
    }

    @Override
    public boolean updateTest(Test t) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE Test SET TestCategoryId=?,Test_Type=?,Test_Fee=?,Test_Duration WHERE Test_Id=?");


        statement.setObject(1,t.getTestCategoryId());
        statement.setObject(2,t.getTestType());
        statement.setObject(3,t.getTestFee());
        statement.setObject(4,t.getDuration());
        statement.setObject(5,t.getTestId());

        return statement.executeUpdate()>0;
    }

    @Override
    public boolean deleteTest(String id) throws SQLException, ClassNotFoundException {
        if(DbConnection.getInstance().getConnection().prepareStatement( "DELETE FROM Test WHERE Test_Id='"+id+"'")
                .executeUpdate()>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public Test getTest(String Id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement=DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Test WHERE Test_Id=?");
        statement.setObject(1,Id);

        ResultSet resultSet=statement.executeQuery();
        if(resultSet.next()){
            return new Test(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)

            );
        }else {
            return null;
        }
    }

    @Override
    public ArrayList<Test> getAllTest() throws SQLException, ClassNotFoundException {
        ArrayList<Test>tests=new ArrayList<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT Test_Id,Test.TestCategoryId,Test_Type,Test_Fee,Test_Duration, TestCategory.TestCategory FROM Test JOIN TestCategory ON Test.TestCategoryId=TestCategory.TestCategoryId";
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            tests.add(new Test(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(6)+"-"+resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)


            ));

        }
        return tests;
    }


    public ArrayList<TestCategory> getAllTestCategory() throws SQLException, ClassNotFoundException {
        ArrayList<TestCategory>testCategories=new ArrayList<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT * FROM TestCategory";
        PreparedStatement statement=connection.prepareStatement(query);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            testCategories.add(new TestCategory(
                    resultSet.getString(1),
                    resultSet.getString(2)

            ));

        }
        return testCategories;

    }

    public String getTestCategoryId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT TestCategoryId FROM TestCategory ORDER BY TestCategoryId DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "C_000" + tempId;
            } else if (tempId <= 99) {
                return "C_00" + tempId;
            } else if (tempId <= 999) {
                return "C_0" + tempId;
            } else {
                return "C_" + tempId;
            }
        } else {
            return "C_0001";
        }
    }

    public boolean saveTestCategory(TestCategory testCategory) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO TestCategory VALUES(?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setObject(1,testCategory.getTestCategoryId());
        statement.setObject(2,testCategory.getTestCategory());
        return statement.executeUpdate()>0;
    }

    public ArrayList<Test> getAllTestByCategory(String newValue) throws SQLException, ClassNotFoundException {
        ArrayList<Test>tests=new ArrayList<>();
        Connection connection=DbConnection.getInstance().getConnection();
        String query="SELECT * FROM Test WHERE TestCategoryId=?";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setString(1,newValue);
        ResultSet resultSet=statement.executeQuery();
        while (resultSet.next()){
            tests.add(new Test(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4),
                    resultSet.getInt(5)

            ));

        }
        return tests;
    }
}
