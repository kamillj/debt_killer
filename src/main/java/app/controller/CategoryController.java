package app.controller;

import app.model.CategoryModel;
import app.dao.Category;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class CategoryController {

    private CategoryModel categoryModel;

    public CategoryController(CategoryModel categoryModel) {
        this.categoryModel = categoryModel;
    }

    public ObservableList<Category> readCategories() throws SQLException {
        return categoryModel.readCategories();
    }

    public void saveCategory(String category, boolean isActive) throws SQLException {
        categoryModel.saveCategory(category, isActive);
    }

    public void updateCategories(ObservableList<Category> categories) throws SQLException {
        categoryModel.updateCategories(categories);
    }
}
