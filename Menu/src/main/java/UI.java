/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author jonat
 */
public class UI {
    public static void main(String args[]){
        Menu mymenu = new Menu();
        mymenu.bootup();
        mymenu.addPromotionalItem();
        mymenu.deletePromotionalItem();
    }
}
