package domain.db;

import java.util.ArrayList;
import domain.model.Question;

public interface QuestionDb {

    void addQuestion(Question question);
    void removeQuestion(Question question);
    ArrayList<Question> getQuestions();

}
