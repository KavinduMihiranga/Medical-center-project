package controller;

import db.DbConnection;
import model.Login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    public String getLoginId() throws SQLException, ClassNotFoundException {
        ResultSet resultSet = DbConnection.getInstance()
                .getConnection().prepareStatement(
                        "SELECT Log_Id FROM Login ORDER BY Log_Id DESC LIMIT 1"
                ).executeQuery();
        if (resultSet.next()) {
            int tempId = Integer.parseInt(resultSet.getString(1).split("_")[1]);
            tempId = tempId + 1;
            if (tempId <= 9) {
                return "I_000" + tempId;
            } else if (tempId <= 99) {
                return "I_00" + tempId;
            } else if (tempId <= 999) {
                return "I_0" + tempId;
            } else {
                return "I_" + tempId;
            }
        }else {
            return "I_0001";
        }
    }

    public boolean saveLogin(Login login) throws SQLException, ClassNotFoundException {
        Connection connection= DbConnection.getInstance().getConnection();
        String query="INSERT INTO Login VALUES(?,?,?,?)";
        PreparedStatement statement=connection.prepareStatement(query);
        statement.setObject(1,login.getLoginId());
        statement.setObject(2,login.getD_Id());
        statement.setObject(3,login.getUserId());
        statement.setObject(4,login.getLoginStatus());
        return statement.executeUpdate()>0;
    }
}
