package app.view.table;

import app.dao.Expense;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import java.util.Date;


public class ExpenseTableView extends TableView<Expense>{

    private TableColumn<Expense, String> categoryColumn;
    private TableColumn<Expense, Double> amoutColumn;
    private TableColumn<Expense, Date> dateColumn;


    public ExpenseTableView() {
        createAndConfigureControls();
        createAndConfigureView();
    }

    private void createAndConfigureControls()
    {
        categoryColumn = new TableColumn<>("Category");
        amoutColumn = new TableColumn<>("Amount");
        dateColumn = new TableColumn<>("Date");
        categoryColumn.setCellValueFactory(category -> category.getValue().categoryProperty());
        amoutColumn.setCellValueFactory(amount -> amount.getValue().amountProperty().asObject());
        dateColumn.setCellValueFactory(date -> date.getValue().dateProperty());
        this.getColumns().addAll(categoryColumn, amoutColumn, dateColumn);
    }

    private void createAndConfigureView() {
        categoryColumn.setMinWidth(200);
        amoutColumn.setMinWidth(100);
        dateColumn.setMinWidth(100);
    }
}
