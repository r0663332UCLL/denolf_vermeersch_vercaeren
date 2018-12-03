package domain.db;

import domain.model.Category;

import java.util.ArrayList;

public interface CategoryDb {

    void addCategory(Category category);
    void removeCategory(Category category);
    ArrayList<String> getCategories();

}
