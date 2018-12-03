package domain.model;

import domain.db.CategoryDb;
import domain.db.CategoryDbInFile;
import domain.db.QuestionDb;
import domain.db.QuestionDbInFile;

import java.util.ArrayList;

public class ApplicationService {

    private CategoryDb categoryDb;
    private QuestionDb questionDb;

    public ApplicationService(){
        categoryDb = new CategoryDbInFile();
        questionDb = new QuestionDbInFile();
    }

    public void AddCategory(Category category){
        categoryDb.addCategory(category);
    }

    public void removeCategory(Category category){
        categoryDb.removeCategory(category);
    }

    public ArrayList<Category> getCategories(){
        return categoryDb.getCategories();
    }

    public void addQuestion(Question question){
        questionDb.addQuestion(question);
    }

    public void removeQuestion(Question question){
        questionDb.removeQuestion(question);
    }

    public ArrayList<Question> getQuestions(){
        return questionDb.getQuestions();
    }

    //voorlopig nog hardcoded behaviour, later met text file
    public Test generateTest(){
        //TODO DIT MOET THOMAS IMPLEMENTEREN WANT IK SNAP NIET HOE WRITERS EN FILES EN SHIT WERKEN
        Test test = new Test(questionDb.getQuestions(), "Score");
        return test;
    }

}
