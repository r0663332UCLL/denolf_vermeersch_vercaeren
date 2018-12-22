package domain.model;

import domain.db.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Properties;

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
    public Test generateTest(){
        return new Test(questionDb.getQuestions(), getPreference());
    }

    private String getPreference(){

        String result = "";
        InputStream inputStream;

        try {
            Properties prop = new Properties();
            String propFileName = "conf/configuration.properties";

             inputStream = new FileInputStream(new File(propFileName));

            if (inputStream != null) {
                prop.load(inputStream);
            } else {
                throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
            }

             result = prop.getProperty("feedbackBehaviour");
        } catch (Exception e) {
            throw new ModelException(e.getMessage());
        }
        return result;
    }

}
