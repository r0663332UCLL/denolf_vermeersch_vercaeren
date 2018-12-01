package domain.db;

import domain.model.Question;

import java.io.*;
import java.util.ArrayList;


public class QuestionDbInFile implements QuestionDb {
    BufferedWriter writer = null;

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
    public void removeQuestion(String question) {

        File inputFile;
        File tempFile;

        try {
            inputFile = new File("testdatabase/vraag.txt");

            if (!inputFile.isFile()) {
                System.out.println("Parameter is not an existing file");
                return;
            }

            tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String currentLine = null;

            while((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().equals(question.trim())) {
                    writer.println(currentLine);
                    writer.flush();
                }
            }
            writer.close();
            reader.close();


            //Delete the original file
            if (!inputFile.delete()) {
                System.out.println("Could not delete file");
                return;
            }

            //Rename the new file to the filename the original file had.
            if (!tempFile.renameTo(inputFile)) {
                System.out.println("Could not rename file");
            }
        } catch (FileNotFoundException ex) {
            throw new DbException(ex.getMessage());
        }
        catch (IOException ex) {
            throw new DbException(ex.getMessage());
        }
    }

    @Override
    public ArrayList<Question> getQuestions() {

        return null;
    }
}
