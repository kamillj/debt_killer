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

        String sql = "SELECT CATEGORY, ACTIVE FROM CATEGORIES";

        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);

        while (resultSet.next()){
            categories.add(new Category(resultSet.getString("CATEGORY"), resultSet.getString("ACTIVE").equals("1")));
        }
        connection.close();

        return categories;
    }

    public void saveCategory(String category, boolean isActive) throws SQLException {
        String isActiveString;
        if(isActive) isActiveString = "1";
        else  isActiveString = "0";

        String sql = "INSERT INTO CATEGORIES (CATEGORY, ACTIVE) VALUES ( '" + category + "'," + isActiveString + ")";
        Connection connection = JDBCUtil.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(sql);
        connection.close();
    }

    public void updateCategories(ObservableList<Category> categories) throws SQLException {
        Connection connection = JDBCUtil.getConnection();
        String sql = "DELETE FROM CATEGORIES";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(sql);

        int counter = 0;
        while(counter < categories.size())
        {
            sql = "INSERT INTO CATEGORIES (CATEGORY, ACTIVE) VALUES ( '" +
                    categories.get(counter).getCategoryName() + "'," +
                    categories.get(counter).getIsActive() + ")";
            preparedStatement.executeUpdate(sql);
            counter++;
        }
        connection.close();
    }
}
