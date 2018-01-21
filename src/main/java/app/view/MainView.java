package app.view;

import app.controller.MainController;
import javafx.geometry.Insets;
import javafx.scene.layout.*;

public class MainView {

    private BorderPane mainDialog;

    private MainController mainController;

    public MainView(MainController mainController){
        this.mainController = mainController;
        createAndConfigureView();
        createAndLayoutControls();
        addListeners();
    }

    public BorderPane asParent(){
        return mainDialog;
    }

    private void createAndConfigureView(){
        mainDialog = new BorderPane();
        mainDialog.setRight(appendAddExpensePanel());
    }

    private void createAndLayoutControls(){
    }

    private void addListeners(){
    }

    private FlowPane appendAddExpensePanel(){
        FlowPane addExpensePanel = new FlowPane();
        addExpensePanel.setPadding(new Insets(5, 0, 5, 0));
        addExpensePanel.setVgap(4);
        addExpensePanel.setHgap(4);
        addExpensePanel.setPrefWrapLength(400);
        addExpensePanel.setStyle("-fx-background-color: DAE6F3;");

        return addExpensePanel;
    }
}
