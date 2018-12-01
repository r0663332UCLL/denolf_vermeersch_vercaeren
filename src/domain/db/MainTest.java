package domain.db;

import domain.model.Category;
import domain.model.Question;
import domain.model.YesNoQuestion;

import java.util.ArrayList;

public class MainTest {
    public static void main(String args[]) {
        Question vraag = new YesNoQuestion("vraag", new ArrayList<String>(), "feedback", new Category("category", "description"));
        System.out.println(vraag.toString());
    }
}
