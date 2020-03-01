package com.company.menu;

import com.company.App;
import java.util.ArrayList;
import java.util.List;

class MenuFactory {

    private App app;
    private List<MenuPicker> mainMenu;
    private List<MenuPicker> editDbMenu;
    private List<MenuPicker> updateObjectsMenu;
    private List<MenuPicker> removeDbObjectsMenu;
    private List<MenuPicker> removeFromMovieMenu;
    private List<MenuPicker> createDbObjectsMenu;
    private List<MenuPicker> displayDbMenu;
    private List<MenuPicker> addToMovieMenu;
    private List<MenuPicker> removeAwardFromCrewMenu;
    private List<MenuPicker> currentMenu;


    MenuFactory(App app) {
        this.app = app;
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
        createRemoveAwardFromCrewMenu();
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
        editDbMenu.add(new MenuPicker("Lägg till/Ta bort Oscars från medarbetare", '6', this::showRemoveAwardFromCrewMenu));
        editDbMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createUpdateObjectsMenu() {
        updateObjectsMenu = new ArrayList<>();
        updateObjectsMenu.add(new MenuPicker("Ändra Skådespelares namn", '1', app.getMovieObjectsUpdater()::updateActor));
        updateObjectsMenu.add(new MenuPicker("Ändra Regissörs namn", '2', app.getMovieObjectsUpdater()::updateDirector));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på genre", '3', app.getMovieObjectsUpdater()::updateGenre));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på format", '4', app.getMovieObjectsUpdater()::updateFormat));
        updateObjectsMenu.add(new MenuPicker("Ändra namn på Oscars", '5', app.getMovieObjectsUpdater()::updateAward));
        updateObjectsMenu.add(new MenuPicker("Ändra titel på film", '6', app.getMovieObjectsUpdater()::updateTitleOfMovie));
        updateObjectsMenu.add(new MenuPicker("Ändra årtal på film", '7', app.getMovieObjectsUpdater()::updateYearOfMovie));
        updateObjectsMenu.add(new MenuPicker("Ändra längden på film", '8', app.getMovieObjectsUpdater()::updateLengthOfMovie));
        updateObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createRemoveDbObjectsMenu() {
        removeDbObjectsMenu = new ArrayList<>();
        removeDbObjectsMenu.add(new MenuPicker("Ta bort film", '1', app.getObjectRemover()::removeMovie));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort genre", '2', app.getObjectRemover()::removeGenre));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort skådespelare", '3', app.getObjectRemover()::removeActor));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort regissör", '4', app.getObjectRemover()::removeDirector));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort Oscars", '5', app.getObjectRemover()::removeAward));
        removeDbObjectsMenu.add(new MenuPicker("Ta bort format", '6', app.getObjectRemover()::removeFormat));
        removeDbObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createRemoveFromMovieMenu() {
        removeFromMovieMenu = new ArrayList<>();
        removeFromMovieMenu.add(new MenuPicker("Ta bort skådespelare från film", '1', app.getObjectsInMovieRemover()::removeActorFromMovie));
        removeFromMovieMenu.add(new MenuPicker("Ta bort regissör från film", '2', app.getObjectsInMovieRemover()::removeDirectorFromMovie));
        removeFromMovieMenu.add(new MenuPicker("Ta bort Oscars från film", '3', app.getObjectsInMovieRemover()::removeAwardFromMovie));
        removeFromMovieMenu.add(new MenuPicker("Ta bort genre från film", '4', app.getObjectsInMovieRemover()::removeGenreFromMovie));
        removeFromMovieMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createRemoveAwardFromCrewMenu() {
        removeAwardFromCrewMenu = new ArrayList<>();
        removeAwardFromCrewMenu.add(new MenuPicker("Ta bort Oscars från skådespelare", '1', app.getCrewsAwardRemover()::removeAwardFromActor));
        removeAwardFromCrewMenu.add(new MenuPicker("Ta bort Oscars från regissör", '2', app.getCrewsAwardRemover()::removeAwardFromDirector));
        removeAwardFromCrewMenu.add(new MenuPicker("Lägg till Oscars till skådespelare", '3', app.getCrewsAwardAdder()::addAwardToActor));
        removeAwardFromCrewMenu.add(new MenuPicker("Lägg till Oscars till regissör", '4', app.getCrewsAwardAdder()::addAwardToDirector));
        removeAwardFromCrewMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createACreateDbObjectsMenu() {
        createDbObjectsMenu = new ArrayList<>();
        createDbObjectsMenu.add(new MenuPicker("Skapa ny film", '1', app.getMovieAdder()::run));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny genre", '2', app.getNewObjectAdderFacade()::addNewGenre));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny Oscar", '3', app.getNewObjectAdderFacade()::addNewAward));
        createDbObjectsMenu.add(new MenuPicker("Skapa nytt format", '4', app.getNewObjectAdderFacade()::addNewFormat));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny Skådespelare", '5', app.getNewObjectAdderFacade()::addNewActor));
        createDbObjectsMenu.add(new MenuPicker("Skapa ny Regissör", '6', app.getNewObjectAdderFacade()::addNewDirector));
        createDbObjectsMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createAddToMovieMenu() {
        addToMovieMenu = new ArrayList<>();
        addToMovieMenu.add(new MenuPicker("Lägg till regissör i film", '1', app.getMovieUpdater()::addDirector));
        addToMovieMenu.add(new MenuPicker("Lägg till skådespelare i film", '2', app.getMovieUpdater()::addActor));
        addToMovieMenu.add(new MenuPicker("Lägg till genre i film", '3', app.getMovieUpdater()::addGenre));
        addToMovieMenu.add(new MenuPicker("Lägg till Oscar i film", '4', app.getMovieUpdater()::addAward));
        addToMovieMenu.add(new MenuPicker("Ändra format i film", '5', app.getMovieUpdater()::changeFormat));
        addToMovieMenu.add(new MenuPicker("Tillbaka till huvudmeny", '0', this::showMainMenu));
    }

    private void createDisplayDbMenu() {
        displayDbMenu = new ArrayList<>();
        displayDbMenu.add(new MenuPicker("Alla filmer", '1', app.getDbViewer()::browseByMovies));
        displayDbMenu.add(new MenuPicker("Alla Genre", '2', app.getDbViewer()::browseByGenre));
        displayDbMenu.add(new MenuPicker("Alla regissörer", '3', app.getDbViewer()::browseByDirector));
        displayDbMenu.add(new MenuPicker("Alla skådespelare", '4', app.getDbViewer()::browseByActor));
        displayDbMenu.add(new MenuPicker("Alla Format", '5', app.getDbViewer()::browseByFormat));
        displayDbMenu.add(new MenuPicker("Sök film", '6', app.getSearchFinder()::searchForMovie));
        displayDbMenu.add(new MenuPicker("Sök skådespelare", '7', app.getSearchFinder()::searchForActor));
        displayDbMenu.add(new MenuPicker("Sök regissör", '8', app.getSearchFinder()::searchForDirector));
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

    private void showRemoveAwardFromCrewMenu(Object o) {
        currentMenu = removeAwardFromCrewMenu;
    }

    private void showAddToMovieMenu(Object o) {
        currentMenu = addToMovieMenu;
    }

    private void showDisplayDbMenu(Object o) {
        currentMenu = displayDbMenu;
    }

    List<MenuPicker> getCurrentMenu() {
        return currentMenu;
    }
}
