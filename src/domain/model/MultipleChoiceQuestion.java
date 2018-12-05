package domain.model;

import java.util.ArrayList;

public class MultipleChoiceQuestion extends Question {
    public MultipleChoiceQuestion(String question, ArrayList<String> statements, String feedback, String category) {
        super(question, statements, feedback, category);
    }
}
