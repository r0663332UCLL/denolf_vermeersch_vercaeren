package domain.model.Handlers;

import domain.model.ApplicationService;
import domain.model.ModelException;

public class HandlerFactory {
    public ActionHandler getHandler(String key, ApplicationService service) {
        return getController(key, service);
    }

    private ActionHandler getController(String handlerName, ApplicationService service) {
        ActionHandler handler = null;
        try {
            Class<?> handlerClass = Class.forName("ui.controller." + handlerName + "Handler");
            Object handlerObject = handlerClass.newInstance();
            handler = (ActionHandler) handlerObject;
            handler.setService(service);
        } catch (Exception e) {
            throw new ModelException("The requested page doesn't exist");
        }
        return handler;
    }
}
