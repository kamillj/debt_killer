package model;

public class LoginModel {

    private String username = "kamillj";
    private String password = "pass";
    private String enteredUsername = "";
    private String enteredPassword = "";
    private boolean isLoginSuccessful;

    public boolean checkUsernameAndPassword(String username, String password) {
        isLoginSuccessful = username.equals(this.username) && password.equals(this.password);
        return isLoginSuccessful;
    }
    public LoginModel(){
        checkUsernameAndPassword(enteredUsername, enteredPassword);
    }

    public void setEnteredUsername(String enteredUsername) {
        this.enteredUsername = enteredUsername;
    }

    public void setEnteredPassword(String enteredPassword) {
        this.enteredPassword = enteredPassword;
    }
}
