package app.view;

import app.controller.CategoryController;
import app.dao.Category;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.sql.SQLException;


public class CategoryView {

    private Label tableTitle;
    private TableColumn<Category, String> categoryColumn;
    private TableColumn<Category, Boolean> activeColumn;
    private TableView<Category> categoryTable;
    private TextField categoryField;
    private CheckBox isActiveCheck;
    private Button addButton;
    private GridPane categoryDialog;

    private Stage stage;

    private CategoryController categoryController;

    public CategoryView(CategoryController categoryController) {
        this.categoryController = categoryController;
        createAndLayoutControls();
        createAndConfigureView();
        addListeners();
        fillData();
    }

    public GridPane asParent() {
        return categoryDialog;
    }

    private void createAndLayoutControls() {
        tableTitle = new Label("Define categories");
        categoryTable = new TableView<>();
        categoryField = new TextField();
        isActiveCheck = new CheckBox("active");
        addButton = new Button("Add");
        categoryColumn = new TableColumn<>("Category");
        activeColumn = new TableColumn<>("Active");

        categoryColumn.setMinWidth(200);
        activeColumn.setMinWidth(100);
        categoryColumn.setCellFactory(TextFieldTableCell.forTableColumn());
        activeColumn.setCellFactory(activeCell -> new CheckBoxTableCell<>());
        categoryTable.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        categoryTable.setEditable(true);
        categoryTable.getColumns().addAll(categoryColumn, activeColumn);

    }

    private void createAndConfigureView() {
        categoryDialog = new GridPane();
        HBox addingPanel = new HBox();
        categoryDialog.setPadding(new Insets(10));
        categoryDialog.setVgap(10);
        categoryDialog.setHgap(5);
        addingPanel.setSpacing(5);
        addingPanel.setAlignment(Pos.CENTER);

        categoryDialog.addRow(0, tableTitle);
        categoryDialog.addRow(1, categoryTable);
        addingPanel.getChildren().addAll(categoryField, isActiveCheck, addButton);
        categoryDialog.addRow(2, addingPanel);
    }

    private void addListeners() {
        categoryColumn.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow())
                .setCategoryName(event.getNewValue()));
        activeColumn.setOnEditCommit(event -> event.getTableView().getItems().get(event.getTablePosition().getRow())
                .setCategoryActive(event.getNewValue()));
        addButton.setOnAction(event -> {
            try {
                categoryController.saveCategory(categoryField.getText(), isActiveCheck.isSelected());
            } catch (SQLException e) {
                e.printStackTrace();
            }
            fillData();
        });
    }

    private void fillData(){
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().categoryNameProperty());
        activeColumn.setCellValueFactory((cellData -> cellData.getValue().isActiveProperty()));
        try {
            categoryTable.setItems(categoryController.readCategories());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void setStage(Stage stage){
        this.stage = stage;
        this.stage.setOnCloseRequest(event -> {
            try {
                categoryController.updateCategories(categoryTable.getItems());
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }
}
