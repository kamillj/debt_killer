package app.view;

import app.controller.MainController;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import valid.DoubleValid;

import java.sql.SQLException;
import java.time.LocalDate;

public class MainView {

    private Text title;
    private Label amountLabel;
    private Label categoryLabel;
    private Label dateLabel;
    private TextField amountField;
    private ChoiceBox<String> categoryBox;
    private DatePicker datePicker;
    private Button saveExpenseButton;
    private BorderPane mainDialog;

    private DoubleValid doubleValid = new DoubleValid();
    private Alert error = new Alert(Alert.AlertType.ERROR, "Error while saving data");
    private Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Saved successfully");

    private MainController mainController;

    public MainView(MainController mainController) {
        this.mainController = mainController;
        createAndLayoutControls();
        createAndConfigureView();
        addListeners();
    }

    public BorderPane asParent() {
        return mainDialog;
    }

    private void createAndConfigureView() {
        mainDialog = new BorderPane();
        GridPane addExpensePanel = appendAddExpensePanel();
        mainDialog.setRight(addExpensePanel);
    }

    private void createAndLayoutControls() {
        //AddExpensePanel
        title = new Text("Add expense");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        amountLabel = new Label("Amount");
        amountField = new TextField("");
        categoryLabel = new Label("Category");
        categoryBox = new ChoiceBox<>(FXCollections.observableArrayList("Food", "Communication", "Charges"));
        dateLabel = new Label("Date");
        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        saveExpenseButton = new Button("Save expense");
    }

    private void addListeners() {
        saveExpenseButton.setOnAction((ActionEvent event) -> {
            try {
                String amount = getAmount();
                if(amount.contains(",")) amount = amount.replaceAll(",",".");
                if(doubleValid.isDouble(amount)) confirmation.showAndWait();
                else error.showAndWait();
                mainController.sendExpenseToSave(amount, getCategory(), getDate());
            } catch (SQLException e) {
                e.printStackTrace();
                error.showAndWait();
            }
        });
    }

    private GridPane appendAddExpensePanel() {
        GridPane addExpensePanel = new GridPane();

        addExpensePanel.addRow(0, title);
        addExpensePanel.addRow(1, amountLabel, amountField);
        addExpensePanel.addRow(2, categoryLabel, categoryBox);
        addExpensePanel.addRow(3, dateLabel, datePicker);
        addExpensePanel.addRow(4, saveExpenseButton);

        addExpensePanel.setStyle("-fx-background-color: DAE6F3;");
        addExpensePanel.setAlignment(Pos.CENTER);

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
        return String.valueOf(date);
    }
}
