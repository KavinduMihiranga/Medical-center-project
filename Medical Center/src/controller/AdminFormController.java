package controller;

import com.jfoenix.controls.*;
import db.DbConnection;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;
import model.Doctor;
import model.Session;
import model.User;
//import util.Validation;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.omg.PortableInterceptor.ACTIVE;
import util.Validation;
import view.tm.SessionTM;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.ResourceBundle;
import java.util.regex.Pattern;

public class AdminFormController implements Initializable {
    public JFXButton btnExit;
    public JFXTextField txtD_Id;
    public AnchorPane contextAdmin;
    public JFXTextField txtD_PhoneNo;
    public JFXTextField txtD_Name;
    public JFXTextField txtMBBSNo;
    public JFXTextField txtD_Speciality;
    public JFXTextField txtD_Charge;
    public JFXButton btnUpdateDoctor;
    public JFXButton btnDeleteDoctor;
    public JFXButton btnAddDoctor;
    public TableView<Doctor> tblAddDoctor;
    public TableColumn colD_Id;
    public TableColumn colD_Name;
    public TableColumn col_MBBSNo;
    public TableColumn colD_PhoneNo;
    public TableColumn colD_Speciality;
    public TableColumn colD_Charge;
    public TableColumn colD_Address;
    public JFXTextField txtD_Address;

    public TableView<SessionTM> tblDoctorSession;
    public TableColumn colS_Id;
    public TableColumn colDS_Id;
    public TableColumn colDS_Name;
    public TableColumn colDateTime;
    public TableColumn colDS_Speciality;
    public TableColumn colMaxNoOfPatients;
    public JFXButton btnUpdateSession;
    public JFXButton btnDeleteSession;
    public JFXButton btnAddSession;
    public Label lblTime;
    public Label lblDate;
    public TableColumn colDoctorName;
    public TableColumn colDoctorSpeciality;
    public TableColumn colS_Date;
    public TableColumn colS_StartTime;
    public TableColumn colS_EndTime;
    public JFXTextField txtMaxNoOfPatients;
    public JFXTimePicker tPS_StartTime;
    public JFXDatePicker dPS_Date;
    public JFXTimePicker tPS_EndTime;
    public Label lblD_Id;
    public Label lblSession_Id;
    public JFXComboBox cmbDoctor;
    public JFXTextField txtF_Name;
    public JFXTextField txtL_Name;
    public Label lblSession_Id1;
    public JFXTextField txtEmail;
    public JFXTextField txtContactNo;
    public JFXPasswordField pwd;
    public JFXTextField txtUserName;
    public JFXComboBox<String> cmbAccessLevel;
    public Label lblUserId;
    public JFXComboBox<String> cmbUserStatus;
    public TableView<User> tblUser;
    public TableColumn colUserId;
    public TableColumn colFirstName;
    public TableColumn colLastName;
    public TableColumn colEmail;
    public TableColumn colContact;
    public TableColumn colUsername;
    public TableColumn colPassword;
    public TableColumn colAccessLevel;
    public TableColumn colStatus;
    public JFXButton btnReport;
    ObservableList<Doctor> observableList;
    ObservableList<SessionTM> sessionTMObservableList=null;


    LinkedHashMap<JFXTextField, Pattern> map = new LinkedHashMap();
    Pattern doctorNamePattern = Pattern.compile("^[A-z\\s]{1,40}$");
    Pattern doctorSpecialityPattern = Pattern.compile("^[A-z]{1,30}$");
    Pattern doctorAddressPattern = Pattern.compile("^[A-z0-9\\.\\-\\s\\,]{1,50}$");
    Pattern doctorMBBSPattern = Pattern.compile("^[A-z0-9]{1,15}$");
    Pattern doctorPhoneNoPattern = Pattern.compile("^[0][0-9]{9}$");
    Pattern doctorChargePattern = Pattern.compile("^\\d{1,10}(?:\\.\\d{0,2})?$");

