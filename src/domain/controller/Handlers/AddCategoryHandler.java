package domain.controller.Handlers;

import domain.model.Category;

import java.util.ArrayList;

public class AddCategoryHandler extends ActionHandler {

    @Override
    //voor UI: in parameters eerst naam, dan description en dan de oudercategorie. Indien er geen oudercategorie is, laatste element in lijst null maken
    public Object HandleRequest(ArrayList<Object> parameters) {
        String name = (String) parameters.get(0);
        String description = (String) parameters.get(1);
        Category masterCategory = (Category) parameters.get(2);
        Category newCategory = new Category(name, description, masterCategory);
        getService().addCategory(newCategory);
        return null;
    }
}
