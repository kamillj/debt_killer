package app.view;

import app.ViewManager;
import app.controller.MainController;
import app.dao.Category;
import app.dao.Expense;
import app.view.table.ExpenseTableView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    //AddExpensePanel
    private Text title;
    private Label amountLabel;
    private Label categoryLabel;
    private Label dateLabel;
    private TextField amountField;
    private ChoiceBox<String> categoryBox;
    private DatePicker datePicker;
    private Button saveExpenseButton;
    private GridPane addExpensePanel;
    //Toolbar
    private Button categoryButton;
    private HBox toolbar;
    //ExpensesPanel
    private HBox expensePanel;
    private TableView<Expense> expenseTableView;
    //Data
    private ObservableList<String> categoriesString;

    private BorderPane mainDialog;

    private DoubleValid doubleValid = new DoubleValid();
    private Alert error = new Alert(Alert.AlertType.ERROR, "Please insert correct amount");
    private Alert confirmation = new Alert(Alert.AlertType.CONFIRMATION, "Saved successfully");

    private MainController mainController;

    public MainView(MainController mainController) {
        this.mainController = mainController;
        fillData();
        createAndLayoutControls();
        createAndConfigureView();
        addListeners();
    }

    public BorderPane asParent() {
        return mainDialog;
    }

    private void createAndConfigureView() {
        mainDialog = new BorderPane();
        mainDialog.setRight(appendAddExpensePanel());
        mainDialog.setTop(appendToolbar());
        mainDialog.setCenter(appendExpensePanel());
    }

    private void createAndLayoutControls() {
        //AddExpensePanel
        title = new Text("Add expense");
        title.setTextAlignment(TextAlignment.CENTER);
        title.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        amountLabel = new Label("Amount");
        amountField = new TextField("");
        categoryLabel = new Label("Category");
        categoryBox = new ChoiceBox<>();
        categoryBox.setItems(categoriesString);
        categoryBox.getSelectionModel().selectFirst();
        dateLabel = new Label("Date");
        datePicker = new DatePicker();
        datePicker.setValue(LocalDate.now());
        saveExpenseButton = new Button("Save expense");

        //Toolbar
        categoryButton = new Button("Categories");
    }

    private void addListeners() {
        saveExpenseButton.setOnAction((ActionEvent event) -> {
            try {
                String amount = getAmount().trim();
                if(amount.contains(",")) amount = amount.replaceAll(",",".");
                if(doubleValid.isDouble(amount)) confirmation.showAndWait();
                else error.showAndWait();
                mainController.sendExpenseToSave(amount, getCategory(), getDate());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
        categoryButton.setOnAction((ActionEvent event) -> ViewManager.loadCategoryView());
    }

    private GridPane appendAddExpensePanel() {
        addExpensePanel = new GridPane();

        addExpensePanel.addRow(0, title);
        addExpensePanel.addRow(1, amountLabel, amountField);
        addExpensePanel.addRow(2, categoryLabel, categoryBox);
        addExpensePanel.addRow(3, dateLabel, datePicker);
        addExpensePanel.addRow(4, saveExpenseButton);

        addExpensePanel.setStyle("-fx-background-color: DAE6F3;");
        addExpensePanel.setAlignment(Pos.CENTER);

        return addExpensePanel;
    }

    private HBox appendToolbar(){
        toolbar = new HBox();
        toolbar.getChildren().add(categoryButton);
        toolbar.setStyle("-fx-background-color: #708090;");

        return toolbar;
    }

    private HBox appendExpensePanel()
    {
        expensePanel = new HBox();
        expenseTableView = new ExpenseTableView();
        try {
            expenseTableView.setItems(mainController.getExpenseData());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        expensePanel.getChildren().add(expenseTableView);

        return expensePanel;
    }

    private void fillData() {
        categoriesString = FXCollections.observableArrayList();
        ObservableList<Category> categories = null;
        try {
            categories = mainController.getActiveCategories();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        assert categories != null;
        for (Category category : categories) {
            categoriesString.add(category.categoryNameProperty().getValue());
        }
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
