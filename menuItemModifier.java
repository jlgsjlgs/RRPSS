import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

public class menuItemModifier {
    
    public void addMenuItem(HashMap<String, ArrayList<MenuItem>> Menu){
        Scanner sc = new Scanner(System.in);
        ArrayList temparray;
        int i=1;
        boolean flag = true;

        for (String itemname: Menu.keySet()){
            System.out.println(i + ". " + itemname);
            i++;
        }

        System.out.println("What type of item do you want to add?");
        Integer userInput = Integer.valueOf(sc.nextLine());
        System.out.println("Item name: ");
        String itemName = sc.nextLine();
        System.out.println("Item description:");
        String itemDescription = sc.nextLine();
        System.out.println("Item price:");
        double itemPrice = Double.valueOf(sc.nextLine());

        switch (userInput){
            case 1:
                Menu.get("MainCourse").add(new MainCourse(itemName, itemDescription, itemPrice));
                break;
            case 2:
                Menu.get("Drink").add(new MainCourse(itemName, itemDescription, itemPrice));
                break;
            case 3:
                Menu.get("Dessert").add(new MainCourse(itemName, itemDescription, itemPrice));
                break;
            default:
                System.out.println("Item type does not exist!");
                flag = false;
                break;
        }

        if (flag == true){
            System.out.println("Item added successfully!");
        }
    }

    public void updateMenuItem(HashMap<String, ArrayList<MenuItem>> Menu, String itemType, int itemNum){
        Scanner sc = new Scanner(System.in);
        ArrayList temparray;
        
        System.out.println("What do you want to change?");
        System.out.println("1. Name");
        System.out.println("2. Description");
        System.out.println("3. Price");
        int userInput = Integer.valueOf(sc.nextLine());

        MenuItem toChange = Menu.get(itemType).get(itemNum-1);
        
        switch (userInput){
            case 1:
                System.out.println("Enter new name: ");
                String newName = sc.nextLine();
                toChange.setName(newName);
                break;
            case 2:
                System.out.println("Enter new description: ");
                String newDescription = sc.nextLine();
                toChange.setDescription(newDescription);
                break;
            case 3:
                System.out.println("Enter new price: ");
                double newPrice = Double.valueOf(sc.nextLine());
                toChange.setPrice(newPrice);
                break;
            default:
                System.out.println("Error!");
        }
    }

    public void deleteMenuItem(HashMap<String, ArrayList<MenuItem>> Menu, String itemType, int itemNum){
        Menu.get(itemType).remove(itemNum-1);
    }
}
