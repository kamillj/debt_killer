package app.view;

import app.controller.LoginController;
import app.model.LoginModel;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.control.Button;
import javafx.scene.layout.Priority;

public class LoginView {

    private GridPane loginDialog;
    private Label usernameLabel;
    private Label passwordLabel;
    private TextField usernameField;
    private PasswordField passwordField;
    private Button loginButton;

    private LoginController loginController;

    public LoginView(LoginModel loginModel, LoginController loginController) {
        this.loginController = loginController;
        createAndConfigurePane();
        createAndLayoutControls();
        addListeners();
        updateControllerFromListeners();
    }

    public Parent asParent() {
        return loginDialog;
    }

    private void createAndConfigurePane() {
        loginDialog = new GridPane();

        ColumnConstraints leftCol = new ColumnConstraints();
        leftCol.setHalignment(HPos.RIGHT);
        leftCol.setHgrow(Priority.NEVER);

        ColumnConstraints rightCol = new ColumnConstraints();
        rightCol.setHgrow(Priority.SOMETIMES);

        loginDialog.getColumnConstraints().addAll(leftCol, rightCol);
        loginDialog.setAlignment(Pos.CENTER);
        loginDialog.setHgap(5);
        loginDialog.setVgap(10);
    }

    private void createAndLayoutControls() {
        usernameLabel = new Label("Username:");
        passwordLabel = new Label("Password");
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Log in");
        loginDialog.addRow(0, usernameLabel, usernameField);
        loginDialog.addRow(1, passwordLabel, passwordField);
        loginDialog.addRow(2, loginButton);
    }

    private void addListeners(){
        loginButton.setOnAction(event -> {
            if(loginController.sendUsernameAndPasswordToCheck(usernameField.getText(), passwordField.getText())){
                System.out.println("Login succesful");
            } else {
                System.out.println("Login failed");
            }
        });
    }

    private void updateControllerFromListeners(){
        usernameField.textProperty().addListener((observable, oldValue, newValue) -> loginController.updateEnteredUsername(newValue));
        passwordField.textProperty().addListener((observable, oldValue, newValue) -> loginController.updateEnteredPassword(newValue));
    }
}
