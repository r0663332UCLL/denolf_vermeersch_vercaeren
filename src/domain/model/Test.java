package domain.model;

import java.util.ArrayList;

public class Test {
    private ArrayList<Question> questions;
    private ArrayList<String> userAnswers;
    private FeedbackBehaviour feedbackBehaviour;

    public Test(ArrayList<Question> questions, String feedbackBehaviour){

    }

    public boolean hasQuestions(){
        return false;
    }

    public ArrayList<Question> getQuestions(){
        return null;
    }

    public String getFeedback(){
        return null;
    }

    public ArrayList<String> getUserAnswers(){
        return null;
    }
}
