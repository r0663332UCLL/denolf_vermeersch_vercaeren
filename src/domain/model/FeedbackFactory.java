package domain.model;

public class FeedbackFactory {

    public FeedbackBehaviour createFeedbackBehaviour(String type, Test test){

        FeedbackBehaviour feedbackBehaviour;
        Class feedbackClass;
        try {
            feedbackClass = Class.forName("domain.model." + type + "Behaviour");
            Object feedbackObject = feedbackClass.newInstance();
            feedbackBehaviour = (FeedbackBehaviour) feedbackObject;
        } catch (ClassNotFoundException | IllegalAccessException | InstantiationException e) {
            throw new ModelException(e.getMessage());
        }

        return feedbackBehaviour;
    }
}
