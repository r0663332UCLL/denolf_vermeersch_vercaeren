package domain.model;

public class Category {
    private String title;
    private String description;
    private Category mainCategory;

    public Category(String title, String description, Category mainCategory) {
        setTitle(title);
        setDescription(description);
        setMainCategory(mainCategory);
    }

    public Category(String title, String description) {
        this(title, description, null);
    }

    private void setTitle(String title) {
        if (title.trim().isEmpty()) {
            throw new ModelException("cannot make category with empty title");
        }
        this.title = title;
    }

    private void setDescription(String description) {
        if (description.trim().isEmpty()) {
            throw new ModelException("cannot make category with empty description");
        }
        this.description = description;
    }

    private void setMainCategory(Category mainCategory) {
        this.mainCategory = mainCategory;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Category getMainCategory() {
        return mainCategory;
    }

    @Override
    public String toString() {
        String str;
        String mainCat;

        if ((getMainCategory()) == null) {
            mainCat = "null;";
        } else {
            mainCat = getMainCategory().title;
        }
        str = getTitle() + ";" + getDescription() + ";" + mainCat;
        return str;
    }
}
