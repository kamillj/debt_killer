package app.controller;

import app.model.LoginModel;
import app.view.LoginView;

import java.sql.SQLException;

public class LoginController {

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginController(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void sendUsernameAndPasswordToCheck(String enteredUsername, String enteredPassword) throws SQLException {
        loginModel.checkUsernameAndPassword(enteredUsername, enteredPassword);
    }

    public boolean loginStatusInfo(){
       return loginModel.isLoginSuccessful();
    }
}
