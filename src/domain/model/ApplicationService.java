package domain.model;

import domain.db.*;

import java.util.ArrayList;
import java.util.HashMap;

public class ApplicationService {

    private CategoryDb categoryDb;
    private QuestionDb questionDb;

    public ApplicationService(){
        categoryDb = new CategoryDbInFile();
        questionDb = new QuestionDbInFile();
    }

    public void addCategory(Category category){
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

    public HashMap<Question, Category> getMappedData() {
        return DbHelper.LoadAllToMemory(this.getQuestions(), this.getCategories());
    }
    //voorlopig nog hardcoded behaviour, later met text file
    public Test generateTest(){
        //TODO DIT MOET THOMAS IMPLEMENTEREN WANT IK SNAP NIET HOE WRITERS EN FILES EN SHIT WERKEN
        return new Test(questionDb.getQuestions(), "Score");
    }

}
