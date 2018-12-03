package domain.db;

import domain.model.Question;

import java.io.*;
import java.util.ArrayList;


public class QuestionDbInFile implements QuestionDb {

    @Override
    public void addQuestion(Question question) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("/testdatabase/vraag.txt"));
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

            String currentLine = null;

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
        } catch (FileNotFoundException ex) {
            throw new DbException(ex.getMessage());
        }
        catch (IOException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    @Override
    public ArrayList<String> getQuestions() {
        ArrayList<String> questions = new ArrayList<>();
        File inputFile;

        try {
            inputFile = new File("testdatabase/vraag.txt");

            if (!inputFile.isFile()) {
                throw new DbException("Parameter is not an existing file");
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine = null;

            while((currentLine = reader.readLine()) != null) {
                questions.add(currentLine.trim());
            }

        } catch (FileNotFoundException ex) {
            throw new DbException(ex.getMessage());
        }
        catch (IOException ex) {
            throw new DbException(ex.getMessage());
        }
        return questions;
    }
}
