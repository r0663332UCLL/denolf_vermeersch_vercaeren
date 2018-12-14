package domain.model;

import domain.model.ApplicationService;
import domain.model.Handlers.HandlerFactory;

public class Controller {
    private ApplicationService service = new ApplicationService();
    private HandlerFactory factory = new HandlerFactory();
    public void DoAction (String action) {
        if (action == null) {
            throw new ModelException("action cannot be empty");
        }
        factory.getHandler(action, service).HandleRequest();
    }
}
