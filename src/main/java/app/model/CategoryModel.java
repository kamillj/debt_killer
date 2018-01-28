package app.model;

import app.controller.CategoryController;
import app.dao.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CategoryModel {

    private CategoryController categoryController;

    public CategoryModel(){}

    public void setCategoryController(CategoryController categoryController){
        this.categoryController = categoryController;
    }

    public ObservableList<Category> readCategories() throws SQLException {
        ObservableList<Category> categories = FXCollections.observableArrayList();
        Connection connection;
        PreparedStatement preparedStatement;

        String sql = "SELECT CATEGORY FROM CATEGORIES";

        connection = JDBCUtil.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);

        while (resultSet.next()){
            categories.add(new Category(new SimpleStringProperty(resultSet.getString("CATEGORY"))));
        }

        return categories;
    }
}
