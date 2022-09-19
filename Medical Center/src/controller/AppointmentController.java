package controller;

import db.DbConnection;
import model.Appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AppointmentController {
    public boolean saveAppointment(Appointment appointment) throws SQLException, ClassNotFoundException {
        Connection connection = DbConnection.getInstance().getConnection();
        String query = "INSERT INTO Appointment VALUES(?,?,?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setObject(1, appointment.getAppointmentId());
        statement.setObject(2, appointment.getPatientId());
        statement.setObject(3, appointment.getSessionId());
        statement.setObject(4, appointment.getAppointmentNo());
        statement.setObject(5, appointment.getAppointmentFee());


        return statement.executeUpdate() > 0;
    }

    public String getAppointmentId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT App_Id FROM Appointment ORDER BY App_Id DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "A_000" + tempId;
            } else if (tempId <= 99) {
                return "A_00" + tempId;
            } else if (tempId <= 999) {
                return "A_0" + tempId;
            } else {
                return "A_" + tempId;
            }
        }else {
            return "A_0001";
        }
    }
}
