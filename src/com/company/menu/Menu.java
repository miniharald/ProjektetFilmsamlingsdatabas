package com.company.menu;

import com.company.App;
import com.company.methods.DbViewer;
import com.company.methods.add.MovieAdder;
import com.company.methods.update.MovieObjectsUpdater;
import com.company.methods.update.MovieUpdater;
import com.company.methods.add.ObjectAdder;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private ArrayList<MenuPicker> mainMenu;
    private ArrayList<MenuPicker> editDbMenu;
    private ArrayList<MenuPicker> updateObjectsMenu;
    private ArrayList<MenuPicker> createDbObjectsMenu;
    private ArrayList<MenuPicker> displayDbMenu;
    private ArrayList<MenuPicker> addToMovieMenu;
    private ArrayList<MenuPicker> currentMenu;
    private MovieAdder movieAdder;
    private ObjectAdder objectAdder;
    private DbViewer dbViewer;
    private MovieUpdater movieUpdater;
    private MovieObjectsUpdater movieObjectsUpdater;

    public Menu(App app){
        movieAdder = new MovieAdder(app);
        dbViewer = new DbViewer(app);
        objectAdder = new ObjectAdder(app);
        movieUpdater = new MovieUpdater(app);
        movieObjectsUpdater = new MovieObjectsUpdater(app);

        loadMenus();

        currentMenu = mainMenu;
    }

    private void loadMenus() {
        mainMenu = new ArrayList<MenuPicker>();
        mainMenu.add(new MenuPicker("Editera Databas", '1', this::showEditDbMenu));
        mainMenu.add(new MenuPicker("Titta på databasen", '2', this::showDisplayDbMenu));
        mainMenu.add(new MenuPicker("Avsluta", '0', null));

        editDbMenu = new ArrayList<MenuPicker>();
        editDbMenu.add(new MenuPicker("Skapa nya filmobjekt", '1', this::showCreateDbObjectsMenu));
        editDbMenu.add(new MenuPicker("Uppdatera filmobjekt", '2', this::showUpdateObjectsMenu));
        editDbMenu.add(new MenuPicker("Lägg till i film", '3', this::showAddToMovieMenu));
        editDbMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));

        updateObjectsMenu = new ArrayList<MenuPicker>();
        updateObjectsMenu.add(new MenuPicker("Ändra Skådespelares namn", '1', movieObjectsUpdater::updateActor));
        updateObjectsMenu.add(new MenuPicker("Ändra Regissörs namn", '2', movieObjectsUpdater::updateDirector));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på genre", '3', movieObjectsUpdater::updateGenre));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på format", '4', movieObjectsUpdater::updateFormat));
        updateObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));

        createDbObjectsMenu = new ArrayList<MenuPicker>();
        createDbObjectsMenu.add(new MenuPicker("Skapa ny film", '1', movieAdder::run));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny genre", '2', objectAdder::addNewGenre));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny Oscar", '3', objectAdder::addNewAward));
        createDbObjectsMenu.add(new MenuPicker("Skapa nytt format", '4', objectAdder::addNewFormat));
        createDbObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));

        addToMovieMenu = new ArrayList<MenuPicker>();
        addToMovieMenu.add(new MenuPicker("Lägg till regissör i film", '1', movieUpdater::addDirector));
        addToMovieMenu.add(new MenuPicker("Lägg till skådespelare i film", '2', movieUpdater::addActor));
        addToMovieMenu.add(new MenuPicker("Lägg till genre i film", '3', movieUpdater::addGenre));
        addToMovieMenu.add(new MenuPicker("Lägg till Oscar i film", '4', movieUpdater::addAward));
        addToMovieMenu.add(new MenuPicker("Ändra format i film", '5', movieUpdater::changeFormat));
        addToMovieMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));

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

    private void showCreateDbObjectsMenu(Object o) {
        currentMenu = createDbObjectsMenu;
    }

    private void showUpdateObjectsMenu(Object o) {
        currentMenu = updateObjectsMenu;
    }

    private void showAddToMovieMenu(Object o) {
        currentMenu = addToMovieMenu;
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
