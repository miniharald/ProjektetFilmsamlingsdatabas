package com.company.methods.addmovie;

import com.company.App;
import com.company.methods.Check;
import com.company.objects.Actor;
import com.company.objects.Director;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class CrewToMovieAdder {

    private App app;
    private Check checker;
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private boolean isDuplicate;
    private String id ="";
    private String firstName;
    private String lastName;

    public CrewToMovieAdder(App app) {
        this.app = app;
        this.checker = new Check(app);
    }

    public void addDirector() {
        do {
            int counter = 1;
            for (Director directorObj : app.getDirectors()) {
                System.out.println("[" + counter + "]" + " " + directorObj.getFirstName() + " " + directorObj.getLastName());
                counter++;
            }
            System.out.println("[" + counter + "] Lägg till regissör");
            System.out.println("Välj ett alternativ ovan!");
            String directorChoice = scan.nextLine();
            int choice = Integer.parseInt(directorChoice);
            if (choice < counter) {
                addExistingDirector(choice);
                inputOk = true;
            } else if (choice == counter) {
                addNewDirector();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingDirector(int choice) {
        for (int i = 0; i < app.getDirectors().size(); i++) {
            if (choice - 1 == i) {
                id = app.getDirectors().get(i).getId();
                for (Director director : app.getDirectors()) {
                    if (director.getId().equals(id)) {
                        director.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        app.getMovies().get(app.getMovies().size() - 1).addToDirector(director);
                        director.deleteFiles(Paths.get("database/directors/" + id + ".txt"));
                        director.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(i));
                    }
                }
            }
        }
    }

    private void addNewDirector() {
        inputName();
        do {
            Director directorObj = new Director();
            id = directorObj.getId();
            File folderPath = new File("database/directors/");
            isDuplicate = directorObj.checkForDuplicateFileNames(folderPath, id);
        } while (isDuplicate);
        app.getDirectors().add(new Director(firstName, lastName, id, app.getMovies().get(app.getMovies().size() - 1)));
        Director directorObj = app.getDirectors().get(app.getDirectors().size() - 1);
        directorObj.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(app.getDirectors().size() - 1));
        app.getMovies().get(app.getMovies().size() - 1).addToDirector(directorObj);
    }

    public void addActor() {
        do {
            int counter = 1;
            for (Actor actor : app.getActors()) {
                System.out.println("[" + counter + "]" + " " + actor.getFirstName() + " " + actor.getLastName());
                counter++;
            }
            System.out.println("[" + counter + "] Lägg till skådespelare");
            System.out.println("Välj ett alternativ ovan!:");
            String actorChoice = scan.nextLine();
            int choice = Integer.parseInt(actorChoice);
            if (choice < counter) {
                addExistingActor(choice);
                inputOk = true;
            } else if (choice == counter) {
                addNewActor();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingActor(int choice) {
        for (int i = 0; i < app.getActors().size(); i++) {
            if (choice - 1 == i) {
                id = app.getActors().get(i).getId();
                for (Actor actor : app.getActors()) {
                    if (actor.getId().equals(id)) {
                        actor.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        app.getMovies().get(app.getMovies().size() - 1).addToCast(actor);
                        actor.deleteFiles(Paths.get("database/actors/" + id + ".txt"));
                        actor.writeToFile("database/actors/" + id + ".txt", app.getActors().get(i));
                    }
                }
            }
        }
    }

    private void addNewActor() {
        inputName();
        do {
            Actor actorObj = new Actor();
            id = actorObj.getId();
            File folderPath = new File("database/actors/");
            isDuplicate = actorObj.checkForDuplicateFileNames(folderPath, id);
        } while (isDuplicate);
        app.getActors().add(new Actor(firstName, lastName, id, app.getMovies().get(app.getMovies().size() - 1)));
        Actor actorObj = app.getActors().get(app.getActors().size() - 1);
        actorObj.writeToFile("database/actors/" + id + ".txt", app.getActors().get(app.getActors().size() - 1));
        app.getMovies().get(app.getMovies().size() - 1).addToCast(actorObj);
    }

    private void inputName() {
        firstName = "";
        do {
            System.out.println("Förnamn:");
            firstName = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(firstName);
            if (firstName.length() < 1 || firstName.isBlank()) {
                System.out.println("Namnet måste innehålla minst en bokstav!");
                inputOk = false;
            }
        }while (!inputOk);
        lastName = "";
        do {
            System.out.println("Efternamn:");
            lastName = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(lastName);
            if (lastName.length() < 1 || lastName.isBlank()) {
                System.out.println("Namnet måste innehålla minst en bokstav!");
                inputOk = false;
            }
        }while (!inputOk);
    }
}