    private void setDoctorTableData() throws SQLException, ClassNotFoundException {
        ArrayList<Doctor> doctors = new DoctorController().getAllDoctor();
        observableList = FXCollections.observableArrayList(doctors);
        tblAddDoctor.setItems(observableList);
    }

    public void exit(ActionEvent actionEvent) throws IOException {
        URL resource = getClass().getResource("../view/DashBoard.fxml");
        Parent load = FXMLLoader.load(resource);
        Scene scene = new Scene(load);
        Stage stage = new Stage();
        stage.setTitle("Main Menu");
        stage.setScene(scene);
        stage.show();
    }


    public void updateDoctor(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String D_Id = lblD_Id.getText();
        String D_Name = txtD_Name.getText();
        String D_Address = txtD_Address.getText();
        String D_Speciality = txtD_Speciality.getText();
        String D_PhoneNo = txtD_PhoneNo.getText();
        String D_MBBSNo = txtMBBSNo.getText();
        double D_Charge = Double.parseDouble(txtD_Charge.getText());

        Doctor doctor = new Doctor(D_Id, D_Name, D_Address, D_Speciality, D_PhoneNo, D_MBBSNo, D_Charge);

        if (new DoctorController().updateDoctor(doctor)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Doctor").show();
        }


        setDoctorTableData();
    }

    private int isExists(String dn_id) {
        for (int i = 0; i < observableList.size(); i++) {
            if (dn_id.equalsIgnoreCase(observableList.get(i).getD_Id())) {
                return i;
            }
        }
        return -1;
    }

    private void loadData(Doctor newValue) {
        lblD_Id.setText(newValue.getD_Id());
        txtD_Name.setText(newValue.getD_Name());
        txtD_Address.setText(newValue.getD_Address());
        txtD_Speciality.setText(newValue.getD_Speciality());
        txtD_PhoneNo.setText(newValue.getD_PhoneNo());
        txtMBBSNo.setText(newValue.getD_MBBSNo());
        txtD_Charge.setText(String.valueOf(newValue.getD_Charge()));


    }

    public void deleteDoctor(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        if (new DoctorController().deleteDoctor(lblD_Id.getText())) {
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }

        setDoctorTableData();
    }

