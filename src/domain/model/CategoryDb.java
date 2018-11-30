package domain.model;

import java.util.ArrayList;

public interface CategoryDb {

    void addCategory(Category category);
    void removeCategory(String title);
    ArrayList<Category> getCategories();

}
