package domain.controller;

import domain.controller.Handlers.HandlerFactory;
import domain.model.ApplicationService;
import domain.model.Category;

import java.util.ArrayList;

public class Controller {
    private ApplicationService service = new ApplicationService();
    private HandlerFactory factory = new HandlerFactory();
    public Object doAction (String action, ArrayList<Object> parameters) {
        if (action == null) {
            throw new ControllerException("action cannot be empty");
        }
        return factory.getHandler(action, service).HandleRequest(parameters);
    }
}
