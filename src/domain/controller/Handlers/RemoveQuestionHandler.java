package domain.controller.Handlers;

import domain.model.Question;

import java.util.ArrayList;

public class RemoveQuestionHandler extends ActionHandler  {
    @Override
    public Object HandleRequest(ArrayList<Object> parameters) {

        Question questionToRemove = (Question) parameters.get(0);
        getService().removeQuestion(questionToRemove);
        return null;
    }
}
