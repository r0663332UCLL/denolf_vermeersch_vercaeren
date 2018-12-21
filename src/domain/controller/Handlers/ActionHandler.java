package domain.controller.Handlers;

import domain.model.ApplicationService;

import java.util.ArrayList;

public abstract class ActionHandler {
    private ApplicationService service;

    public void setService(ApplicationService service) {
        this.service = service;
    }
    public ApplicationService getService() {
        return this.service;
    }

    public abstract Object HandleRequest(ArrayList<Object> parameters);
}
