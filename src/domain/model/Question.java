package domain.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public abstract class Question {
    private String question;
    private ArrayList<String> statements;
    private String feedback;
    private String category;

    public Question(String question, ArrayList<String> statements, String feedback, String category) {
        setQuestion(question);
        setStatements(statements);
        setFeedback(feedback);
        setCategory(category);
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
    public ArrayList<String> getRandomStatements(ArrayList<String> statements) {
        ArrayList<String> larray = statements;
        Collections.shuffle(larray);
        return larray;
    }

    private void setStatements(ArrayList<String> statements) {
        for (int i = 0; i < statements.size(); i++) {
            if (statements.get(i).contains(";")) {
                throw new ModelException("statement cannot contain a ';'");
            }
        }
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

    public String getCategory() {
        return category;
    }

    private void setCategory(String category) {
        if (category == null) {
            throw new ModelException("Cannot make a question with empty category");
        }
        this.category = category;
    }

    //zolang we het effectieve juiste antwoord niet nodig hebben best private laten
    private String getCorrectStatement() {
        if (statements.get(0).isEmpty()) {
            return "empty";
        } else {
            return statements.get(0);
        }
    }

    boolean isCorrectStatement(String statement) {
        if (statement != null) {
            return statement.equals(getCorrectStatement());
        } else {
            return false;
        }

    }


    @Override
    public String toString() {
        String str;
        String statementString = new String();

        for (String i : getStatements()) {
            statementString += i + ";";
        }
        if (statementString == null) {
            statementString = "null;";
        }
        str = getQuestion() + ";" + statementString + getFeedback() + ";" + getCategory() + ";" + this.getClass().getName();
        return str;
    }
}
