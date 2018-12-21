package domain.controller;

import domain.controller.Handlers.HandlerFactory;
import domain.model.ApplicationService;
import java.util.ArrayList;

public class Controller {
    private ApplicationService service = new ApplicationService();
    private HandlerFactory factory = new HandlerFactory();
    public void DoAction (String action, ArrayList<Object> parameters) {
        if (action == null) {
            throw new ControllerException("action cannot be empty");
        }
        factory.getHandler(action, service).HandleRequest(parameters);
    }
}
