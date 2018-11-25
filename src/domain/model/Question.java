package domain.model;

import java.util.ArrayList;

abstract class Question {
    private String question;
    private ArrayList<String> statements;
    private String feedback;

    public Question(String question, ArrayList<String> statements, String feedback) {
        setQuestion(question);
        setStatements(statements);
        setFeedback(feedback);
    }

    public String getQuestion() {
        return question;
    }

    private void setQuestion(String question) {
        if (question.isEmpty()) {
            throw new ModelException("Cannot make question with empty question string");
        }
        this.question = question.trim();
    }

    public ArrayList<String> getStatements() {
        return statements;
    }

    private void setStatements(ArrayList<String> statements) {
        this.statements = statements;
    }

    public String getFeedback() {
        return feedback;
    }

    private void setFeedback(String feedback) {
        if (question.isEmpty()) {
            throw new ModelException("Cannot make question with empty question string");
        }
        this.feedback = feedback.trim();
    }

    public String getCorrectStatement() {
        if (statements.get(0).isEmpty()) {
            return "empty";
        } else {
            return statements.get(0);
        }
    }
}
