package com.company.menu;

import com.company.App;
import com.company.methods.DbViewer;
import com.company.methods.ObjectRemover;
import com.company.methods.ObjectsInMovieRemover;
import com.company.methods.SearchFinder;
import com.company.methods.add.MovieAdder;
import com.company.methods.add.ObjectAdder;
import com.company.methods.update.MovieObjectsUpdater;
import com.company.methods.update.MovieUpdater;

import java.util.ArrayList;
import java.util.List;

public class MenuFactory {

    private List<MenuPicker> mainMenu;
    private List<MenuPicker> editDbMenu;
    private List<MenuPicker> updateObjectsMenu;
    private List<MenuPicker> removeDbObjectsMenu;
    private List<MenuPicker> removeFromMovieMenu;
    private List<MenuPicker> createDbObjectsMenu;
    private List<MenuPicker> displayDbMenu;
    private List<MenuPicker> addToMovieMenu;
    private List<MenuPicker> currentMenu;
    private MovieAdder movieAdder;
    private ObjectAdder objectAdder;
    private DbViewer dbViewer;
    private MovieUpdater movieUpdater;
    private MovieObjectsUpdater movieObjectsUpdater;
    private ObjectRemover objectRemover;
    private ObjectsInMovieRemover objectsInMovieRemover;
    private SearchFinder searchFinder;

    public MenuFactory(App app) {
        movieAdder = new MovieAdder(app);
        dbViewer = new DbViewer(app);
        objectAdder = new ObjectAdder(app);
        movieUpdater = new MovieUpdater(app);
        movieObjectsUpdater = new MovieObjectsUpdater(app);
        objectRemover = new ObjectRemover(app);
        objectsInMovieRemover = new ObjectsInMovieRemover(app);
        searchFinder = new SearchFinder(app);
        createAllMenus();
        currentMenu = mainMenu;
    }

    private void createAllMenus() {
        createACreateDbObjectsMenu();
        createAddToMovieMenu();
        createDisplayDbMenu();
        createEditDbMenu();
        createMainMenu();
        createRemoveDbObjectsMenu();
        createRemoveFromMovieMenu();
        createUpdateObjectsMenu();
    }

    private void createMainMenu() {
        mainMenu = new ArrayList<>();
        mainMenu.add(new MenuPicker("Editera Databas", '1', this::showEditDbMenu));
        mainMenu.add(new MenuPicker("Titta på databasen", '2', this::showDisplayDbMenu));
        mainMenu.add(new MenuPicker("Avsluta", '0', null));
    }

    private void createEditDbMenu() {
        editDbMenu = new ArrayList<>();
        editDbMenu.add(new MenuPicker("Skapa nya filmobjekt", '1', this::showCreateDbObjectsMenu));
        editDbMenu.add(new MenuPicker("Uppdatera filmobjekt", '2', this::showUpdateObjectsMenu));
        editDbMenu.add(new MenuPicker("Ta bort filmobjekt", '3', this::showRemoveDbObjectsMenu));
        editDbMenu.add(new MenuPicker("Lägg till i film", '4', this::showAddToMovieMenu));
        editDbMenu.add(new MenuPicker("Ta bort från film", '5', this::showRemoveFromMovieMenu));
        editDbMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createUpdateObjectsMenu() {
        updateObjectsMenu = new ArrayList<>();
        updateObjectsMenu.add(new MenuPicker("Ändra Skådespelares namn", '1', movieObjectsUpdater::updateActor));
        updateObjectsMenu.add(new MenuPicker("Ändra Regissörs namn", '2', movieObjectsUpdater::updateDirector));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på genre", '3', movieObjectsUpdater::updateGenre));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på format", '4', movieObjectsUpdater::updateFormat));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på Oscars", '5', movieObjectsUpdater::updateAward));
        updateObjectsMenu.add(new MenuPicker("Ändra titel på film", '6', movieObjectsUpdater::updateTitleOfMovie));
        updateObjectsMenu.add(new MenuPicker("Ändra årtal på film", '7', movieObjectsUpdater::updateYearOfMovie));
        updateObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createRemoveDbObjectsMenu() {
        removeDbObjectsMenu = new ArrayList<>();
        removeDbObjectsMenu.add(new MenuPicker("Ta bort film", '1', objectRemover::removeMovie));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort genre", '2', objectRemover::removeGenre));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort skådespelare", '3', objectRemover::removeActor));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort regissör", '4', objectRemover::removeDirector));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort Oscars", '5', objectRemover::removeAward));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort format", '6', objectRemover::removeFormat));
        removeDbObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createRemoveFromMovieMenu() {
        removeFromMovieMenu = new ArrayList<>();
        removeFromMovieMenu.add(new MenuPicker("Ta bort skådespelare från film", '1', objectsInMovieRemover::removeActorFromMovie));
        removeFromMovieMenu.add(new MenuPicker("Ta bort regissör från film", '2', objectsInMovieRemover::removeDirectorFromMovie));
        removeFromMovieMenu.add(new MenuPicker("Ta bort Oscars från film", '3', objectsInMovieRemover::removeAwardFromMovie));
        removeFromMovieMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createACreateDbObjectsMenu() {
        createDbObjectsMenu = new ArrayList<>();
        createDbObjectsMenu.add(new MenuPicker("Skapa ny film", '1', movieAdder::run));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny genre", '2', objectAdder::addNewGenre));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny Oscar", '3', objectAdder::addNewAward));
        createDbObjectsMenu.add(new MenuPicker("Skapa nytt format", '4', objectAdder::addNewFormat));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny Skådespelare", '5', objectAdder::addNewActor));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny Regissör", '6', objectAdder::addNewDirector));
        createDbObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createAddToMovieMenu() {
        addToMovieMenu = new ArrayList<>();
        addToMovieMenu.add(new MenuPicker("Lägg till regissör i film", '1', movieUpdater::addDirector));
        addToMovieMenu.add(new MenuPicker("Lägg till skådespelare i film", '2', movieUpdater::addActor));
        addToMovieMenu.add(new MenuPicker("Lägg till genre i film", '3', movieUpdater::addGenre));
        addToMovieMenu.add(new MenuPicker("Lägg till Oscar i film", '4', movieUpdater::addAward));
        addToMovieMenu.add(new MenuPicker("Ändra format i film", '5', movieUpdater::changeFormat));
        addToMovieMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createDisplayDbMenu() {
        displayDbMenu = new ArrayList<MenuPicker>();
        displayDbMenu.add(new MenuPicker("Alla filmer", '1', dbViewer::browseByMovies));
        displayDbMenu.add(new MenuPicker("Alla Genre", '2', dbViewer::browseByGenre));
        displayDbMenu.add(new MenuPicker("Alla regissörer", '3', dbViewer::browseByDirector));
        displayDbMenu.add(new MenuPicker("Alla skådespelare", '4', dbViewer::browseByActor));
        displayDbMenu.add(new MenuPicker("Alla Format", '5', dbViewer::browseByFormat));
        displayDbMenu.add(new MenuPicker("Sök", '6', searchFinder::search));
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

    private void showRemoveDbObjectsMenu(Object o) {
        currentMenu = removeDbObjectsMenu;
    }

    private void showRemoveFromMovieMenu(Object o) {
        currentMenu = removeFromMovieMenu;
    }

    private void showAddToMovieMenu(Object o) {
        currentMenu = addToMovieMenu;
    }

    private void showDisplayDbMenu(Object o) {
        currentMenu = displayDbMenu;
    }

    public List<MenuPicker> getCurrentMenu() {
        return currentMenu;
    }
}
