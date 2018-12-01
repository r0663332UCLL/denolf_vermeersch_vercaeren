package domain.db;

import domain.model.Question;
import domain.model.YesNoQuestion;

import java.util.ArrayList;

public class DbHelper {

    public static Question stringToQuestion(String question) {
        String[] separated = null;
        char separator = ';';
        ArrayList<String> statements = new ArrayList<>();
        String title = null;
        String feedback = null;
        String category = null;
        String type = null;

        if (!question.equals(null)) {
            separated = question.split("[" + separator + "]");

            for (int i = 1; i <= separated.length -3; i++) {
                statements.add(separated[i]);
            }

            title = separated[0];
            feedback = separated[separated.length - 3];
            category = separated[separated.length - 2]
            type = separated[separated.length -1];

            if (type.equals("domain.model.YesNoQuestion")) {
                Question question1 = new YesNoQuestion(title, statements, feedback, category);
            }

        } else {
            throw new DbException("question is null");
        }
        return null;
    }
}
