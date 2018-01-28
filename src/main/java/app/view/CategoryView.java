package app.view;

import app.controller.CategoryController;
import app.dao.Category;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.GridPane;

import java.sql.SQLException;

public class CategoryView {

    private Label tableTitle;
    private TableView<Category> categoryTable;
    private GridPane categoryDialog;

    private CategoryController categoryController;

    public CategoryView(CategoryController categoryController) {
        this.categoryController = categoryController;
        createAndLayoutControls();
        createAndConfigureView();
        addListeners();
    }

    public GridPane asParent() {
        return categoryDialog;
    }

    private void createAndLayoutControls() {
        tableTitle = new Label("Define categories");
        categoryTable = new TableView<>();
        TableColumn<Category, String> categoryColumn = new TableColumn<>("Category");
        categoryTable.getColumns().add(categoryColumn);
        categoryColumn.setCellValueFactory(cellData -> cellData.getValue().getCategoryName());
        try {
            categoryTable.setItems(categoryController.readCategories());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createAndConfigureView() {
        categoryDialog = new GridPane();
        categoryDialog.addRow(0, tableTitle);
        categoryDialog.addRow(1, categoryTable);
    }

    private void addListeners() {
    }
}
