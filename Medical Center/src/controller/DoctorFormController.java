package controller;

import com.jfoenix.controls.JFXButton;
import db.DbConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Session;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class DoctorFormController implements Initializable {
    public TableView<Session> tblDoctorSessions;
    public TableColumn colSessionDate;
    public TableColumn colSessionStartTime;
    public TableColumn colSessionEndTime;
    public TableColumn colSessionMaxNoOfPatients;
    public TableColumn colSessionNoOfAppointments;
    public JFXButton btPatientsList;
    public Label lblDoctor;
    public TableColumn colSessionId;
    public JFXButton btnDoctorIncomeReport;

    public void logOut(ActionEvent actionEvent) throws IOException {

    }

    public void viewPatientsList(ActionEvent actionEvent) {

    }
    ArrayList<Session> sessions =new ArrayList<>();

    @Override
    public void initialize(URL location, ResourceBundle resources) {

//        lblDoctor.setText();

        colSessionId.setCellValueFactory(new PropertyValueFactory<>("session_Id"));
        colSessionDate.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        colSessionStartTime.setCellValueFactory(new PropertyValueFactory<>("sessionStartTime"));
        colSessionEndTime.setCellValueFactory(new PropertyValueFactory<>("sessionEndTime"));
        colSessionMaxNoOfPatients.setCellValueFactory(new PropertyValueFactory<>("maxNoOfPatient"));
        colSessionNoOfAppointments.setCellValueFactory(new PropertyValueFactory<>("currentAppointmentNo"));

        tblDoctorSessions.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            btPatientsList.setOnAction(event -> {
                FXMLLoader loader=new FXMLLoader(this.getClass().getResource("../view/PatientsList.fxml"));
                Parent load=null;
                try {
                     load = loader.load();
                } catch (IOException e) {
                    e.printStackTrace();
                }

                PatientsListController controller=loader.<PatientsListController>getController();
                controller.lblDoctorSession.setText(newValue.getSession_Id()+"-"+newValue.getD_Id());
                try {
                    controller.loadIds(newValue.getSession_Id(),newValue.getD_Id());
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                Stage stage = new Stage();
                stage.setScene(new Scene(load));
                stage.show();
            });
        });

    }
    private void initTable(String D_id){
        try {
            sessions =new SessionController().getSessionByDoctorId(D_id);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        ObservableList<Session> sessionObservableList = FXCollections.observableArrayList(sessions);


        tblDoctorSessions.setItems(sessionObservableList);
    }

    public void loadData(String d_id) {
        initTable(d_id);
    }

    public void incomeReport(ActionEvent actionEvent) {
        try {
            JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/report/DoctorIncome.jrxml"));
            JasperReport jasperReport = JasperCompileManager.compileReport(design);
            String doctorId = lblDoctor.getText().split("-")[0];
            HashMap map = new HashMap();
            map.put("doctorId",doctorId);
            JasperPrint print = null;
            print = JasperFillManager.fillReport(jasperReport, map,DbConnection.getInstance().getConnection());

            JasperViewer.viewReport(print,false);
        } catch (JRException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
