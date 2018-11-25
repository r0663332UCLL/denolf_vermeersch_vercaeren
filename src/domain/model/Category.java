package domain.model;

class Category {
    private String title;
    private String description;
    private Category mainCategory;

    Category(String title, String description, Category mainCategory) {
        setTitle(title);
        setDescription(description);
        setMainCategory(mainCategory);
    }

    Category(String title, String description) {
        this(title, description, null);
    }

    private void setTitle(String title) {
        if (title.isEmpty()) {
            throw new ModelException("cannot make category with empty title");
        }
        this.title = title;
    }

    private void setDescription(String description) {
        if (description.isEmpty()) {
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
}
