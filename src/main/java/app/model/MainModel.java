package app.model;

import app.controller.MainController;
import util.db.JDBCUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
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
    }

    public void setMainController(MainController mainController) {
        this.mainController = mainController;
    }
}
