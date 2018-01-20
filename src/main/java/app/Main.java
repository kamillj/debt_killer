package app;


import app.controller.LoginController;
import app.model.LoginModel;
import app.view.LoginView;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {


    @Override
    public void start(Stage primaryStage) {
        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginModel);
        LoginView loginView = new LoginView(loginController);

        Scene scene = new Scene(loginView.asParent(), 400, 400);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
