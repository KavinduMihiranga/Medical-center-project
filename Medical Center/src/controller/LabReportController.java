package controller;

import db.DbConnection;
import model.LabReport;
import model.TestLabReport;
import view.tm.LabReportTM;
import view.tm.TestReportTM;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class LabReportController implements LabReportServices {
    public String getLabReportId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT LabReport_Id FROM LabReport ORDER BY LabReport_Id DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "L_000" + tempId;
            } else if (tempId <= 99) {
                return "L_00" + tempId;
            } else if (tempId <= 999) {
                return "L_0" + tempId;
            } else {
                return "L_" + tempId;
            }
        } else {
            return "L_0001";
        }
    }

    @Override
    public boolean saveLabReport(LabReport l) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO LabReport VALUES(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, l.getLabReportId());
        statement.setObject(2, l.getTestedDate());
        statement.setObject(3, l.getAmount());
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean updateLabReport(LabReport l) throws SQLException, ClassNotFoundException {
        return false;
    }


    @Override
    public boolean deleteLabReport(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM LabReport WHERE getLabReportId='" + id + "'")
                .executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public LabReport getLabReport(String Id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM LabReport WHERE getLabReportId=?");
        statement.setObject(1, Id);

//        ResultSet resultSet=statement.executeQuery();
//        if(resultSet.next()){
//            return new LabReport(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    ((Date) resultSet.getObject(4)).toLocalDate(),
//                    ((Date) resultSet.getObject(4)).toLocalDate()
//
//
//            );
//        }else {
        return null;
//        }
    }

    @Override
    public ArrayList<LabReport> getAllLabReport() throws SQLException, ClassNotFoundException {
        ArrayList<LabReport> labReports = new ArrayList<>();
//        Connection connection=DbConnection.getInstance().getConnection();
//        String query="SELECT * FROM LabReport";
//        PreparedStatement statement=connection.prepareStatement(query);
//        ResultSet resultSet=statement.executeQuery();
//        while (resultSet.next()){
//            labReports.add(new LabReport(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    ((Date) resultSet.getObject(4)).toLocalDate(),
//                    ((Date) resultSet.getObject(4)).toLocalDate()
//
//
//            ));
//
//        }
//        return labReports;
        return null;
    }

    public ArrayList<LabReportTM> getAllLabReportTM() throws SQLException, ClassNotFoundException {
        ArrayList<LabReportTM> labReportTMS = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM LabReport";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<String> LabReportArrayList = new ArrayList<>();
        while (resultSet.next()) {
            labReportTMS.add(new LabReportTM(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    ((Date) resultSet.getObject(3)).toLocalDate(),
                    ((Date) resultSet.getObject(4)).toLocalDate()
//                     resultSet.getDouble(5)


            ));
            LabReportArrayList.add(resultSet.getString(1));

//        }
//        for (int i = 0; i < LabReportArrayList.size(); i++) {
//            connection = DbConnection.getInstance().getConnection();
//
//            String query1 = "SELECT TestType FROM Test WHERE Test_Fee=?";
//            PreparedStatement statement1 = connection.prepareStatement(query1);
//            statement1.setObject(1,labReportTMS.get(i));
//            ResultSet resultSet1 = statement1.executeQuery();
//
//            while (resultSet1.next()) {
//                labReportTMS.get(i).setTestFee(resultSet1.getDouble(3));
//
//
//            }
        }
        return labReportTMS;
//        return null;
    }


    public TestReportTM getTestReportTM(String testId, String testCategoryId, LocalDateTime testDateTime) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT t.Test_Type,t.Test_Fee,t.Test_Duration, c.TestCategory FROM Test t  INNER JOIN TestCategory c ON t.TestCategoryId =c.TestCategoryId WHERE Test_Id=?" ;
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1,testId);
        ResultSet resultSet = statement.executeQuery();
        String testType = null;
        double testFee = 0.0;
        int testDuration = 0;
        while (resultSet.next()) {
            testType =resultSet.getString(4)+"-"+resultSet.getString(1);
            testFee = resultSet.getDouble(2);
            testDuration = resultSet.getInt(3);
        }
        LocalDateTime dateTime = testDateTime.plusHours(testDuration);

        TestReportTM testReportTM = new TestReportTM(testType,testDateTime,dateTime,testFee);

        return testReportTM;
    }

    public boolean saveTestLabReport(TestLabReport testLabReport) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO TestLabReport VALUES(?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        String currentId=getTestLabReportId();
        statement.setObject(1,currentId);
        statement.setObject(2, testLabReport.getTestId());
        statement.setObject(3, testLabReport.getLabReportId());
        statement.setObject(4, testLabReport.getReceiveDate());
        return statement.executeUpdate() > 0;
    }

//    private String getTestLabReportId(TestLabReport testLabReport) {
//        int tempId = Integer.parseInt(testLabReport.getTestLabReportId().split("_")[1]);
//
//        if (testLabReports.isEmpty()) {
//            return "R_0001";
//        } else {
//            tempId = tempId + 1;
//            if (tempId < 9) {
//                return "R_000" + tempId;
//            } else if (tempId < 99) {
//                return "R_00" + tempId;
//            } else if (tempId < 999) {
//                return "R_0" + tempId;
//            } else {
//                return "R_" + tempId;
//            }
//        }
//    }


        public String getTestLabReportId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT TestLabReportId FROM TestLabReport ORDER BY TestLabReportId DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "R_000" + tempId;
            } else if (tempId <= 99) {
                return "R_00" + tempId;
            } else if (tempId <= 999) {
                return "R_0" + tempId;
            } else {
                return "R_" + tempId;
            }
        } else {
            return "R_0001";
        }
    }
}
