package app.view;

import app.controller.MainController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.sql.SQLException;
import java.time.LocalDate;

public class MainView {

    private TextField amountField;
    private ChoiceBox<String> categoryBox;
    private DatePicker datePicker;
    private Button saveExpenseButton;
    private BorderPane mainDialog;

    private MainController mainController;

    public MainView(MainController mainController) {
        this.mainController = mainController;
        createAndConfigureView();
        createAndLayoutControls();
        addListeners();
    }

    public BorderPane asParent() {
        return mainDialog;
    }

    private void createAndConfigureView() {
        mainDialog = new BorderPane();
        mainDialog.setRight(appendAddExpensePanel());
    }

    private void createAndLayoutControls() {
    }

    private void addListeners() {
        saveExpenseButton.setOnAction((ActionEvent event) -> {
            try {
                mainController.sendExpenseToSave(getAmount(), getCategory(), getDate());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private GridPane appendAddExpensePanel() {
        GridPane addExpensePanel = new GridPane();
        Text title = new Text("ADD EXPENSE");
        title.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        addExpensePanel.setMinWidth(400);
        addExpensePanel.setStyle("-fx-background-color: DAE6F3;");

        Label amountLabel = new Label("Amount");
        amountField = new TextField("");
        Label categoryLabel = new Label("Category");
        categoryBox = new ChoiceBox<>(FXCollections.observableArrayList("Food", "Communication", "Charges"));
        Label dateLabel = new Label("Date");
        datePicker = new DatePicker();
        saveExpenseButton = new Button("Save expense");

        addExpensePanel.addRow(0, title);
        addExpensePanel.addRow(1, amountLabel, amountField);
        addExpensePanel.addRow(2, categoryLabel, categoryBox);
        addExpensePanel.addRow(3,dateLabel, datePicker);
        addExpensePanel.addRow(4,saveExpenseButton);

        return addExpensePanel;
    }

    private String getAmount() {
        return amountField.getText();
    }

    private String getCategory() {
        return categoryBox.getValue();
    }

    private String getDate() {
        LocalDate date = datePicker.getValue();
        String dateString = String.valueOf(date);
        System.out.println(dateString);
        return dateString;
    }
}
