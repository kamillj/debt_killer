package app;

import app.controller.CategoryController;
import app.controller.LoginController;
import app.controller.MainController;
import app.model.CategoryModel;
import app.model.LoginModel;
import app.model.MainModel;
import app.view.CategoryView;
import app.view.LoginView;
import app.view.MainView;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.sql.SQLException;


public class ViewManager {

    private static Scene scene;
    private static Stage stage;

    static void start(Stage primaryStage) {
        stage = primaryStage;
        loadLoginView(primaryStage);
    }

    private static void loadLoginView(Stage primaryStage) {
        stage = primaryStage;

        LoginModel loginModel = new LoginModel();
        LoginController loginController = new LoginController(loginModel);
        LoginView loginView = new LoginView(loginController);

        scene = new Scene(loginView.asParent(), 400, 400);
        stage.initStyle(StageStyle.UTILITY);
        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) stage.setMaximized(false);
        });
        stage.setScene(scene);
        stage.show();
    }

    public static void loadMainView() {
        MainModel mainModel = new MainModel();
        MainController mainController = new MainController(mainModel);
        MainView mainView = new MainView(mainController);
        mainModel.setMainController(mainController);

        scene = new Scene(mainView.asParent(), 1000, 800);
        stage.setScene(scene);
        stage.centerOnScreen();
        stage.setMaximized(true);
    }

    public static void loadCategoryView() {
        CategoryModel categoryModel = new CategoryModel();
        CategoryController categoryController = new CategoryController(categoryModel);
        CategoryView categoryView = new CategoryView(categoryController);
        categoryModel.setCategoryController(categoryController);

        Scene scene = new Scene(categoryView.asParent(), 300, 400);
        Stage stage = new Stage();
        stage.initStyle(StageStyle.UTILITY);
        stage.initModality(Modality.WINDOW_MODAL);
        stage.initOwner(ViewManager.stage);
        stage.setMinWidth(300);
        stage.setMinHeight(400);
        categoryView.setStage(stage);
        stage.setScene(scene);
        stage.maximizedProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue) stage.setMaximized(false);
        });
        stage.show();
    }
}
