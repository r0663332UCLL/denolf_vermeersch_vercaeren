package domain.model.Handlers;

import domain.model.ApplicationService;
import domain.model.ModelException;

public class HandlerFactory {
    public ActionHandler getHandler(String name, ApplicationService service){
        ActionHandler handler = null;
        Class handlerClass = null;
        try {
            handlerClass = Class.forName("domain.model.Handlers." + name + "Handler");
            Object handlerObject = handlerClass.newInstance();
            handler = (ActionHandler) handlerObject;
            handler.setService(service);
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e){
            throw new ModelException(e.getMessage());
        }

        return handler;
    }
}
