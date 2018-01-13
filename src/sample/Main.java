package sample;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Debt Killer");
        Button closeButton = new Button("Zamknij");
        closeButton.setOnAction(event -> {
            System.out.println("Zamykam!");
            primaryStage.close();
        });

        StackPane root = new StackPane();
        root.getChildren().add(closeButton);
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
