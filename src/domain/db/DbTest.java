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
        ArrayList<Category> cats = service.getCategories();
        ArrayList<Question> qus = service.getQuestions();
        for (int i = 0; i < cats.size(); i++) {
            System.out.println(cats.get(i).toString());
        }

        for (int i = 0; i < qus.size(); i++) {
            System.out.println(qus.get(i).toString());
        }

        service.removeCategory(subcat);
        service.removeQuestion(q1);
        System.out.println("verwijdering in orde");
        cats = service.getCategories();
        qus = service.getQuestions();

        for (int i = 0; i < cats.size(); i++) {
            System.out.println(cats.get(i).toString());
        }

        for (int i = 0; i < qus.size(); i++) {
            System.out.println(qus.get(i).toString());
        }

        System.out.println(service.getMappedData());
    }
}