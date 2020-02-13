package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.objects.Actor;
import com.company.objects.Director;

import java.nio.file.Paths;
import java.util.Scanner;

public class MovieUpdater {

    private App app;
    private CrewAdder crewAdder;
    private InputChecker checker;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private String id ="";
    private String firstName;
    private String lastName;

    public MovieUpdater(App app) {
        this.app = app;
        this.crewAdder = new CrewAdder(app);
        this.checker = new InputChecker(app);
    }

    public void addDirectorToMovie(Object o) {
        fileManager.showListOfOptions(app.getMovies());
        String input = scan.nextLine();
        int movieChoice = Integer.parseInt(input) - 1;
        do {
            fileManager.showListOfOptions(app.getDirectors());
            int newDirector = app.getDirectors().size() + 1;
            System.out.println("[" + newDirector + "] Lägg till regissör");
            System.out.println("Välj ett alternativ ovan!");
            String directorChoice = scan.nextLine();
            int choice = Integer.parseInt(directorChoice);
            if (choice < newDirector) {
                addExistingDirectorToMovie(choice, movieChoice);
                inputOk = true;
            } else if (choice == newDirector) {
                addNewDirectorToMovie(movieChoice);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingDirectorToMovie(int choice, int movieChoice) {
        for (int i = 0; i < app.getDirectors().size(); i++) {
            if (choice - 1 == i) {
                id = app.getDirectors().get(i).getId();
                for (Director director : app.getDirectors()) {
                    if (director.getId().equals(id)) {
                        director.addToFilmography(app.getMovies().get(movieChoice));
                        app.getMovies().get(movieChoice).addToDirector(director);
                        fileManager.deleteFiles(Paths.get("database/directors/" + id + ".txt"));
                        fileManager.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(i));
                        fileManager.deleteFiles(Paths.get("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt"));
                        fileManager.writeToFile("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt", app.getMovies().get(movieChoice));
                    }
                }
            }
        }
    }

    private void addNewDirectorToMovie(int movieChoice) {
        inputName();

        app.getDirectors().add(new Director(firstName, lastName, app.getMovies().get(movieChoice)));
        Director directorObj = app.getDirectors().get(app.getDirectors().size() - 1);
        id = app.getDirectors().get(app.getDirectors().size() - 1).getId();
        fileManager.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(app.getDirectors().size() - 1));
        app.getMovies().get(movieChoice).addToDirector(directorObj);
        fileManager.deleteFiles(Paths.get("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt"));
        fileManager.writeToFile("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt", app.getMovies().get(movieChoice));
    }

    public void addActorToMovie(Object o) {
        fileManager.showListOfOptions(app.getMovies());
        String input = scan.nextLine();
        int movieChoice = Integer.parseInt(input) - 1;
        do {
            fileManager.showListOfOptions(app.getActors());
            int newActor = app.getActors().size() + 1;
            System.out.println("[" + newActor + "] Lägg till skådespelare");
            System.out.println("Välj ett alternativ ovan!:");
            String actorChoice = scan.nextLine();
            int choice = Integer.parseInt(actorChoice);
            if (choice < newActor) {
                addExistingActorToMovie(choice, movieChoice);
                inputOk = true;
            } else if (choice == newActor) {
                addNewActorToMovie(movieChoice);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingActorToMovie(int choice, int movieChoice) {
        for (int i = 0; i < app.getActors().size(); i++) {
            if (choice - 1 == i) {
                id = app.getActors().get(i).getId();
                for (Actor actor : app.getActors()) {
                    if (actor.getId().equals(id)) {
                        actor.addToFilmography(app.getMovies().get(movieChoice));
                        app.getMovies().get(movieChoice).addToCast(actor);
                        fileManager.deleteFiles(Paths.get("database/actors/" + id + ".txt"));
                        fileManager.writeToFile("database/actors/" + id + ".txt", app.getActors().get(i));
                        fileManager.deleteFiles(Paths.get("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt"));
                        fileManager.writeToFile("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt", app.getMovies().get(movieChoice));

                    }
                }
            }
        }
    }

    private void addNewActorToMovie(int movieChoice) {
        inputName();
        app.getActors().add(new Actor(firstName, lastName, app.getMovies().get(movieChoice)));
        Actor actorObj = app.getActors().get(app.getActors().size() - 1);
        id = app.getActors().get(app.getActors().size() - 1).getId();
        fileManager.writeToFile("database/actors/" + id + ".txt", app.getActors().get(app.getActors().size() - 1));
        app.getMovies().get(movieChoice).addToCast(actorObj);
        fileManager.deleteFiles(Paths.get("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt"));
        fileManager.writeToFile("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt", app.getMovies().get(movieChoice));
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
