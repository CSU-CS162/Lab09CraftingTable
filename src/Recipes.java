import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Recipes {
    ArrayList<Recipe> recipes = new ArrayList<>();
    /**
     * Each individual recipe is stored as a recipe object, which is then stored in a list of recipes.
     * This uses a file to populate the recipe book.
     */
    public Recipes(String filename) throws FileNotFoundException {
        Scanner scan = new Scanner(new File(filename));
        while(scan.hasNextLine()) {
            String name = scan.next();
            String[] ingredients = scan.next().split(",");
            recipes.add(new Recipe(name, ingredients));
        }
    }
}
