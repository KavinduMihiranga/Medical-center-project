package controller;

import db.DbConnection;
import model.Session;
import view.tm.DoctorSessionsTM;
import view.tm.PatientListTM;
import view.tm.SessionTM;

import java.sql.*;
import java.util.ArrayList;

public class SessionController implements SessionServices {

    public String getSessionId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT Session_Id FROM Session ORDER BY Session_Id DESC LIMIT 1"
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
        } else {
            return "S_0001";
        }
    }

    @Override
    public boolean saveSession(Session s) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Session VALUES(?,?,?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, s.getSession_Id());
        statement.setObject(2, s.getD_Id());
        statement.setObject(3, s.getMaxNoOfPatient());
        statement.setObject(4, s.getCurrentAppointmentNo());
        statement.setObject(5, s.getSessionDate());
        statement.setObject(6, s.getSessionStartTime());
        statement.setObject(7, s.getSessionEndTime());
        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean updateSession(Session s) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement
                ("UPDATE Session SET MaxNoOfPatients=?,CurrentAppointmentNo=?,Session_Date=?,Session_StartTime=?,Session_EndTime=? WHERE Session_Id=?");

        statement.setObject(1, s.getMaxNoOfPatient());
        statement.setObject(2, s.getCurrentAppointmentNo());
        statement.setObject(3, s.getSessionDate());
        statement.setObject(4, s.getSessionStartTime());
        statement.setObject(5, s.getSessionEndTime());
        statement.setObject(6, s.getSession_Id());

        return statement.executeUpdate() > 0;
    }

    @Override
    public boolean deleteSession(String id) throws SQLException, ClassNotFoundException {
        if (DbConnection.getInstance().getConnection().prepareStatement("DELETE FROM Session WHERE Session_Id='" + id + "'")
                .executeUpdate() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Session getSession(String Id) throws SQLException, ClassNotFoundException {
        PreparedStatement statement = DbConnection.getInstance().getConnection().prepareStatement("SELECT * FROM Session WHERE Session_Id=?");
        statement.setObject(1, Id);

        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return new Session(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    ((Date) resultSet.getObject(5)).toLocalDate(),
                    ((Time) resultSet.getObject(6)).toLocalTime(),
                    ((Time) resultSet.getObject(7)).toLocalTime()


            );
        } else {
            return null;
        }
    }

    @Override
    public ArrayList<Session> getAllSession() throws SQLException, ClassNotFoundException {
//        ArrayList<Session>Sessions=new ArrayList<>();
//        Connection connection=DbConnection.getInstance().getConnection();
//        String query="SELECT * FROM Session";
//        PreparedStatement statement=connection.prepareStatement(query);
//        ResultSet resultSet=statement.executeQuery();
//        while (resultSet.next()){
//            Sessions.add(new Session(
//                    resultSet.getString(1),
//                    resultSet.getString(2),
//                    resultSet.getDate(3),
//                    resultSet.getInt(4)
//
//
//            ));
//
//        }
//        return Sessions;
        return null;

    }

    public ArrayList<SessionTM> getAllSessionTM() throws SQLException, ClassNotFoundException {
        ArrayList<SessionTM> SessionTMS = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM Session";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        ArrayList<String> doctorIdArrayList = new ArrayList<>();
        while (resultSet.next()) {
            SessionTMS.add(new SessionTM(
                    resultSet.getString(1),
                    resultSet.getInt(4),
                    ((Date) resultSet.getObject(5)).toLocalDate(),
                    ((Time) resultSet.getObject(6)).toLocalTime(),
                    ((Time) resultSet.getObject(7)).toLocalTime()

            ));
            doctorIdArrayList.add(resultSet.getString(2));

        }
        for (int i = 0; i < doctorIdArrayList.size(); i++) {
            connection = DbConnection.getInstance().getConnection();

            String query1 = "SELECT D_Name,D_Speciality FROM Doctor WHERE D_Id=?";
            PreparedStatement statement1 = connection.prepareStatement(query1);
            statement1.setObject(1, doctorIdArrayList.get(i));
            ResultSet resultSet1 = statement1.executeQuery();

            while (resultSet1.next()) {
                SessionTMS.get(i).setDoctorName(resultSet1.getString(1));
                SessionTMS.get(i).setDoctorSpeciality(resultSet1.getString(2));

            }
        }
        return SessionTMS;
    }


    public ArrayList<DoctorSessionsTM> getAllSessionsTM(String doctorId) throws SQLException, ClassNotFoundException {
        ArrayList<DoctorSessionsTM> sessionsTMS = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT Session_Id,Session_Date,Session_StartTime,CurrentAppointmentNo FROM Session WHERE D_Id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, doctorId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            sessionsTMS.add(new DoctorSessionsTM(
                    resultSet.getString(1),
                    ((Date) resultSet.getObject(2)).toLocalDate(),
                    ((Time) resultSet.getObject(3)).toLocalTime(),
                    resultSet.getInt(4)

            ));

        }
        return sessionsTMS;
    }

    public int getMaxNoOfPatientBySessionId(String sessionId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT MaxNoOfPatients FROM Session WHERE Session_Id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, sessionId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return -1;
        }
    }

    public int getCurrentAppointmentNo(String sessionId) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT CurrentAppointmentNo FROM Session WHERE Session_Id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, sessionId);
        ResultSet resultSet = statement.executeQuery();
        if (resultSet.next()) {
            return resultSet.getInt(1);
        } else {
            return 0;
        }
    }

    public ArrayList<Session> getSessionByDoctorId(String doctorId) throws SQLException, ClassNotFoundException {
        ArrayList<Session> sessionsTMS = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT * FROM Session WHERE D_Id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, doctorId);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            sessionsTMS.add(new Session(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getInt(3),
                    resultSet.getInt(4),
                    ((Date) resultSet.getObject(5)).toLocalDate(),
                    ((Time) resultSet.getObject(6)).toLocalTime(),
                    ((Time) resultSet.getObject(7)).toLocalTime()

            ));

        }
        System.out.println(sessionsTMS);
        return sessionsTMS;

    }

    public ArrayList<PatientListTM> getPatientListBySessionId(String session_id) throws SQLException, ClassNotFoundException {
        ArrayList<PatientListTM> patientListTMS = new ArrayList<>();
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "SELECT a.App_Number,p.P_Name From Session s INNER JOIN Appointment a ON s.Session_Id = a.Session_Id INNER JOIN Patient p ON a.P_Id = p.P_Id Where s.Session_Id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, session_id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            patientListTMS.add(new PatientListTM(
                    resultSet.getInt(1),
                    resultSet.getString(2)
            ));
        }
            return patientListTMS;
    }
}
