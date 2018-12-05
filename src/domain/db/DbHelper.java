package domain.db;

import domain.model.Category;
import domain.model.Question;
import java.util.HashMap;
import java.util.ArrayList;

public class DbHelper {

    public static HashMap<Question, Category> LoadAllToMemory(ArrayList<Question> questions, ArrayList<Category> categories) {

        HashMap<Question, Category> hmap = new HashMap<>();

        for (int i = 0; i < questions.size(); i++) {
            Category tmpCat = null;
            for (int x = 0; x < categories.size(); x++) {
                if (questions.get(i).getCategory().equals(categories.get(x).getTitle())) {
                    tmpCat = categories.get(x);
                }
            }
            hmap.put(questions.get(i), tmpCat);
        }

        return hmap;
    }




}

