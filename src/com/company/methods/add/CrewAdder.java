package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
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
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private String id ="";
    private String firstName;
    private String lastName;

    public CrewAdder(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
    }

    void addDirectorToMovie() {
        do {
            fileManager.showListOfOptions(app.getDirectors());
            int newDirector = app.getDirectors().size() + 1;
            System.out.println(newDirector + ".) Lägg till regissör");
            System.out.println("Välj ett alternativ ovan!");
            String directorChoice = scan.nextLine();
            int choice = Integer.parseInt(directorChoice);
            if (choice < newDirector) {
                addExistingCrewToMovie(choice, Collections.unmodifiableList(app.getDirectors()), App.DIRECTORFOLDER, MovieObjects.director);
                inputOk = true;
            } else if (choice == newDirector) {
                addNewCrewToMovie(Collections.unmodifiableList(app.getDirectors()), App.DIRECTORFOLDER, MovieObjects.director);
                inputOk = true;
            }
        } while (!inputOk);
    }

    void addActorToMovie() {
        do {
            fileManager.showListOfOptions(app.getActors());
            int newActor = app.getActors().size() + 1;
            System.out.println(newActor + ".) Lägg till skådespelare");
            System.out.println("Välj ett alternativ ovan!:");
            String actorChoice = scan.nextLine();
            int choice = Integer.parseInt(actorChoice);
            if (choice < newActor) {
                addExistingCrewToMovie(choice, Collections.unmodifiableList(app.getActors()), App.ACTORFOLDER, MovieObjects.actor);
                inputOk = true;
            } else if (choice == newActor) {
                addNewCrewToMovie(Collections.unmodifiableList(app.getActors()), App.ACTORFOLDER, MovieObjects.actor);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addNewCrewToMovie(List<BaseObject> list, String folder, MovieObjects movieObjects) {
        inputName();
        switch (movieObjects) {
            case actor:
                app.getActors().add(new Actor(firstName, lastName, app.getMovies().get(app.getMovies().size() - 1)));
                app.getMovies().get(app.getMovies().size() - 1).addToCast(app.getActors().get(app.getActors().size() - 1));
                break;
            case director:
                app.getDirectors().add(new Director(firstName, lastName, app.getMovies().get(app.getMovies().size() - 1)));
                app.getMovies().get(app.getMovies().size() - 1).addToDirector(app.getDirectors().get(app.getDirectors().size() - 1));
                break;
        }
        id = list.get(list.size() - 1).getId();
        fileManager.writeToFile(folder + id + ".txt", list.get(list.size() - 1));

    }

    private void addExistingCrewToMovie(int choice, List<BaseObject> list, String folder, MovieObjects movieObjects) {
        id = list.get(choice - 1).getId();
        for (BaseObject baseObject : list) {
            if (baseObject.getId().equals(id)) {
                switch (movieObjects) {
                    case director:
                        Director director = (Director) baseObject;
                        director.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        app.getMovies().get(app.getMovies().size() - 1).addToDirector(director);
                        break;
                    case actor:
                        Actor actor = (Actor) baseObject;
                        actor.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        app.getMovies().get(app.getMovies().size() - 1).addToCast(actor);
                        break;
                }
                fileManager.deleteFiles(Paths.get(folder + id + ".txt"));
                fileManager.writeToFile(folder + id + ".txt", list.get(choice - 1));
            }
        }
    }

    void addNewCrew(List<BaseObject> list, String folder, MovieObjects movieObjects) {
        inputName();
        switch (movieObjects) {
            case director:
                app.getDirectors().add(new Director(firstName, lastName));
                break;
            case actor:
                app.getActors().add(new Actor(firstName, lastName));
                break;
        }
        fileManager.writeToFile(folder + id + ".txt", list.get(list.size() - 1));
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