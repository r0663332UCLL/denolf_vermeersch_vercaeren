package domain.model;

import java.util.ArrayList;

public class ScoreBehaviour implements FeedbackBehaviour {

    @Override
    public String getFeedback(Test test) {
        int questionCount = 0;
        int correctAnswerCount = 0;
        ArrayList<String> answers = test.getUserAnswers();
        ArrayList<Question> questions = test.getQuestions();
        for (int i = 0; i < questions.size(); i++){
            Question currentQuestion = questions.get(i);
            String currentAnswer = answers.get(i);
            questionCount++;
            if(currentQuestion.isCorrectStatement(currentAnswer)){
                correctAnswerCount++;
            }
        }

        return "out of the " + questionCount + " questions, " + correctAnswerCount + " answers were correct";

    }
}
