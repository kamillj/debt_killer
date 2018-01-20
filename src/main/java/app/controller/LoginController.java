package app.controller;

import app.model.LoginModel;

import java.sql.SQLException;

public class LoginController {

    private LoginModel loginModel;

    public LoginController(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void sendUsernameAndPasswordToCheck(String enteredUsername, String enteredPassword) throws SQLException {
        loginModel.checkUsernameAndPassword(enteredUsername, enteredPassword);
    }
}
