package app.model;

import app.controller.MainController;
import app.dao.Category;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import util.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MainModel {

    private MainController mainController;

    public MainModel(){}

    public void saveExpense() throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        String sql = "INSERT INTO EXPENSES (AMOUNT, CATEGORY, DATE) VALUES (" +
                mainController.getExpenseAmount() + ", '" +
                mainController.getExpenseCategory() + "', '" +
                mainController.getExpenseDate() + "')";

        connection = JDBCUtil.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.executeUpdate(sql);
        connection.close();
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }

    public ObservableList<Category> getActiveCategories() throws SQLException {
        Connection connection;
        PreparedStatement preparedStatement;
        ObservableList<Category> categories = FXCollections.observableArrayList();

        String sql = "SELECT CATEGORY FROM CATEGORIES WHERE ACTIVE = 1";
        connection = JDBCUtil.getConnection();
        preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery(sql);
        while (resultSet.next()){
            categories.add(new Category(new SimpleStringProperty(resultSet.getString("CATEGORY")),
                                        new SimpleStringProperty("1")));
        }
        connection.close();

        return categories;
    }
}
