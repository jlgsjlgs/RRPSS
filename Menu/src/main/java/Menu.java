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
    
    public void addMenuItem(){
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
    
    public void updateMenuItem(){
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
                } else if (itemPara == 2){
                    System.out.println("Enter new description:");
                    newString = sc.nextLine();
                    this.mains.get(itemNumber-1).setDescription(newString);
                } else if (itemPara == 3){
                    System.out.println("Enter new price:");
                    newDouble = Double.valueOf(sc.nextLine());
                    this.mains.get(itemNumber-1).setPrice(newDouble);
                }
                break;
            case 2:
                if (this.drinks.size() == 0){
                    System.out.println("No such items to update!");
                    break;
                }
                getDrinks();
                System.out.println("Which item do you want to update?");
                itemNumber = Integer.valueOf(sc.nextLine());
                if (itemNumber < 1 || itemNumber > drinks.size()){
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
                    this.drinks.get(itemNumber-1).setName(newString);
                } else if (itemPara == 2){
                    System.out.println("Enter new description:");
                    newString = sc.nextLine();
                    this.drinks.get(itemNumber-1).setDescription(newString);
                } else if (itemPara == 3){
                    System.out.println("Enter new price:");
                    newDouble = Double.valueOf(sc.nextLine());
                    this.drinks.get(itemNumber-1).setPrice(newDouble);
                }
                break;
            case 3:
                if (this.desserts.size() == 0){
                    System.out.println("No such items to update!");
                    break;
                }
                getDesserts();
                System.out.println("Which item do you want to update?");
                itemNumber = Integer.valueOf(sc.nextLine());
                if (itemNumber < 1 || itemNumber > desserts.size()){
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
                    this.desserts.get(itemNumber-1).setName(newString);
                } else if (itemPara == 2){
                    System.out.println("Enter new description:");
                    newString = sc.nextLine();
                    this.desserts.get(itemNumber-1).setDescription(newString);
                } else if (itemPara == 3){
                    System.out.println("Enter new price:");
                    newDouble = Double.valueOf(sc.nextLine());
                    this.desserts.get(itemNumber-1).setPrice(newDouble);
                }
                break;
            default:
                System.out.println("No such type of item!");        
        }
    }
    
    public void deleteMenuItem(){
        Scanner sc = new Scanner(System.in);
        Integer toRemove;
        
        System.out.println("What type of item do you want to delete?");
        showItemTypes();
        Integer userInput = Integer.valueOf(sc.nextLine());
        switch (userInput){
            case 1:
                if (this.mains.size() == 0){
                    System.out.println("No items to remove!");
                    break;
                }
                System.out.println("Which item do you want to remove?");
                getMains();
                toRemove = Integer.valueOf(sc.nextLine());
                if (toRemove < 1 || toRemove > this.mains.size()){
                    System.out.println("Invalid item to remove!");
                    break;
                }
                this.mains.remove(toRemove-1);
                break;
            case 2:
                if (this.drinks.size() == 0){
                    System.out.println("No items to remove!");
                    break;
                }
                System.out.println("Which item do you want to remove?");
                getDrinks();
                toRemove = Integer.valueOf(sc.nextLine());
                if (toRemove < 1 || toRemove > this.drinks.size()){
                    System.out.println("Invalid item to remove!");
                    break;
                }
                this.drinks.remove(toRemove-1);
                break;
            case 3:
                if (this.desserts.size() == 0){
                    System.out.println("No items to remove!");
                    break;
                }
                System.out.println("Which item do you want to remove?");
                getDesserts();
                toRemove = Integer.valueOf(sc.nextLine());
                if (toRemove < 1 || toRemove > this.desserts.size()){
                    System.out.println("Invalid item to remove!");
                    break;
                }
                this.desserts.remove(toRemove-1);
                break;
            default:
                System.out.println("Not a valid item type!");
        }
    }
    
    public void addPromotionalItem(){
        Scanner sc = new Scanner(System.in);
        int userchoice;
        
        System.out.println("Enter name for promotion: ");
        String promoname = sc.nextLine();
        Promotional temp = new Promotional(promoname);
        
        System.out.println("Add main course to the promotion, input 0 to exit");
        getMains();
        while(true){
            userchoice = Integer.valueOf(sc.nextLine());
            
            if (userchoice == 0){
                break;
            }
            
            if (userchoice < 0 || userchoice > this.mains.size()){
                System.out.println("Invalid choice!");
            } else {
                temp.addItem(this.mains.get(userchoice-1));
            }
        }
        
        System.out.println("Add drink to the promotion, input 0 to exit");
        getDrinks();
        while(true){
            userchoice = Integer.valueOf(sc.nextLine());
            
            if (userchoice == 0){
                break;
            }
            
            if (userchoice < 0 || userchoice > this.drinks.size()){
                System.out.println("Invalid choice!");
            } else {
                temp.addItem(this.drinks.get(userchoice-1));
            }
        }
        
        System.out.println("Add dessert to the promotion, input 0 to exit");
        getDesserts();
        while(true){
            userchoice = Integer.valueOf(sc.nextLine());
            
            if (userchoice == 0){
                break;
            }
            
            if (userchoice < 0 || userchoice > this.desserts.size()){
                System.out.println("Invalid choice!");
            } else {
                temp.addItem(this.desserts.get(userchoice-1));
            }
        }
        
        temp.printItems();
        System.out.println("Total price for promotion: " + temp.getPrice());
        this.promotions.add(temp);
    }
    
    public void updatePromotionalItem(){
        if (this.promotions.size() == 0){
            System.out.println("No promotions to update!");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Which promotion item do you want to update?");
        getPromotions();
        
        int promonum = Integer.valueOf(sc.nextLine());
        if (promonum < 1 || promonum > this.promotions.size()){
            System.out.println("Invalid promotion!");
            return;
        }
        
        System.out.println("What do you want to update?");
        System.out.println("1. Add items to promotion");
        System.out.println("2. Remove items from promotion");
        int userchoice = Integer.valueOf(sc.nextLine());
        
        switch (userchoice){
            case 1:
                System.out.println("Add main course to the promotion, input 0 to exit");
                getMains();
                while(true){
                    userchoice = Integer.valueOf(sc.nextLine());

                    if (userchoice == 0){
                        break;
                    }

                    if (userchoice < 0 || userchoice > this.mains.size()){
                        System.out.println("Invalid choice!");
                    } else {
                        this.promotions.get(promonum-1).addItem(this.mains.get(userchoice-1));
                    }
                }

                System.out.println("Add drink to the promotion, input 0 to exit");
                getDrinks();
                while(true){
                    userchoice = Integer.valueOf(sc.nextLine());

                    if (userchoice == 0){
                        break;
                    }

                    if (userchoice < 0 || userchoice > this.drinks.size()){
                        System.out.println("Invalid choice!");
                    } else {
                        this.promotions.get(promonum-1).addItem(this.drinks.get(userchoice-1));
                    }
                }

                System.out.println("Add dessert to the promotion, input 0 to exit");
                getDesserts();
                while(true){
                    userchoice = Integer.valueOf(sc.nextLine());

                    if (userchoice == 0){
                        break;
                    }

                    if (userchoice < 0 || userchoice > this.desserts.size()){
                        System.out.println("Invalid choice!");
                    } else {
                        this.promotions.get(promonum-1).addItem(this.desserts.get(userchoice-1));
                    }
                }
                break;
            case 2:
                System.out.println("Input index to remove item, input 0 to exit");
                while(true){
                    this.promotions.get(promonum-1).printItems();
                    userchoice = Integer.valueOf(sc.nextLine());
                    if (userchoice == 0){
                        break;
                    }
                    
                    if (userchoice < 0 || userchoice > this.promotions.get(promonum-1).getNumOfItems()){
                        System.out.println("Invalid input!");
                    } else{
                        this.promotions.get(promonum-1).removeItem(userchoice-1);
                    }
                }
                break;
            default:
                System.out.println("Invalid choice!");
                break;
        } 
        this.promotions.get(promonum-1).printItems();
        System.out.println("Total price for promotion: " + this.promotions.get(promonum-1).getPrice());
    }
    
    public void deletePromotionalItem(){
        if (this.promotions.size() == 0){
            System.out.println("No promotions to update!");
            return;
        }
        
        Scanner sc = new Scanner(System.in);
        System.out.println("Which promotion item do you want to delete?");
        getPromotions();
        
        int promonum = Integer.valueOf(sc.nextLine());
        if (promonum < 1 || promonum > this.promotions.size()){
            System.out.println("Invalid promotion!");
            return;
        } else {
            this.promotions.remove(promonum-1);
        }
        getPromotions();
    }
    
    public void getMains(){
        int i = 1;
        System.out.println();
        System.out.println("***Mains in the menu");
        for (MainCourse temp: mains){
            System.out.println(i + ". " + temp.getName());
            System.out.println("Description: " + temp.getDescription());
            System.out.println("Price : " + temp.getPrice());
            System.out.println();
            i++;
        }
    }
    
    public void getDrinks(){
        int i = 1;
        System.out.println();
        System.out.println("***Drinks in the menu");
        for (Drink temp: drinks){
            System.out.println(i + ". " + temp.getName());
            System.out.println("Description: " + temp.getDescription());
            System.out.println("Price: " + temp.getPrice());
            System.out.println();
            i++;
        }
    }
    
    public void getDesserts(){
        int i = 1;
        System.out.println();
        System.out.println("***Desserts in the menu");
        for (Dessert temp: desserts){
            System.out.println(i + ". " + temp.getName());
            System.out.println("Description: " + temp.getDescription());
            System.out.println("Price: " + temp.getPrice());
            System.out.println();
            i++;
        }
    }
    
    public void getPromotions(){
        if (this.promotions.size() == 0){
            System.out.println("No promotions found!");
        } else {
            for (int i =0; i<this.promotions.size(); i++){
                System.out.println((i+1) + ". " + this.promotions.get(0).getName());
            }
        }
    }
    
}
