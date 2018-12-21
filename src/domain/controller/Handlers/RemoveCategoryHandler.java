package domain.controller.Handlers;

import domain.model.Category;

import java.util.ArrayList;

public class RemoveCategoryHandler extends ActionHandler {
    @Override
    public Object HandleRequest(ArrayList<Object> parameters) {
        Category categoryToRemove = (Category) parameters.get(0);
        getService().removeCategory(categoryToRemove);
        return null;
    }
}
