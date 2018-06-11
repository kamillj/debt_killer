package app.dao;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Category {

    private final StringProperty categoryName;
    private final BooleanProperty isActive;

    public Category(String categoryName, boolean isActive) {
        this.categoryName = new SimpleStringProperty(categoryName);
        this.isActive = new SimpleBooleanProperty(isActive);
    }

    public StringProperty categoryNameProperty() {
        return categoryName;
    }

    public BooleanProperty isActiveProperty() {
        return isActive;
    }

    public String getCategoryName() {
        return categoryName.getValue();
    }

    public boolean getIsActive() {
        return isActive.getValue();
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.setValue(categoryName);
    }

    public void setCategoryActive(boolean isActive) {
        this.isActive.setValue(isActive);
    }
}
