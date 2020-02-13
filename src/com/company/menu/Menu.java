package com.company.menu;

import com.company.App;
import com.company.methods.DbViewer;
import com.company.methods.MovieAdder;
import com.company.methods.MovieUpdater;
import com.company.methods.ObjectAdder;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ArrayList<MenuPicker> mainMenu;
    private ArrayList<MenuPicker> editDbMenu;
    private ArrayList<MenuPicker> displayDbMenu;
    private ArrayList<MenuPicker> currentMenu;
    private MovieAdder movieAdder;
    private ObjectAdder objectAdder;
    private DbViewer dbViewer;
    private MovieUpdater movieUpdater;

    public Menu(App app){
        movieAdder = new MovieAdder(app);
        dbViewer = new DbViewer(app);
        objectAdder = new ObjectAdder(app);
        movieUpdater = new MovieUpdater(app);

        loadMenus();

        currentMenu = mainMenu;
    }

    private void loadMenus() {
        mainMenu = new ArrayList<MenuPicker>();
        mainMenu.add(new MenuPicker("Editera Databas", '1', this::showEditDbMenu));
        mainMenu.add(new MenuPicker("Titta på databasen", '2', this::showDisplayDbMenu));
        mainMenu.add(new MenuPicker("Avsluta", '0', null));

        editDbMenu = new ArrayList<MenuPicker>();
        editDbMenu.add(new MenuPicker("Skapa ny film", '1', movieAdder::run));
        editDbMenu.add(new MenuPicker("Skapa ny genre", '2', objectAdder::addNewGenre));
        editDbMenu.add(new MenuPicker("Skapa ny Oscar", '3', objectAdder::addNewAward));
        editDbMenu.add(new MenuPicker("Skapa nytt format", '4', objectAdder::addNewFormat));
        editDbMenu.add(new MenuPicker("Lägg till regissör i film", '5', movieUpdater::addDirectorToMovie));
        editDbMenu.add(new MenuPicker("Lägg till skådespelare i film", '6', movieUpdater::addActorToMovie));
        editDbMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));

        displayDbMenu = new ArrayList<MenuPicker>();
        displayDbMenu.add(new MenuPicker("Alla filmer", '1', dbViewer::browseByMovies));
        displayDbMenu.add(new MenuPicker("Alla Genre", '2', dbViewer::browseByGenre));
        displayDbMenu.add(new MenuPicker("Alla regissörer", '3', dbViewer::browseByDirector));
        displayDbMenu.add(new MenuPicker("Alla skådespelare", '4', dbViewer::browseByActor));
        displayDbMenu.add(new MenuPicker("Alla Format", '5', dbViewer::browseByFormat));
        displayDbMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void showMainMenu(Object o) {
        currentMenu = mainMenu;
    }

    private void showEditDbMenu(Object o) {
        currentMenu = editDbMenu;
    }

    private void showDisplayDbMenu(Object o) {
        currentMenu = displayDbMenu;
    }

    private void printMenu() {
        System.out.println("");
        for (MenuPicker menuPicker : currentMenu) {
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
        for (MenuPicker menuPicker : currentMenu) {
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
