package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.objects.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

class MovieObjectsAdder {

    private App app;
    private InputChecker checker;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private String input;

    MovieObjectsAdder(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
    }

    void addMovieObjectToMovie(List<BaseObject> list, String folder, MovieObjects movieObjects, String type) {
        do {
            int newMovieObject;
            List<BaseObject> searchResult = new ArrayList<>();
            if (list.size() > 10) {
                System.out.println("Gör en sökning för att se om det/den " + type + " du ska lägga in redan finns:");
                String input = scan.nextLine();
                searchResult = fileManager.searchSpecific(input, list);
                fileManager.showListOfOptions(searchResult);
                newMovieObject = searchResult.size() + 1;
            } else {
                fileManager.showListOfOptions(list);
                newMovieObject = list.size() + 1;
            }
            System.out.println(newMovieObject + ".) Lägg till " + type);
            System.out.println("Välj ett alternativ ovan!");
            String crewChoice = scan.nextLine();
            int choice = Integer.parseInt(crewChoice);
            if (choice < newMovieObject) {
                addExistingMovieObjectToMovie(choice, movieObjects, searchResult);
                inputOk = true;
            } else if (choice == newMovieObject) {
                addNewMovieObjectToMovie(list, folder, movieObjects, type);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingMovieObjectToMovie(int choice, MovieObjects movieObject) {
        switch (movieObject) {
            case genre:
                app.getMovies().get(app.getMovies().size() - 1).addToGenre(app.getGenres().get(choice -1));
                break;
            case Oscars:
                app.getMovies().get(app.getMovies().size() - 1).addToAwards(app.getAwards().get(choice - 1));
                break;
            case format:
                app.getMovies().get(app.getMovies().size() - 1).setFormat(app.getFormats().get(choice - 1));
        }
    }

    void addNewMovieObjectToMovie(List<BaseObject> list, String folder, MovieObjects movieObjects, String type) {
        inputName(type);
        switch (movieObjects) {
            case genre:
                app.getGenres().add(new Genre(input));
                app.getMovies().get(app.getMovies().size() - 1).addToGenre(app.getGenres().get(app.getGenres().size() - 1));
                break;
            case Oscars:
                app.getAwards().add(new AcademyAward(input));
                app.getMovies().get(app.getMovies().size() - 1).addToAwards(app.getAwards().get(app.getAwards().size() - 1));
                break;
            case format:
                app.getFormats().add(new Format(input));
                app.getMovies().get(app.getMovies().size() - 1).setFormat(app.getFormats().get(app.getFormats().size() - 1));
                break;
        }
        fileManager.writeToFile(folder + list.get(list.size() - 1).getId() + ".txt", list.get(list.size() - 1));
    }

    void askToAddAwardToMovie() {
        System.out.println("Tryck 1 för att lägga till en Oscars. Annars valfri bokstav eller siffra!");
        String input = scan.nextLine();
        if(input.equals("1")) {
            addMovieObjectToMovie(Collections.unmodifiableList(app.getAwards()), App.AWARDFOLDER, MovieObjects.Oscars, "Oscars");
            MovieAdder movieAdder = new MovieAdder(app);
            movieAdder.addMore("Oscar");
        }
    }

    void addNewMovieObject(List<BaseObject> list, String folder, MovieObjects movieObjects, String type) {
        inputName(type);
        switch (movieObjects) {
            case genre:
                app.getGenres().add(new Genre(input));
                break;
            case Oscars:
                app.getAwards().add(new AcademyAward(input));
                break;
            case format:
                app.getFormats().add(new Format(input));
                break;
        }
        fileManager.writeToFile(folder + list.get(list.size() - 1).getId() + ".txt", list.get(list.size() - 1));
    }

    private void inputName(String type) {
        input = "";
        do {
            System.out.println("Ny " + type + ":");
            input = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(input);
            if (input.length() < 1 || input.isBlank()) {
                System.out.println(type + " måste innehålla minst en bokstav!");
                inputOk = false;
            }
        } while (!inputOk);
    }
}