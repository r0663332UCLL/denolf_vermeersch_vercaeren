package domain.model;

public class QuestionFactory {
    private static QuestionFactory instance = null;
    protected QuestionFactory() {

    }

    public static QuestionFactory getInstance() {
        if (instance == null) {
            instance = new QuestionFactory();
        }
        return instance;
    }
    public Question CreateQuestion(String questionType, Question question) {
        if(questionType == null) {
            return null;
        }

        if(questionType.equalsIgnoreCase("YESNOQUESTION")) {
            return new YesNoQuestion(question.getQuestion(), question.getStatements(), question.getFeedback(), question.getCategory());
        } else if(questionType.equalsIgnoreCase("MULTIPLECHOICEQUESTION")) {
            return new MultipleChoiceQuestion(question.getQuestion(), question.getStatements(), question.getFeedback(), question.getCategory());

        }

        return null;
    }

}
