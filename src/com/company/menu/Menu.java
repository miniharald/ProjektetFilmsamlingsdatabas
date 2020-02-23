package com.company.menu;

import com.company.App;

import java.util.Scanner;

public class Menu {

    private MenuFactory menuFactory;

    public Menu(App app){
        menuFactory = new MenuFactory(app);
    }

    private void printMenu() {
        System.out.println("");
        for (MenuPicker menuPicker : menuFactory.getCurrentMenu()) {
            System.out.printf("%s%n", menuPicker.getKeyAndTitle());
        }
        System.out.println("Ange ditt val: ");
    }

    private MenuPicker getMenuChoice() {
        String choice;
        Scanner scan = new Scanner(System.in);
        do {
            choice = scan.nextLine();
        } while (choice.length() == 0);
        for (MenuPicker menuPicker : menuFactory.getCurrentMenu()) {
            if (menuPicker.getKey() == choice.charAt(0))
                return menuPicker;
        }
        return null;
    }

    public void handleMenu() {
        boolean isStopping = false;
        MenuPicker menuPicker;
        while (!isStopping) {
            printMenu();
            menuPicker = getMenuChoice();
            if (menuPicker == null) {
                System.out.println("Felaktigt val, försök igen!");
            } else {
                System.out.printf("Du valde: %s%n", menuPicker.getTitle());
                isStopping = menuPicker.getFunction() == null;
                if (!isStopping) {
                    menuPicker.getFunction().accept(null);
                }
            }
        }
    }
}
