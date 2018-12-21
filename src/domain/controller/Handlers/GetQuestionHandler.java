package domain.controller.Handlers;

import java.util.ArrayList;

public class GetQuestionHandler extends ActionHandler {
    @Override
    public Object HandleRequest(ArrayList<Object> parameters) {
        return getService().getQuestions();
    }
}
