package domain.model;

import java.util.ArrayList;

public interface QuestionDb {

    void addQuestion(Question question);
    void removeQuestion(String question);
    ArrayList<Question> getQuestions();

}
