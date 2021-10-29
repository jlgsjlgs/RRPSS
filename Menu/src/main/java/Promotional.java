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
import java.util.ArrayList;
import java.math.*;

public class Promotional {
    
    private String name;
    private ArrayList<MenuItem> items;
    private double totalprice;
    
    public Promotional(String name){
        this.name = name;
        this.totalprice = 0;
        this.items = new ArrayList<>();
    }
    
    public void addItem(MenuItem item){
        this.items.add(item);
        updatePrice();
    }
    
    public void removeItem(int num){
        this.items.remove(num);
        updatePrice();
    }
    
    public void printItems(){
        System.out.println("Items included in the promotional set,");
        int i = 1;
        for (MenuItem temp: this.items){
            System.out.println(i + ". " + temp.getName());
            System.out.println("Description: " + temp.getDescription());
            System.out.println();
            i+=1;
        }
    }
    
    public double getPrice(){
        return this.totalprice;
    }
    
    public String getName(){
        return this.name;
    }
    
    public int getNumOfItems(){
        return this.items.size();
    }
    
    public void updatePrice(){
        double counter = 0;
        for (MenuItem temp: this.items){
            counter+= temp.getPrice();
        }
        this.totalprice = counter/100 * 80;
        this.totalprice = Math.round(this.totalprice * 100)/100;
    }
}
