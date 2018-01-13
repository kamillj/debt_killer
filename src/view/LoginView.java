package view;

import controller.LoginController;
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
import model.LoginModel;

public class LoginView {

    private GridPane view;
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
        return view;
    }

    private void createAndConfigurePane() {
        view = new GridPane();

        ColumnConstraints leftCol = new ColumnConstraints();
        leftCol.setHalignment(HPos.RIGHT);
        leftCol.setHgrow(Priority.NEVER);

        ColumnConstraints rightCol = new ColumnConstraints();
        rightCol.setHgrow(Priority.SOMETIMES);

        view.getColumnConstraints().addAll(leftCol, rightCol);
        view.setAlignment(Pos.CENTER);
        view.setHgap(5);
        view.setVgap(10);
    }

    private void createAndLayoutControls() {
        usernameLabel = new Label("Username:");
        passwordLabel = new Label("Password");
        usernameField = new TextField();
        passwordField = new PasswordField();
        loginButton = new Button("Log in");
        view.addRow(0, usernameLabel, usernameField);
        view.addRow(1, passwordLabel, passwordField);
        view.addRow(2, loginButton);
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
