package controller;

import com.jfoenix.controls.*;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.LabReport;
import model.Test;
import model.TestCategory;
import model.TestLabReport;
import view.tm.LabReportTM;
import view.tm.TestReportTM;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;

public class LabFormController {
    public JFXTextField txtTestType;
    public JFXTextField txtTestFee;
    public JFXButton btnUpdateTest;
    public JFXButton btnDeleteTest;
    public JFXButton btnAddTest;
    public Label lblTestId;
    public TableView<Test> tblTest;
    public TableColumn colTestId;
    public TableColumn colTestType;
    public TableColumn colTestFee;
    public JFXTextField txtLabReportTestFee;
    public JFXButton btnUpdateLabReport;
    public JFXButton btnDeleteReport;
    public JFXButton btnAddReport;
    public JFXDatePicker dpTestedDay;
    public JFXDatePicker dpReceivedDate;
    public JFXComboBox cmbTestType;
    public TableColumn colLabReportId;
    public TableColumn colLabReportTestType;
    public TableView<TestReportTM> tblLabReport;
    public TableColumn colTestedDay;
    public TableColumn colReceivedDay;
    public TableColumn colLabReportTestFee;
    public Label lblLabReportId;
    public JFXTextField txtDuration;
    public TableColumn colDuration;
    public JFXTimePicker tpTestedTime;
    public JFXButton btnIssueReceipt;
    public Label lblLabReportTotal;
    public JFXButton btnAddTestReport;
    public JFXTextField txtTestCategory;
    public JFXComboBox<String> cmbAddTestCategory;
    public JFXComboBox<String> cmbTestCategory;
    ObservableList<Test> observableList;
    ObservableList<TestReportTM> testReportTMObservableList = FXCollections.observableArrayList();
    ObservableList<String> testCategoryObsList = FXCollections.observableArrayList();
    ArrayList<TestLabReport> testLabReports = new ArrayList<>();

    double amount = 0.0;

    public void initialize() throws SQLException, ClassNotFoundException {
        ArrayList<TestCategory> testCategoryArrayList = null;
        try {
            testCategoryArrayList = new TestController().getAllTestCategory();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        for (TestCategory testCategory : testCategoryArrayList) {

            testCategoryObsList.add(testCategory.getTestCategoryId()+"-"+testCategory.getTestCategory());

        }

        cmbAddTestCategory.setItems(testCategoryObsList);
        cmbTestCategory.setItems(testCategoryObsList);




        colTestId.setCellValueFactory(new PropertyValueFactory<>("TestId"));
        colTestType.setCellValueFactory(new PropertyValueFactory<>("TestType"));
        colTestFee.setCellValueFactory(new PropertyValueFactory<>("TestFee"));
        colDuration.setCellValueFactory(new PropertyValueFactory<>("duration"));

        setTestTableData();

        try {
            lblTestId.setText(new TestController().getTestId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        tblTest.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                loadData(newValue);

        });

        colLabReportTestType.setCellValueFactory(new PropertyValueFactory<>("testType"));
        colTestedDay.setCellValueFactory(new PropertyValueFactory<>("testedDay"));
        colReceivedDay.setCellValueFactory(new PropertyValueFactory<>("receivedDay"));
        colLabReportTestFee.setCellValueFactory(new PropertyValueFactory<>("testFee"));

        try {
            lblLabReportId.setText(new LabReportController().getLabReportId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }


        cmbTestCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            loadTestType(newValue);
        });


    }


