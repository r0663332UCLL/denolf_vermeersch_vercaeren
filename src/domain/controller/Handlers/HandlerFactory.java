package domain.controller.Handlers;

import domain.controller.ControllerException;
import domain.model.ApplicationService;
import domain.model.ModelException;

public class HandlerFactory {
    public ActionHandler getHandler(String name, ApplicationService service){
        ActionHandler handler = null;
        Class handlerClass = null;
        try {
            handlerClass = Class.forName("domain.controller.Handlers." + name + "Handler");
            Object handlerObject = handlerClass.newInstance();
            handler = (ActionHandler) handlerObject;
            handler.setService(service);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
            throw new ControllerException(e.getMessage());
        }

        return handler;
    }
}
