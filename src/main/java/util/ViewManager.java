package util;

import app.controller.LoginController;
import app.controller.MainController;
import app.model.LoginModel;
import app.model.MainModel;
import app.view.LoginView;
import app.view.MainView;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ViewManager {

    private static Scene scene;
    private static Stage stage;

    public static void start(Stage primaryStage) {
        stage = primaryStage;
        loadLoginView(primaryStage);
    }

    private static void loadLoginView(Stage primaryStage) {
        stage = primaryStage;

        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginModel);
        LoginView loginView = new LoginView(loginController);

        scene = new Scene(loginView.asParent(), 400, 400);
        stage.setScene(scene);
        stage.show();
    }

    public static void loadMainView() {
        MainModel mainModel = new MainModel();
        MainController mainController = new MainController(mainModel);
        MainView mainView = new MainView(mainController);
        mainModel.setMainController(mainController);

        scene = new Scene(mainView.asParent(), 1000, 600);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(true);
    }
}
