package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.methods.ObjectLister;
import com.company.objects.AcademyAward;
import com.company.objects.Genre;
import java.util.Scanner;

public class GenreAndAwardAdder {

    private App app;
    private InputChecker checker;
    private FileManager fileManager = new FileManager();
    private ObjectLister objectLister;
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private String id = "";
    private String input;

    public GenreAndAwardAdder(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
        this.objectLister = new ObjectLister(app);
    }

    public void addGenreToMovie() {
        do {
            objectLister.listGenres();
            int newGenre = app.getGenres().size() + 1;
            System.out.println("[" + newGenre + "]" + " " + "Lägg till genre");
            System.out.println("Välj ett alternativ ovan!");
            String genreChoice = scan.nextLine();
            int choice = Integer.parseInt(genreChoice);
            if (choice < newGenre) {
                addExistingGenreToMovie(choice);
                inputOk = true;
            } else if (choice == newGenre) {
                addNewGenre();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingGenreToMovie(int choice) {
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

    public void addNewGenre() {
        inputNames("Genre");
        app.getGenres().add(new Genre(input));
        Genre genre = app.getGenres().get(app.getGenres().size() - 1);
        id = app.getGenres().get(app.getGenres().size() - 1).getId();
        fileManager.writeToFile("database/genres/" + id + ".txt", genre);
        app.getMovies().get(app.getMovies().size() - 1).addToGenre(genre);
    }

    public void addAwardToMovie() {
        do {
            objectLister.listAwards();
            int newAward = app.getAwards().size() + 1;
            System.out.println("[" + newAward + "]" + " " + "Lägg till Oscar");
            System.out.println("Välj ett alternativ ovan!");
            String awardChoice = scan.nextLine();
            int choice = Integer.parseInt(awardChoice);
            if (choice < newAward) {
                addExistingAwardToMovie(choice);
                inputOk = true;
            } else if (choice == newAward) {
                addNewAward();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingAwardToMovie(int choice) {
        for (int i = 0; i < app.getAwards().size(); i++) {
            if (choice - 1 == i) {
                id = app.getAwards().get(i).getId();
                for (AcademyAward award : app.getAwards()) {
                    if (award.getId().equals(id)) {
                        app.getMovies().get(app.getMovies().size() - 1).addToAwards(award);
                    }
                }
            }
        }
    }

    public void addNewAward() {
        inputNames("Oscar");
        app.getAwards().add(new AcademyAward(input));
        AcademyAward award = app.getAwards().get(app.getAwards().size() - 1);
        id = app.getAwards().get(app.getAwards().size() - 1).getId();
        fileManager.writeToFile("database/awards/" + id + ".txt", award);
    }

    public void areYouAddingAward() {
        System.out.println("Tryck 1 för att lägga till en Oscars. Annars valfri bokstav eller siffra!");
        String input = scan.nextLine();
        if(input.equals("1")) {
            addAwardToMovie();
        }
        return;
    }

    private void inputNames(String type) {
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