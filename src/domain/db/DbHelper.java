package domain.db;

import domain.model.Category;
import domain.model.MultipleChoiceQuestion;
import domain.model.Question;
import domain.model.YesNoQuestion;

import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
import java.util.Set;

import java.io.*;
import java.util.ArrayList;

public class DbHelper {

    public static Question stringToQuestion(String question) {
        String[] separated;
        char separator = ';';
        ArrayList<String> statements = new ArrayList<>();
        String title;
        String feedback;
        String category;
        String type = null;

        if (question != null) {
            separated = question.split("[" + separator + "]");

            for (int i = 1; i <= separated.length -3; i++) {
                statements.add(separated[i]);
            }

            title = separated[0];
            feedback = separated[separated.length - 3];
            category = separated[separated.length - 2];
            type = separated[separated.length -1];

            if (type.equals("domain.model.YesNoQuestion")) {
                return new YesNoQuestion(title, statements, feedback, category);
            } else if(type.equals("domain.model.MultipleChoiceQuestion")) {
               return new MultipleChoiceQuestion(title, statements, feedback, category);
            }

        } else {
            throw new DbException("question is null");
        }
        return null;
    }

    public static Category stringToCategory(String category) {
        String[] separated = null;
        char separator = ';';

        String title = null;
        String description = null;

        if (category != null) {
            separated = category.split("[" + separator + "]");

            title = separated[0];
            description = separated[1];

            return new Category(title, description);

        } else {
            throw new DbException("question is null");
        }
    }

    public static Category stringToCategoryWithMain(String category, Category maincat) {
        String[] separated = null;
        char separator = ';';

        String title = null;
        String description = null;
        String mainCat = null;

        if (category != null) {
            separated = category.split("[" + separator + "]");

            title = separated[0];
            description = separated[1];

            return new Category(title, description, maincat);

        } else {
            throw new DbException("question is null");
        }
    }

    private static String getMainCategory(String category) {
        String[] separated = null;
        char separator = ';';

        if (category != null) {
            separated = category.split("[" + separator + "]");

            return separated[2];

        } else {
            throw new DbException("question is null");
        }
    }

    public static HashMap<Question, Category> LoadAllToMemory(ArrayList<String> questions, ArrayList<String> categories) {

        ArrayList<Category> tmpCat = new ArrayList<>();
        ArrayList<Question> tmpQue = new ArrayList<>();
        HashMap<Question, Category> hmap = new HashMap<>();

        //get all main categories first
        for (int i = 0; i < categories.size(); i++) {
            if(!hasMainCategory(categories.get(i))) {
                tmpCat.add(stringToCategory(categories.get(i)));
            }
        }

        //get all categories and match them with their main categories

        for (int y = 0; y < categories.size(); y++) {
            if(hasMainCategory(categories.get(y))) {
                Category cat1 = null;
                for (int x = 0; x < tmpCat.size(); x++) {
                    if (tmpCat.get(x).getTitle().equals(getMainCategory(categories.get(y)))) {
                        cat1 = tmpCat.get(x);
                    }
                }
                tmpCat.add(stringToCategoryWithMain(categories.get(y), cat1));
            }
        }

        for (int z = 0; z < questions.size(); z++) {

        }
        return hmap;
    }



    public static boolean hasMainCategory(String category) {
        String[] separated = null;
        char separator = ';';

        if (!(category == null)) {
            separated = category.split("[" + separator + "]");

            if (separated[separated.length -1].equals(null)) {
                return false;
            } else {
                return true;
            }

        } else {
            throw new DbException("question is null");
        }
    }
}

