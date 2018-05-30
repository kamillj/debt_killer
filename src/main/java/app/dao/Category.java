package app.dao;

import javafx.beans.property.SimpleStringProperty;

public class Category {

    private final SimpleStringProperty categoryName;
    private final SimpleStringProperty isActive;

    public Category(SimpleStringProperty categoryName, SimpleStringProperty isActive) {
        this.categoryName = categoryName;
        this.isActive = isActive;
    }

    public SimpleStringProperty getCategoryNameStringProperty() {
        return categoryName;
    }

    public SimpleStringProperty getIsActiveStringProperty() {
        return isActive;
    }

    public String getCategoryName() {
        return categoryName.getValue();
    }

    public String getIsActive() {
        return isActive.getValue();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public void setCategoryActive(String isActive) {
        this.isActive.setValue(isActive);
    }
}
