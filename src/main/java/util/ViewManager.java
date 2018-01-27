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

    public static void start(Stage primaryStage) {
        loadLoginView(primaryStage);
    }

    private static void loadLoginView(Stage primaryStage) {
        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginModel);
        LoginView loginView = new LoginView(loginController);

        Scene loginScene = new Scene(loginView.asParent(), 400, 400);
        primaryStage.setScene(loginScene);
        primaryStage.show();
    }

    public static void loadMainView() {
        MainModel mainModel = new MainModel();
        MainController mainController = new MainController(mainModel);
        MainView mainView = new MainView(mainController);
        mainModel.setMainController(mainController);

        Scene mainScene = new Scene(mainView.asParent(), 0, 0);
        Stage mainStage = new Stage();
        mainStage.setFullScreen(true);
        mainStage.setScene(mainScene);
        mainStage.show();
    }
}
