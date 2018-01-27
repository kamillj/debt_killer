package app.model;

import app.controller.LoginController;
import util.db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {

    private boolean loginStatus;

    private LoginController loginController;

    public LoginModel(){
    }

    public void checkUsernameAndPassword(String enteredUsername, String enteredPassword) throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        String sql = "SELECT USERNAME, PASSWORD FROM ACCOUNTS WHERE USERNAME = ? AND PASSWORD = ?";

        connection = JDBCUtil.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, enteredUsername);
        preparedStatement.setString(2, enteredPassword);

        ResultSet resultSet = preparedStatement.executeQuery();

        loginStatus = resultSet.next();
    }

    public boolean isLoginSuccessful() {
        return loginStatus;
    }
}
