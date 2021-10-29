/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonat
 */
import java.util.Scanner;
import java.nio.file.Paths;
import java.util.ArrayList;

public class Menu {
    private ArrayList<MainCourse> mains;
    private ArrayList<Drink> drinks;
    private ArrayList<Dessert> desserts;
    private ArrayList<Promotional> promotions;
    
    public Menu(){
        this.mains = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.desserts = new ArrayList<>();
        this.promotions = new ArrayList<>();   
    }
    
    public void bootup(){
        Scanner filescanner = new Scanner(System.in);
        //System.out.println("File to read: ");
        String filename = "testitems.txt";
        
        try (Scanner filescan = new Scanner(Paths.get(filename))){
            ArrayList<String> fileLines = new ArrayList<>();
            
            while (filescan.hasNextLine()){
                String line = filescan.nextLine();
                
                if (line.equals("END")){
                    break;
                }
                
                if (line.isEmpty() || !filescan.hasNextLine()){
                    String itemType = fileLines.get(0);
                    String itemName = fileLines.get(1);
                    String itemDescription = fileLines.get(2);
                    double itemPrice = Double.valueOf(fileLines.get(3));
                    
                    switch(itemType){
                        case "MainCourse":
                            this.mains.add(new MainCourse(itemName, itemDescription, itemPrice));
                            break;
                        case "Drink":
                            this.drinks.add(new Drink(itemName, itemDescription, itemPrice));
                            break;
                        case "Dessert":
                            this.desserts.add(new Dessert(itemName, itemDescription, itemPrice));
                            break;
                        default:
                            System.out.println("Error in item type");
                    }
                    fileLines.clear();
                } else {
                    fileLines.add(line);
                }
            }
        } catch (Exception e){
            System.out.println("Error: " + e.getMessage());
        }
    }
    
    public void showItemTypes(){
        System.out.println("1. MainCourse");
        System.out.println("2. Drink");
        System.out.println("3. Dessert");
    }
    
    public void showItemParameters(){
        System.out.println("1. Name");
        System.out.println("2. Description");
        System.out.println("3. Price");
    }
    
    public void addItem(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What type of item do you want to add?");
        showItemTypes();
        Integer userInput = Integer.valueOf(sc.nextLine());
        System.out.println("Item name: ");
        String itemName = sc.nextLine();
        System.out.println("Item description:");
        String itemDescription = sc.nextLine();
        System.out.println("Item price:");
        double itemPrice = Double.valueOf(sc.nextLine());
        
        switch (userInput){
            case 1:
                this.mains.add(new MainCourse(itemName, itemDescription, itemPrice));
                break;
            case 2:
                this.drinks.add(new Drink(itemName, itemDescription, itemPrice));
                break;
            case 3:
                this.desserts.add(new Dessert(itemName, itemDescription, itemPrice));
                break;
            default:
                System.out.println("Invalid item type!");
        }      
    }
    
    public void updateItem(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("What type of item do you want to update?");
        showItemTypes();
        Integer userInput = Integer.valueOf(sc.nextLine());
        Integer itemNumber;
        Integer itemPara;
        String newString;
        Double newDouble;
        
        switch(userInput){
            case 1:
                if (this.mains.size() == 0){
                    System.out.println("No such items to update!");
                    break;
                }
                getMains();
                System.out.println("Which item do you want to update?");
                itemNumber = Integer.valueOf(sc.nextLine());
                if (itemNumber < 1 || itemNumber > mains.size()){
                    System.out.println("Invalid item!");
                    break;
                }
                System.out.println("What parameter do you want to update?");
                showItemParameters();
                itemPara = Integer.valueOf(sc.nextLine());
                if (itemPara < 1 || itemPara > 3){
                    System.out.println("Invalid parameter!");
                    break;
                }
                if (itemPara == 1){
                    System.out.println("Enter new name:");
                    newString = sc.nextLine();
                    this.mains.get(itemNumber-1).setName(newString);
                }
                
                
                
        }
    }
    
    public void getMains(){
        int i = 1;
        System.out.println("Mains in the menu");
        for (MainCourse temp: mains){
            System.out.println(i + ". " + temp.getName());
            System.out.println(temp.getDescription());
            System.out.println(temp.getPrice());
            System.out.println("");
            i++;
        }
    }
    
    public void getDrinks(){
        int i = 1;
        System.out.println("Drinks in the menu");
        for (Drink temp: drinks){
            System.out.println(i + ". " + temp.getName());
            System.out.println(temp.getDescription());
            System.out.println(temp.getPrice());
            System.out.println("");
            i++;
        }
    }
    
    public void getDesserts(){
        int i = 1;
        System.out.println("Desserts in the menu");
        for (Dessert temp: desserts){
            System.out.println(i + ". " + temp.getName());
            System.out.println(temp.getDescription());
            System.out.println(temp.getPrice());
            System.out.println("");
            i++;
        }
    }
    
}
