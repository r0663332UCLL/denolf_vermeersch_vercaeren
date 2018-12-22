package domain.model;

import java.util.ArrayList;

public class VerboseBehaviour implements FeedbackBehaviour {

    @Override
    public String getFeedback(Test test) {
        String feedBack = "";
        ArrayList<String> answers = test.getUserAnswers();
        ArrayList<Question> questions = test.getQuestions();
        for (int i = 0; i < questions.size(); i++){
            Question currentQuestion = questions.get(i);
            String currentAnswer = answers.get(i);
            if (!currentQuestion.isCorrectStatement(currentAnswer)){
                feedBack += currentQuestion.getFeedback() + "\n";
            }
        }
        if (feedBack.trim().isEmpty()){
            feedBack = "Proficiat, alles juist!";
        }

        return feedBack;
    }
}
