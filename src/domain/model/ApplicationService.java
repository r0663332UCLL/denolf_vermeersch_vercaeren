package domain.model;

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

    public void removeCategory(String title){
        categoryDb.removeCategory(title);
    }

    public ArrayList<Category> getCategories(){
        return categoryDb.getCategories();
    }

    public void addQuestion(Question question){
        questionDb.addQuestion(question);
    }

    public void removeQuestion(String question){
        questionDb.removeQuestion(question);
    }

    public ArrayList<Question> getQuestions(){
        return questionDb.getQuestions();
    }

    public Test generateTest(){
        Test test = new Test(questionDb.getQuestions(), "score");
        return test;
    }
}