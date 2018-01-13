package controller;

import model.LoginModel;

public class LoginController {

    private final LoginModel loginModel;

    public LoginController(LoginModel loginModel) {
        this.loginModel = loginModel;
    }

    public void updateEnteredUsername(String enteredUsername){
        loginModel.setEnteredUsername(enteredUsername);
    }

    public void updateEnteredPassword(String enteredPassword){
        loginModel.setEnteredPassword(enteredPassword);
    }
}