    public void addDoctor(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String D_Id = lblD_Id.getText();
        String D_Name = txtD_Name.getText();
        String D_Address = txtD_Address.getText();
        String D_Speciality = txtD_Speciality.getText();
        String D_PhoneNo = txtD_PhoneNo.getText();
        String D_MBBSNo = txtMBBSNo.getText();
        double D_Charge = Double.parseDouble(txtD_Charge.getText());

        Doctor doctor = new Doctor(D_Id, D_Name, D_Address, D_Speciality, D_PhoneNo, D_MBBSNo, D_Charge);
        if (new DoctorController().saveDoctor(doctor)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Doctor Successfully...").show();
            User user = new User(new UserController().getUserId(),"Dr.",D_Name,D_PhoneNo,"Doctor","Inactive");
            if (new UserController().saveUser(user)) {
                new Alert(Alert.AlertType.INFORMATION, "Enable Doctor as User").show();
            }
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        setDoctorTableData();
        setUserTableData();
        lblD_Id.setText("");
        txtD_Name.clear();
        txtD_Address.clear();
        txtD_Speciality.clear();
        txtD_PhoneNo.clear();
        txtMBBSNo.clear();
        txtD_Charge.clear();
        lblD_Id.setText(new DoctorController().getDoctorId());

    }

    private void setDoctorId() {
        try {
            lblD_Id.setText(new DoctorController().getDoctorId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public void updateSession(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {

        String sessionId = lblSession_Id.getText();

//        String doctor = (String) cmbDoctor.getSelectionModel().getSelectedItem();
//        String[] array=  doctor.split("-");
//        String doctorId=array[0];
//        String doctorName=array[1];
//        String doctorSpeciality=array[2];
        LocalDate sessionDate=dPS_Date.getValue();
        LocalTime startTime=tPS_StartTime.getValue();
        LocalTime endTime=tPS_EndTime.getValue();
        int maxNoOfPatients = Integer.parseInt(txtMaxNoOfPatients.getText());
        int index=-1;
        for (int i=0;i<sessionTMObservableList.size();i++) {
            if (sessionId.equalsIgnoreCase(sessionTMObservableList.get(i).getSessionId())){
                index=i;
            }
        }

        Session session=new Session(sessionId,maxNoOfPatients,sessionDate,startTime,endTime);

        SessionTM sessionTM=new SessionTM(sessionId,doctorName,doctorSpeciality,maxNoOfPatients,sessionDate,startTime,endTime);

        if (new SessionController().updateSession(session)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Doctor").show();
            sessionTMObservableList.remove(index);
            sessionTMObservableList.add(sessionTM);

        }



        tblDoctorSession.refresh();
    }

    public void deleteSession(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id=lblSession_Id.getText();
        int index=-1;
        for (int i=0;i<sessionTMObservableList.size();i++) {
            if (id.equalsIgnoreCase(sessionTMObservableList.get(i).getSessionId())){
                index=i;
            }
        }
        if (new SessionController().deleteSession(lblSession_Id.getText())) {
            sessionTMObservableList.remove(index);
            new Alert(Alert.AlertType.CONFIRMATION, "Deleted").show();
            tblDoctorSession.refresh();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again").show();
        }


    }


    public void addSession(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String sessionId = lblSession_Id.getText();
        String doctor = (String) cmbDoctor.getSelectionModel().getSelectedItem();
        String[] array=  doctor.split("-");
        String doctorId=array[0];
        String doctorName=array[1];
        String doctorSpeciality=array[2];
        LocalDate sessionDate=dPS_Date.getValue();
        LocalTime startTime=tPS_StartTime.getValue();
        LocalTime endTime=tPS_EndTime.getValue();
        int maxNoOfPatients = Integer.parseInt(txtMaxNoOfPatients.getText());

        SessionTM sessionTM=new SessionTM(sessionId,doctorName,doctorSpeciality,maxNoOfPatients,sessionDate,startTime,endTime);

        Session session=new Session(sessionId,doctorId,maxNoOfPatients,0,sessionDate,startTime,endTime);
        if (new SessionController().saveSession(session)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add Session Successfully...").show();
            sessionTMObservableList.add(sessionTM);
            tblDoctorSession.refresh();
        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        lblSession_Id.setText("");
//        dPS_Date.setValue();
        txtMaxNoOfPatients.clear();
        lblSession_Id.setText(new SessionController().getSessionId());
    }

    public void selectDoctor(ActionEvent actionEvent) {
    }

    private void loadDateAndTime() {
        // load Date
        Date date = new Date();
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        lblDate.setText(f.format(date));

        // load Time
        Timeline time = new Timeline(new KeyFrame(Duration.ZERO, e -> {
            LocalTime currentTime = LocalTime.now();
            lblTime.setText(
                    currentTime.getHour() + " : " + currentTime.getMinute() +
                            " : " + currentTime.getSecond()
            );
        }),
                new KeyFrame(Duration.seconds(1))
        );
        time.setCycleCount(Animation.INDEFINITE);
        time.play();
    }

    public void searchDoctor(ActionEvent actionEvent) {


    }
    private void storeValidations() {
        map.put(txtD_Name, doctorNamePattern);
        map.put(txtD_Speciality,doctorSpecialityPattern);
        map.put(txtD_Address, doctorAddressPattern);
        map.put(txtMBBSNo, doctorMBBSPattern);
        map.put(txtD_PhoneNo, doctorPhoneNoPattern);
        map.put(txtD_Charge, doctorChargePattern);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        btnAddDoctor.setDisable(true);
        storeValidations();


        colD_Id.setCellValueFactory(new PropertyValueFactory<>("d_Id"));
        colD_Name.setCellValueFactory(new PropertyValueFactory<>("d_Name"));
        colD_Address.setCellValueFactory(new PropertyValueFactory<>("d_Address"));
        colD_Speciality.setCellValueFactory(new PropertyValueFactory<>("d_Speciality"));
        colD_PhoneNo.setCellValueFactory(new PropertyValueFactory<>("d_PhoneNo"));
        col_MBBSNo.setCellValueFactory(new PropertyValueFactory<>("d_MBBSNo"));
        colD_Charge.setCellValueFactory(new PropertyValueFactory<>("d_Charge"));

        try {
            lblUserId.setText(new UserController().getUserId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
//        loadDateAndTime();
        try {
            lblSession_Id.setText(new SessionController().getSessionId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        setDoctorId();


        try {
            setDoctorTableData();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblAddDoctor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null)
                loadData(newValue);

        });

        ArrayList<Doctor> doctorArrayList = null;
        try {
            doctorArrayList = new DoctorController().getAllDoctor();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<String> observableListD_Name = FXCollections.observableArrayList();
        for (Doctor doctor : doctorArrayList) {
            observableListD_Name.add(doctor.getD_Id() + "-" + doctor.getD_Name() + "-" + doctor.getD_Speciality());
        }
        cmbDoctor.setItems(observableListD_Name);



        ObservableList<String> observableListAccessLevel = FXCollections.observableArrayList("Admin","Doctor","Reception","Pharmacy","Laboratory");

        cmbAccessLevel.setItems(observableListAccessLevel);




        colS_Id.setCellValueFactory(new PropertyValueFactory<>("sessionId"));
        colDoctorName.setCellValueFactory(new PropertyValueFactory<>("doctorName"));
        colDoctorSpeciality.setCellValueFactory(new PropertyValueFactory<>("doctorSpeciality"));
        colMaxNoOfPatients.setCellValueFactory(new PropertyValueFactory<>("maxNoOfPatients"));
        colS_Date.setCellValueFactory(new PropertyValueFactory<>("sessionDate"));
        colS_StartTime.setCellValueFactory(new PropertyValueFactory<>("sessionStartTime"));
        colS_EndTime.setCellValueFactory(new PropertyValueFactory<>("sessionEndTime"));


        try {
            sessionTMObservableList=FXCollections.observableArrayList(new SessionController().getAllSessionTM());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        tblDoctorSession.setItems(sessionTMObservableList);

      tblDoctorSession.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
          if (newValue!=null){
              loadSessionData(newValue);
          }
      });

      ObservableList<String> observableList = FXCollections.observableArrayList("Active","Inactive");
      cmbUserStatus.setItems(observableList);

      colUserId.setCellValueFactory(new PropertyValueFactory<>("userId"));
      colFirstName.setCellValueFactory(new PropertyValueFactory<>("firstName"));
      colLastName.setCellValueFactory(new PropertyValueFactory<>("lastName"));
      colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
      colContact.setCellValueFactory(new PropertyValueFactory<>("contact"));
      colUsername.setCellValueFactory(new PropertyValueFactory<>("userName"));
      colPassword.setCellValueFactory(new PropertyValueFactory<>("password"));
      colAccessLevel.setCellValueFactory(new PropertyValueFactory<>("AccessLevel"));
      colStatus.setCellValueFactory(new PropertyValueFactory<>("status"));

       setUserTableData();

       tblUser.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
           if(newValue!=null) {
               loadUserData(newValue);
           }
       });
    }

    private void loadUserData(User newValue) {
        lblUserId.setText(newValue.getUserId());
        txtF_Name.setText(newValue.getFirstName());
        txtL_Name.setText(newValue.getLastName());
        txtContactNo.setText(newValue.getContact());
        cmbAccessLevel.getSelectionModel().select(newValue.getAccessLevel());
        cmbUserStatus.getSelectionModel().select(newValue.getStatus());
    }


    String doctorName=null;
    String doctorSpeciality=null;
    private void loadSessionData(SessionTM newValue) {
        lblSession_Id.setText(newValue.getSessionId());
        dPS_Date.setValue(newValue.getSessionDate());
        tPS_StartTime.setValue(newValue.getSessionStartTime());
        tPS_EndTime.setValue(newValue.getSessionEndTime());
        txtMaxNoOfPatients.setText(String.valueOf(newValue.getMaxNoOfPatients()));
        doctorName=newValue.getDoctorName();
        doctorSpeciality=newValue.getDoctorSpeciality();
    }

    public void logOut(ActionEvent actionEvent) throws IOException {
//        URL resource = getClass().getResource("../view/DashBoard.fxml");
//        Parent load = FXMLLoader.load(resource);
//        new DashBoardController().contextMain.getChildren().clear();
//        new DashBoardController().contextMain.getChildren().add(load);

    }

    public void addUser(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String userId = lblUserId.getText();
        String firstName = txtF_Name.getText();
        String lastName = txtL_Name.getText();
        String email = txtEmail.getText();
        String contactNo = txtContactNo.getText();
        String userName = txtUserName.getText();
        String password = pwd.getText();
        String accessLevel = cmbAccessLevel.getSelectionModel().getSelectedItem();
        String userStatus = (String) cmbUserStatus.getSelectionModel().getSelectedItem();

        User user = new User(userId, firstName, lastName, email, contactNo,userName, password, accessLevel,userStatus);
        if (new UserController().saveUser(user)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Add User Successfully...").show();

        } else {
            new Alert(Alert.AlertType.WARNING, "Try Again...").show();
        }
        setUserTableData();
        lblUserId.setText("");
        txtF_Name.clear();
        txtL_Name.clear();
        txtEmail.clear();
        txtContactNo.clear();
        txtUserName.clear();
        pwd.clear();
//        cmbAccessLevel.;
        lblUserId.setText(new UserController().getUserId());
    }

    private void setUserTableData() {
        ArrayList<User> usersList = new ArrayList<>();
        try {
            usersList=new UserController().getAllUsers();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        ObservableList<User> userObservableList = FXCollections.observableArrayList(usersList);
        tblUser.setItems(userObservableList);
    }

    public void jfxTextField_KeyReleased(KeyEvent keyEvent) {
        Object response = Validation.validate(map,btnAddDoctor);

        if (keyEvent.getCode() == KeyCode.ENTER) {
            if (response instanceof JFXTextField) {
                JFXTextField errorText = (JFXTextField) response;
                errorText.requestFocus();
            } else if (response instanceof Boolean) {
                new Alert(Alert.AlertType.INFORMATION, "Aded").showAndWait();
            }
        }
    }

    public void enableUser(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String userId = lblUserId.getText();
        String firstName = txtF_Name.getText();
        String lastName = txtL_Name.getText();
        String email = txtEmail.getText();
        String contactNo = txtContactNo.getText();
        String username = txtUserName.getText();
        String password = pwd.getText();
        String accessLevel = cmbAccessLevel.getSelectionModel().getSelectedItem();
        String userStatus = cmbUserStatus.getSelectionModel().getSelectedItem();

        User user = new User(userId,firstName,lastName,email,contactNo,username,password,accessLevel,userStatus);

        if (new UserController().updateUser(user)) {
            new Alert(Alert.AlertType.CONFIRMATION, "Update Doctor").show();
        }
        setUserTableData();
    }

    public void viewReport(ActionEvent actionEvent) throws SQLException, ClassNotFoundException, JRException {
        try {
        JasperDesign design = JRXmlLoader.load(this.getClass().getResourceAsStream("/view/report/DoctorSpeciality.jrxml"));
        JasperReport jasperReport = JasperCompileManager.compileReport(design);
        JasperPrint print = null;
            print = JasperFillManager.fillReport(jasperReport, null, DbConnection.getInstance().getConnection());

        JasperViewer.viewReport(print,false);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }


//    public void selectDoctor(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
//        if (new DoctorController().getDoctorId().equalsIgnoreCase(String.valueOf(txtDS_Id))) {
//           txtD_Name.setText(new DoctorController());
}
//    }
//}
