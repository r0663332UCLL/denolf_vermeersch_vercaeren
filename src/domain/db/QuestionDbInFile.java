package domain.db;

import domain.model.MultipleChoiceQuestion;
import domain.model.Question;
import domain.model.YesNoQuestion;

import java.io.*;
import java.util.ArrayList;


public class QuestionDbInFile implements QuestionDb {

    @Override
    public void addQuestion(Question question) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("testdatabase/vraag.txt", true));
            writer.write(question.toString() + System.getProperty("line.separator"));
        } catch (IOException e) {
            throw new DbException(e.getMessage());
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    System.err.println(e.getMessage());
                }
            }
        }
    }

    @Override
    public void removeQuestion(Question question) {

        File inputFile;
        File tempFile;


        try {
            inputFile = new File("testdatabase/vraag.txt");

            if (!inputFile.isFile()) {
                throw new DbException("Parameter is not an existing file");
            }

            tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().equals(question.toString().trim())) {
                    writer.println(currentLine);
                    writer.flush();
                }
            }
            writer.close();
            reader.close();


            //Delete the original file
            if (!inputFile.delete()) {
                throw new DbException("Could not delete file");
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inputFile)) {
                throw new DbException("Could not rename file");
            }
        } catch (IOException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Question> getQuestions() {
        ArrayList<Question> questions = new ArrayList<>();
        File inputFile;

        try {
            inputFile = new File("testdatabase/vraag.txt");

            if (!inputFile.isFile()) {
                throw new DbException("Parameter is not an existing file");
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine;

            while((currentLine = reader.readLine()) != null) {
                questions.add(stringToQuestion(currentLine));
            }

        } catch (IOException ex) {
            throw new DbException(ex.getMessage());
        }
        return questions;
    }

    public static Question stringToQuestion(String question) {
        String[] separated;
        char separator = ';';
        ArrayList<String> statements = new ArrayList<>();
        String title;
        String feedback;
        String category;
        String type;

        if (question != null) {
            separated = question.split("[" + separator + "]");

            for (int i = 1; i < separated.length -3; i++) {
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
}
