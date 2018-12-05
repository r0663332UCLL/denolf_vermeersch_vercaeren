package domain.db;

import domain.model.Category;

import java.io.*;
import java.util.ArrayList;

public class CategoryDbInFile implements CategoryDb {
    @Override
    public void addCategory(Category category) {
        BufferedWriter writer = null;

        try {
            writer = new BufferedWriter(new FileWriter("testdatabase/groep.txt"));
            writer.write(category.toString() + System.getProperty("line.separator"));
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
    public void removeCategory(Category category) {
        File inputFile;
        File tempFile;

        try {
            inputFile = new File("testdatabase/groep.txt");

            if (!inputFile.isFile()) {
                throw new DbException("Parameter is not an existing file");
            }

            tempFile = new File(inputFile.getAbsolutePath() + ".tmp");

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            PrintWriter writer = new PrintWriter(new FileWriter(tempFile));

            String currentLine = null;

            while((currentLine = reader.readLine()) != null) {
                if (!currentLine.trim().equals(category.toString().trim())) {
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
    public ArrayList<Category> getCategories() {
        ArrayList<String> categories = new ArrayList<>();
        ArrayList<Category> tmpCat = new ArrayList<>();

        File inputFile;

        try {
            inputFile = new File("testdatabase/groep.txt");

            if (!inputFile.isFile()) {
                throw new DbException("Parameter is not an existing file");
            }

            BufferedReader reader = new BufferedReader(new FileReader(inputFile));

            String currentLine = null;

            while((currentLine = reader.readLine()) != null) {
                categories.add(currentLine);
            }

            //get all main categories first
            for (int i = 0; i < categories.size(); i++) {
                if(!hasMainCategory(categories.get(i))) {
                    tmpCat.add(stringToCategory(categories.get(i)));
                }
            }

            //get all categories and match them with their main categories

            for (int y = 0; y < categories.size(); y++) {
                if(hasMainCategory(categories.get(y))) {
                    Category cat1 = null;
                    for (int x = 0; x < tmpCat.size(); x++) {
                        if (tmpCat.get(x).getTitle().equals(getMainCategory(categories.get(y)))) {
                            cat1 = tmpCat.get(x);
                        }
                    }
                    tmpCat.add(stringToCategoryWithMain(categories.get(y), cat1));
                }
            }

        } catch (FileNotFoundException ex) {
            throw new DbException(ex.getMessage());
        }
        catch (IOException ex) {
            throw new DbException(ex.getMessage());
        }
        return tmpCat;
    }

    public static boolean hasMainCategory(String category) {
        String[] separated;
        char separator = ';';

        if (!(category == null)) {
            separated = category.split("[" + separator + "]");

            if (separated[separated.length -1].equals(null)) {
                return false;
            } else {
                return true;
            }

        } else {
            throw new DbException("question is null");
        }
    }

    public static String getMainCategory(String category) {
        String[] separated;
        char separator = ';';

        if (category != null) {
            separated = category.split("[" + separator + "]");

            return separated[2];

        } else {
            throw new DbException("question is null");
        }
    }

    public static Category stringToCategoryWithMain(String category, Category maincat) {
        String[] separated;
        char separator = ';';

        String title;
        String description;
        String mainCat = null;

        if (category != null) {
            separated = category.split("[" + separator + "]");

            title = separated[0];
            description = separated[1];

            return new Category(title, description, maincat);

        } else {
            throw new DbException("question is null");
        }
    }

    public static Category stringToCategory(String category) {
        String[] separated;
        char separator = ';';

        String title;
        String description;

        if (category != null) {
            separated = category.split("[" + separator + "]");

            title = separated[0];
            description = separated[1];

            return new Category(title, description);

        } else {
            throw new DbException("question is null");
        }
    }
}
