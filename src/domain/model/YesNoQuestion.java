package domain.model;

import java.util.ArrayList;

public class YesNoQuestion extends Question {
    public YesNoQuestion(String question, ArrayList<String> statements, String feedback, String category) {
        super(question, statements, feedback, category);
    }
}
