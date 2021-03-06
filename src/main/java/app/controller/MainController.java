package app.controller;

import app.dao.Category;
import app.dao.Expense;
import app.model.MainModel;
import javafx.collections.ObservableList;

import java.sql.SQLException;

public class MainController {

    private String expenseAmount;
    private String expenseCategory;
    private String expenseDate;

    private MainModel mainModel;

    public MainController(MainModel mainModel){
        this.mainModel = mainModel;
    }

    public void sendExpenseToSave(String expenseAmount, String expenseCategory, String expenseDate) throws SQLException {
        this.expenseAmount = expenseAmount;
        this.expenseCategory = expenseCategory;
        this.expenseDate = expenseDate;
        mainModel.saveExpense();
    }

    public String getExpenseAmount() {
        return expenseAmount;
    }

    public String getExpenseCategory() {
        return expenseCategory;
    }

    public String getExpenseDate() {
        return expenseDate;
    }

    public ObservableList<Category> getActiveCategories() throws SQLException {
        return mainModel.getActiveCategories();
    }

    public ObservableList<Expense> getExpenseData() throws SQLException {
        return mainModel.getExpenseData();
    }
}
