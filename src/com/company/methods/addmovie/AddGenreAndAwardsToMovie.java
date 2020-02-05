package com.company.methods.addmovie;

import com.company.FileManager;
import com.company.objects.AcademyAward;
import com.company.App;
import com.company.objects.Genre;
import com.company.methods.Check;

import java.io.File;
import java.util.Scanner;

public class AddGenreAndAwardsToMovie {

    private App app;
    private Check checker;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private boolean isDuplicate;
    private String id = "";
    private String input;

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
        inputNames("Genre");
        //do {
            Genre genre = new Genre();
            id = genre.getId();
            //File folderPath = new File("database/genre/");
            //isDuplicate = genre.checkForDuplicateFileNames(folderPath, id);
        //} while (isDuplicate);
        app.getGenres().add(new Genre(input));
        genre = app.getGenres().get(app.getGenres().size() - 1);
        fileManager.writeToFile("database/genres/" + id + ".txt", genre);
    }

    public void addAward() {
        do {
            int awardCounter = 1;
            for (AcademyAward awardObj : app.getAwards()) {
                System.out.println("[" + awardCounter + "]" + " " + awardObj.getName());
                awardCounter++;
            }
            int newAward = app.getAwards().size() + 1;
            System.out.println("[" + newAward + "]" + " " + "Lägg till Oscar");
            System.out.println("Välj ett alternativ ovan!");
            String awardChoice = scan.nextLine();
            int choice = Integer.parseInt(awardChoice);
            if (choice < awardCounter) {
                addExistingAward(choice);
                inputOk = true;
            } else if (choice == awardCounter) {
                addNewAward();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingAward(int choice) {
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

    private void addNewAward() {
        inputNames("Oscar");
        //do {
            AcademyAward award = new AcademyAward();
            id = award.getId();
            //File folderPath = new File("database/awards/");
            //isDuplicate = award.checkForDuplicateFileNames(folderPath, id);
        //} while (isDuplicate);
        app.getAwards().add(new AcademyAward(input));
        award = app.getAwards().get(app.getAwards().size() - 1);
        fileManager.writeToFile("database/awards/" + id + ".txt", award);
    }

    public void areYouAddingAward() {
        System.out.println("Tryck 1 för att lägga till en Oscars. Annars valfri bokstav eller siffra!");
        String input = scan.nextLine();
        if(input.equals("1")) {
            addAward();
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
