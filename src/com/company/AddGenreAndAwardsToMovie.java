package com.company;

import java.io.File;
import java.util.Scanner;

public class AddGenreAndAwardsToMovie {

    private App app;
    private Check checker;
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private boolean isDuplicate;
    private String id ="";

    public AddGenreAndAwardsToMovie(App app) {
        this.app = app;
        this.checker = new Check(app);
    }

    public void addGenre() {
        do {
            int genreCounter = 1;
            for (Genre genreObj : app.getGenres()) {
                System.out.println("[" + genreCounter + "]" + " " + genreObj.getName());
                genreCounter++;
            }
            int newGenre = app.getGenres().size() + 1;
            System.out.println("[" + newGenre + "]" + " " + "Lägg till genre");
            System.out.println("Välj ett alternativ ovan!");
            String genreChoice = scan.nextLine();
            int choice = Integer.parseInt(genreChoice);
            if (choice < genreCounter) {
                addExistingGenre(choice);
                inputOk = true;
            } else if (choice == genreCounter) {
                addNewGenre();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingGenre(int choice) {
        for (int i = 0; i < app.getGenres().size(); i++) {
            if (choice - 1 == i) {
                id = app.getGenres().get(i).getId();
                for (Genre genre : app.getGenres()) {
                    if (genre.getId().equals(id)) {
                        app.getMovies().get(app.getMovies().size() - 1).addToGenre(genre);
                    }
                }
            }
        }
    }

    private void addNewGenre() {
        String genre;
        do {
            System.out.println("Ny genre:");
            genre = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(genre);
            if (genre.length() < 1 || genre.isBlank()) {
                System.out.println("Genre måste innehålla minst en bokstav!");
                inputOk = false;
            }
        } while (!inputOk);
        do {
            Genre genreObj = new Genre();
            id = genreObj.getId();
            File folderPath = new File("database/genre/");
            isDuplicate = genreObj.checkForDuplicateFileNames(folderPath, id);
        } while (isDuplicate);
        app.getGenres().add(new Genre(genre));
        Genre genreObj = app.getGenres().get(app.getGenres().size() - 1);
        genreObj.writeToFile("database/genres/" + id, genreObj);
    }
}
