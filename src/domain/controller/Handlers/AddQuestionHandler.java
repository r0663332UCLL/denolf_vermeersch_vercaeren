package domain.controller.Handlers;

import domain.model.MultipleChoiceQuestion;
import domain.model.Question;
import domain.model.YesNoQuestion;

import java.util.ArrayList;

public class AddQuestionHandler extends ActionHandler {
    @Override
    //voor UI: eerst het type vraag, dan de vraag, dan een arraylist van Strings met statements, dan feedback en dan de categorie
    public Object HandleRequest(ArrayList<Object> parameters) {
        String type = (String) parameters.get(0);
        String question = (String) parameters.get(1);
        ArrayList<String> statements = (ArrayList<String>) parameters.get(2);
        String feedback = (String) parameters.get(3);
        String category = (String) parameters.get(4);
        Question newQuestion = null;
        if (type.equals("YesNoQuestion")){
            newQuestion = new YesNoQuestion(question, statements, feedback, category);
        } else if (type.equals("MultipleChoiceQuestion")){
            newQuestion = new MultipleChoiceQuestion(question, statements, feedback, category);
        }
        getService().addQuestion(newQuestion);
        return null;
    }
}