    private void loadTestType(String newValue) {
        String testCategoryId = newValue.split("-")[0];
        ArrayList<Test> testArrayList = null;
        try {
            testArrayList = new TestController().getAllTestByCategory(testCategoryId);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<String> observableListTestType = FXCollections.observableArrayList();
        for (Test test : testArrayList) {
            observableListTestType.add(test.getTestId()+"-"+test.getTestType());
        }
        cmbTestType.setItems(observableListTestType);

    }


    private void loadData(Test newValue) {
        lblTestId.setText(newValue.getTestId());
        txtTestType.setText(newValue.getTestType());
        txtTestFee.setText(String.valueOf(newValue.getTestFee()));
    }

    public void logOut(ActionEvent actionEvent) throws IOException {

    }

    public void updateTest(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String testId = lblTestId.getText();
        String testCategory =cmbAddTestCategory.getSelectionModel().getSelectedItem();
        String testCategoryId = testCategory.split("-")[0];
        String testType = txtTestType.getText();
        double testFee = Double.parseDouble(txtTestFee.getText());
        int duration = Integer.parseInt(txtDuration.getText());


        Test test = new Test(testId,testCategoryId, testType, testFee, duration);
        if (new TestController().updateTest(test)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Test Successfully...").show();
        }
        setTestTableData();
    }

    public void deleteTest(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new TestController().deleteTest(lblTestId.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setTestTableData();

    }

    public void addTest(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String testId = lblTestId.getText();
        String testType = txtTestType.getText();
        String testCategory =cmbAddTestCategory.getSelectionModel().getSelectedItem();
        String testCategoryId = testCategory.split("-")[0];
        double testFee = Double.parseDouble(txtTestFee.getText());
        int duration = Integer.parseInt(txtDuration.getText());

        Test test = new Test(testId,testCategoryId, testType, testFee, duration);
        if (new TestController().saveTest(test)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Test Successfully...").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        setTestTableData();
        lblTestId.setText("");
        txtTestType.clear();
        txtTestFee.clear();
        txtDuration.clear();
        lblTestId.setText(new TestController().getTestId());


    }

    private void setTestTableData() throws SQLException, ClassNotFoundException {
        ArrayList<Test> tests = new TestController().getAllTest();

        observableList = FXCollections.observableArrayList(tests);
        tblTest.setItems(observableList);
    }

    public void updateLabReport(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        String labReportId = lblLabReportId.getText();
//        String test = (String) cmbTestType.getSelectionModel().getSelectedItem();
//        String[] labReportArray=  test.split("-");
//        String testId=labReportArray[0];
//        String testType=labReportArray[1];
//        double testFee= Double.parseDouble(labReportArray[2]);
////        double testFee= Double.parseDouble(txtLabReportTestFee.getText());
//        LocalDate testedDate=dpTestedDay.getValue();
//        LocalDate receivedDate=dpReceivedDate.getValue();
//        int index=-1;
//        for (int i = 0; i< testReportTMObservableList.size(); i++) {
////            if (labReportId.equalsIgnoreCase(testReportTMObservableList.get(i).getLabReportId())){
//                index=i;
//            }
//        }

//        LabReport labReport=new LabReport(labReportId,testId,testedDate,receivedDate);
//
//        LabReportTM labReportTM=new LabReportTM(labReportId,testType,testedDate,receivedDate,testFee);
//
////        if (new LabReportController().updateLabReport(labReport)) {
//            new Alert(Alert.AlertType.CONFIRMATION, "Update LabReport").show();
//            testReportTMObservableList.remove(index);
//            testReportTMObservableList.add(labReportTM);

//        }


//        tblLabReport.refresh();

    }

    public void deleteLabReport(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        String id=lblLabReportId.getText();
//        int index=-1;
//        for (int i = 0; i< testReportTMObservableList.size(); i++) {
//            if (id.equalsIgnoreCase(testReportTMObservableList.get(i).getLabReportId())){
//                index=i;
//            }
//        }
//        if (new LabReportController().deleteLabReport(lblLabReportId.getText())) {
//            testReportTMObservableList.remove(index);
//            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
//            tblLabReport.refresh();
//        } else {
//            new Alert(Alert.AlertType.WARNING, "Try Again").show();
//        }
    }


    public void addTestReport(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String test = (String) cmbTestType.getSelectionModel().getSelectedItem();
        String testId = test.split("-")[0];
        String testCategory=cmbTestCategory.getSelectionModel().getSelectedItem();
        String testCategoryId=testCategory.split("-")[0];
        LocalDate testedDayValue = dpTestedDay.getValue();
        LocalTime testedTimeValue = tpTestedTime.getValue();


        LocalDateTime testDateTime = LocalDateTime.of(testedDayValue,testedTimeValue);

        TestReportTM testReportTM =new LabReportController().getTestReportTM(testId,testCategoryId,testDateTime);

        testReportTMObservableList.add(testReportTM);
        tblLabReport.setItems(testReportTMObservableList);
        amount += testReportTM.getTestFee();
        lblLabReportTotal.setText(String.valueOf(amount));


        String labReportId = lblLabReportId.getText();

        TestLabReport testLabReport = new TestLabReport(getTestLabReportId(),testId,labReportId,testReportTM.getReceivedDay());

        testLabReports.add(testLabReport);

    }

    private String getTestLabReportId() {
        if(testLabReports.isEmpty()){
            return "R_0001";
        }else {
            int tempId = Integer.parseInt(testLabReports.get(testLabReports.size()-1).getTestLabReportId().split("_")[1]);
            tempId=tempId+1;
            if (tempId <= 9) {
                return "R_000" + tempId;
            } else if (tempId <= 99) {
                return "R_00" + tempId;
            } else if (tempId <= 999) {
                return "R_0" + tempId;
            } else {
                return "R_" + tempId;
            }
        }

    }

    public void issueReceipt(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String labReportId = lblLabReportId.getText();
        LocalDateTime receiptDate = LocalDateTime.now();
        double amount=0.0;
        for (TestReportTM testReportTM : testReportTMObservableList) {
            amount += testReportTM.getTestFee();

        }
        LabReport labReport = new LabReport(labReportId,receiptDate,amount);
        if (new LabReportController().saveLabReport(labReport)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add LabReport Successfully...").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }

        for (TestLabReport testLabReport : testLabReports) {
            boolean b = new LabReportController().saveTestLabReport(testLabReport);

        }
        testReportTMObservableList.clear();
        testLabReports.clear();
        tblLabReport.setItems(null);
        lblLabReportId.setText(new LabReportController().getLabReportId());
    }

    public void AddTestCategory(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String testCategoryText = txtTestCategory.getText();
        TestCategory testCategory = new TestCategory(new TestController().getTestCategoryId(),testCategoryText);
        if (new TestController().saveTestCategory(testCategory)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add TestCategory Successfully...").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        testCategoryObsList.add(testCategory.getTestCategoryId()+"-"+testCategory.getTestCategory());
    }
}
