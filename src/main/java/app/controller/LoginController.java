package app.controller;

import app.model.LoginModel;
import app.view.LoginView;

public class LoginController {

    private LoginModel loginModel;
    private LoginView loginView;

    public LoginController(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void updateEnteredUsername(String enteredUsername){
        loginModel.setEnteredUsername(enteredUsername);
    }

    public void updateEnteredPassword(String enteredPassword){
        loginModel.setEnteredPassword(enteredPassword);
    }

    public boolean sendUsernameAndPasswordToCheck(String enteredUsername, String enteredPassword){
        return loginModel.checkUsernameAndPassword(enteredUsername, enteredPassword);
    }
}
