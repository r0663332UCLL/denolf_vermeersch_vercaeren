package domain.model;

import java.util.ArrayList;

public class Test {
    private ArrayList<Question> questions;
    private ArrayList<String> userAnswers;
    private FeedbackBehaviour feedbackBehaviour;
    private int questionCounter = 0;

    public Test(ArrayList<Question> questions, String feedbackBehaviour){
        setQuestions(questions);
        setFeedbackBehaviour(feedbackBehaviour);
    }
    private void setFeedbackBehaviour(String type){
        FeedbackFactory feedbackFactory = new FeedbackFactory();
        feedbackBehaviour =  feedbackFactory.createFeedbackBehaviour(type, this);
    }

    private void setQuestions(ArrayList<Question> questions){
        if(questions != null){
            if (!questions.isEmpty()) {
                this.questions = questions;
            }
        } else throw new ModelException("the questions list cannot be empty");
    }
    public Question next(){
        try{
            Question question = questions.get(questionCounter);
            questionCounter++;
            return question;
        } catch (NullPointerException e){
            throw new ModelException("You are out of questions");
        }
    }

    public boolean hasQuestions(){
        Boolean hasquestions = false;
        if(questionCounter + 1 <= questions.size()){
            hasquestions = true;
        }
        return hasquestions;
    }

    public ArrayList<Question> getQuestions(){
        return this.questions;
    }

    public String getFeedback(){
        return feedbackBehaviour.getFeedback(this);
    }

    public ArrayList<String> getUserAnswers(){
        return userAnswers;
    }

    public void setUserAnswers(ArrayList<String> userAnswers){
        if(userAnswers != null && userAnswers.size() == questions.size()){
            this.userAnswers = userAnswers;
        } else throw new ModelException("the answers list cannot be empty or have a different number of answers than there are questions");
    }
}
