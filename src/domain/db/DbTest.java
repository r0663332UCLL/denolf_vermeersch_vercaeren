package domain.db;

import domain.model.ApplicationService;
import domain.model.Category;
import domain.model.Question;
import domain.model.YesNoQuestion;

import java.util.ArrayList;

public class DbTest {
    public static void main(String args[]) {
        ArrayList<String> statements1 = new ArrayList<>();
        statements1.add("yes yes yes");
        statements1.add("no no no");

        Category maincat = new Category("ooo", "object georienteerd programmeren");
        Category subcat = new Category("patronen", "patronen van ooo", maincat);
        Question q1 = new YesNoQuestion("vraag1", statements1, "da kan beter eh vriend", "ooo");
        Question q2 = new YesNoQuestion("vraag", statements1, "was da alles?", "patronen");
        ApplicationService service = new ApplicationService();
        service.addQuestion(q1);
        service.addQuestion(q2);
        service.addCategory(maincat);
        service.addCategory(subcat);
    }
}