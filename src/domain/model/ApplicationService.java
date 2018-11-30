package domain.model;

import java.util.ArrayList;

public class ApplicationService {

    private CategoryDb categoryDb;
    private QuestionDb questionDb;

    public ApplicationService(){
        categoryDb = new CategoryDbInFile();
        categoryDb = new CategoryDbInFile();
    }

    public void AddCategory(Category category){

    }

    public void removeCategory(String title){

    }

    public ArrayList<Category> getCategories(){
        return null;
    }

    public void addQuestion(Question question){

    }

    public void removeQuestion(String question){

    }

    public ArrayList<Question> getQuestions(){
        return null;
    }

    public Test generateTest(){
        return null;
    }
}
