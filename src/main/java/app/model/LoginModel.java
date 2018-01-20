package app.model;

import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginModel {


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

        if(resultSet.next()){
            System.out.println("login successful");
        } else {
            System.out.println("login failed");
        }
    }
}
