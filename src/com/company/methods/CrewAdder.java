package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.methods.ObjectLister;
import com.company.objects.Actor;
import com.company.objects.Director;

import java.nio.file.Paths;
import java.util.Scanner;

public class CrewAdder {

    private App app;
    private InputChecker checker;
    private FileManager fileManager = new FileManager();
    private ObjectLister objectLister;
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private String id ="";
    private String firstName;
    private String lastName;

    public CrewAdder(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
        this.objectLister = new ObjectLister(app);
    }

    public void addDirectorToMovie() {
        do {
            objectLister.listDirectors();
            int newDirector = app.getDirectors().size() + 1;
            System.out.println("[" + newDirector + "] Lägg till regissör");
            System.out.println("Välj ett alternativ ovan!");
            String directorChoice = scan.nextLine();
            int choice = Integer.parseInt(directorChoice);
            if (choice < newDirector) {
                addExistingDirectorToMovie(choice);
                inputOk = true;
            } else if (choice == newDirector) {
                addNewDirectorToMovie();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingDirectorToMovie(int choice) {
        for (int i = 0; i < app.getDirectors().size(); i++) {
            if (choice - 1 == i) {
                id = app.getDirectors().get(i).getId();
                for (Director director : app.getDirectors()) {
                    if (director.getId().equals(id)) {
                        director.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        app.getMovies().get(app.getMovies().size() - 1).addToDirector(director);
                        fileManager.deleteFiles(Paths.get("database/directors/" + id + ".txt"));
                        fileManager.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(i));
                    }
                }
            }
        }
    }

    private void addNewDirectorToMovie() {
        inputName();

        app.getDirectors().add(new Director(firstName, lastName, app.getMovies().get(app.getMovies().size() - 1)));
        Director directorObj = app.getDirectors().get(app.getDirectors().size() - 1);
        id = app.getDirectors().get(app.getDirectors().size() - 1).getId();
        fileManager.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(app.getDirectors().size() - 1));
        app.getMovies().get(app.getMovies().size() - 1).addToDirector(directorObj);
    }

    public void addActorToMovie() {
        do {
            objectLister.listActors();
            int newActor = app.getActors().size() + 1;
            System.out.println("[" + newActor + "] Lägg till skådespelare");
            System.out.println("Välj ett alternativ ovan!:");
            String actorChoice = scan.nextLine();
            int choice = Integer.parseInt(actorChoice);
            if (choice < newActor) {
                addExistingActorToMovie(choice);
                inputOk = true;
            } else if (choice == newActor) {
                addNewActorToMovie();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingActorToMovie(int choice) {
        for (int i = 0; i < app.getActors().size(); i++) {
            if (choice - 1 == i) {
                id = app.getActors().get(i).getId();
                for (Actor actor : app.getActors()) {
                    if (actor.getId().equals(id)) {
                        actor.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        app.getMovies().get(app.getMovies().size() - 1).addToCast(actor);
                        fileManager.deleteFiles(Paths.get("database/actors/" + id + ".txt"));
                        fileManager.writeToFile("database/actors/" + id + ".txt", app.getActors().get(i));
                    }
                }
            }
        }
    }

    private void addNewActorToMovie() {
        inputName();
        app.getActors().add(new Actor(firstName, lastName, app.getMovies().get(app.getMovies().size() - 1)));
        Actor actorObj = app.getActors().get(app.getActors().size() - 1);
        id = app.getActors().get(app.getActors().size() - 1).getId();
        fileManager.writeToFile("database/actors/" + id + ".txt", app.getActors().get(app.getActors().size() - 1));
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