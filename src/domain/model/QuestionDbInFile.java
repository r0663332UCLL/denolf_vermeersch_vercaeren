package domain.model;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Properties
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class QuestionDbInFile implements QuestionDb {
    BufferedWriter writer = null;

    @Override
    public void addQuestion(Question question) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("/testdatabase/vraag.txt"));
            writer.write(question.toString());
        } catch (IOException e) {
            throw new ModelException(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e);
                }
            }
        }
    }

    @Override
    public void removeQuestion(String question) {
        try {
            String data= null;
            File file = new File("testdatabase/vraag.txt");
            BufferedReader br;
            try (FileReader fr = new FileReader(file)) {
                br = new BufferedReader(fr);
            }
            while((data=br.readLine())!= null) {
                String[] de = data.split(" ");
                if(de[0].equals("vimal")) {
                    data.trim();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        br.close()
    }
    }

    @Override
    public ArrayList<Question> getQuestions() {
        return null;
    }
}
