package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.methods.ObjectLister;
import com.company.objects.Actor;
import com.company.objects.Director;
import com.company.objects.MovieObjects;

import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;
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
            fileManager.showListOfOptions(app.getDirectors());
            int newDirector = app.getDirectors().size() + 1;
            System.out.println(newDirector + ".) Lägg till regissör");
            System.out.println("Välj ett alternativ ovan!");
            String directorChoice = scan.nextLine();
            int choice = Integer.parseInt(directorChoice);
            if (choice < newDirector) {
                addExistingDirectorToMovie(choice, Collections.unmodifiableList(app.getDirectors()), MovieObjects.director);
                inputOk = true;
            } else if (choice == newDirector) {
                addNewDirectorToMovie();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingDirectorToMovie(int choice, List<BaseObject> list, MovieObjects movieObjects) {
        for (int i = 0; i < list.size(); i++) {
            if (choice - 1 == i) {
                id = list.get(i).getId();
                for (BaseObject baseObject : list) {
                    if (baseObject.getId().equals(id)) {
                        switch (movieObjects) {
                            case director:
                                Director director = (Director) baseObject;
                                director.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                                app.getMovies().get(app.getMovies().size() - 1).addToDirector(director);
                                fileManager.deleteFiles(Paths.get(App.DIRECTORFOLDER + id + ".txt"));
                                fileManager.writeToFile(App.DIRECTORFOLDER + id + ".txt", app.getDirectors().get(i));
                                break;
                            case actor:
                                Actor actor = (Actor) baseObject;
                                actor.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                                app.getMovies().get(app.getMovies().size() - 1).addToCast(actor);
                                fileManager.deleteFiles(Paths.get(App.ACTORFOLDER + id + ".txt"));
                                fileManager.writeToFile(App.ACTORFOLDER + id + ".txt", app.getActors().get(i));
                        }
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
        fileManager.writeToFile(App.DIRECTORFOLDER + id + ".txt", app.getDirectors().get(app.getDirectors().size() - 1));
        app.getMovies().get(app.getMovies().size() - 1).addToDirector(directorObj);
    }

    public void addActorToMovie() {
        do {
            fileManager.showListOfOptions(app.getActors());
            int newActor = app.getActors().size() + 1;
            System.out.println(newActor + ".) Lägg till skådespelare");
            System.out.println("Välj ett alternativ ovan!:");
            String actorChoice = scan.nextLine();
            int choice = Integer.parseInt(actorChoice);
            if (choice < newActor) {
                addExistingDirectorToMovie(choice, Collections.unmodifiableList(app.getActors()), MovieObjects.actor);
                inputOk = true;
            } else if (choice == newActor) {
                addNewActorToMovie();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addNewActorToMovie() {
        inputName();
        app.getActors().add(new Actor(firstName, lastName, app.getMovies().get(app.getMovies().size() - 1)));
        Actor actorObj = app.getActors().get(app.getActors().size() - 1);
        id = app.getActors().get(app.getActors().size() - 1).getId();
        fileManager.writeToFile(App.ACTORFOLDER + id + ".txt", app.getActors().get(app.getActors().size() - 1));
        app.getMovies().get(app.getMovies().size() - 1).addToCast(actorObj);
    }

    public void addNewDirector() {
        inputName();
        app.getDirectors().add(new Director(firstName, lastName));
        fileManager.writeToFile(App.DIRECTORFOLDER + id + ".txt", app.getDirectors().get(app.getDirectors().size() - 1));
    }

    public void addNewActor() {
        inputName();
        app.getActors().add(new Actor(firstName, lastName));
        fileManager.writeToFile(App.ACTORFOLDER + id + ".txt", app.getActors().get(app.getActors().size() - 1));
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