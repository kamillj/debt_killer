package app.dao;

import javafx.beans.property.SimpleStringProperty;

public class Category {

    private final SimpleStringProperty categoryName;

    public Category(SimpleStringProperty categoryName) {
        this.categoryName = categoryName;
    }

    public SimpleStringProperty getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }
}
