import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Play {
    String[][] CraftingTable = new String[3][3];
    Scanner input = new Scanner(System.in);
    CraftingTable Crafting = new CraftingTable();
    Recipes RecipeBook;

    public Play(String filename) throws FileNotFoundException {
        for(int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                CraftingTable[i][j] = "";
            }
        }
        RecipeBook = new Recipes(filename);
    }

    /**
     * returns a string containing the options
     */
    public static String Options(){
        return  "A: Add Item\n" +
                "R: Remove Item\n" +
                "V: View Crafting Table\n" +
                "C: Craft\n" +
                "P: Recipes\n" +
                "X: Exit\n" +
                "Please enter the letter for your option.";
    }

    /**
     * gets the item, row, and column, then calls the AddItem function
     */
    public void Add(){
        System.out.println("What would you like to add?");
        String item = input.nextLine();
        System.out.println("Where would you like to add it?\nRow: ");
        int row = Integer.parseInt(input.nextLine());
        System.out.println("Column: ");
        int column = Integer.parseInt(input.nextLine());
        Crafting.AddItem(CraftingTable, item, row, column);
    }

    /**
     * gets the item to be removed, then calls the Remove function
     */
    public void Remove(){
        System.out.println("What item would you like to remove?");
        String item = input.nextLine();
        Crafting.RemoveItem(CraftingTable, item);
    }

    /**
     * prints the crafting table
     */
    public void View(String[][] table){
        System.out.println("_ _ _ _ _ _ _ _ _ _ _ _");
        for (String[] rows : table){
            System.out.print("| ");
            for(String column : rows){
                System.out.print(column + " | ");
            }
            System.out.println();
            System.out.println("_ _ _ _ _ _ _ _ _ _ _ _");
        }
    }

    /**
     * prints information in different recipes
     */
    public void RecipeList(){
        System.out.println("The Recipes are as Follows: ");
        int counter = 1;
        for (Recipe r : RecipeBook.recipes){
            System.out.println(String.format("%d: %s", counter, r.name));
            counter++;
        }
        System.out.println("Please enter the number of the recipe you would like to see: ");
        int index = Integer.parseInt(input.nextLine()) - 1;
        View(RecipeBook.recipes.get(index).recipe);

    }

    /**
     * attempts to craft an item using the recipe book and the current crafting table
     */
    public String Craft(){
        for (Recipe r : RecipeBook.recipes){
            if (Arrays.deepEquals(r.recipe, CraftingTable)){
                return "You Crafted: " + r.name;
            }
        }
        return "Recipe not found!";
    }

    public void PlayGame(){
        System.out.println("Welcome to the Crafting Table Simulator!\nWhat would you like to do?");
        while (true){
            System.out.println(Options());
            String userInput = input.nextLine();
            if(userInput.equalsIgnoreCase("a")){ //add item
                Add();
            }
            else if(userInput.equalsIgnoreCase("r")){ //remove item
                Remove();
            }
            else if (userInput.equalsIgnoreCase("v")){ //view table
                    View(CraftingTable);
            }
            else if (userInput.equalsIgnoreCase("c")){ //craft
                    System.out.println(Craft());
            }
            else if (userInput.equalsIgnoreCase("p")){ //display recipes
                RecipeList();
            }
            else if (userInput.equalsIgnoreCase("x")){ //exit
                break;
            }
            else{
                continue;
            }
        }
        System.out.println("Happy Crafting!!");
    }


    public static void main(String[] args) throws FileNotFoundException {
        Play craft = new Play("C:\\Users\\inter\\Desktop\\CS College programming\\MinecraftLab\\src\\recipes.txt"); //this loads up the recipes file for our crafting and starts the program
        craft.PlayGame();
    }
}
