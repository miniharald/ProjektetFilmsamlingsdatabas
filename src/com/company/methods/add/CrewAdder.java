package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.objects.Actor;
import com.company.objects.Director;
import com.company.objects.MovieObjects;
import java.util.List;
import java.util.Scanner;

public class CrewAdder {

    private App app;
    private InputChecker checker;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private String firstName;
    private String lastName;

    public CrewAdder(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
    }

    void addCrewToMovie(List<BaseObject> list, String folder, MovieObjects crew, String crewType) {
        do {
            fileManager.showListOfOptions(list);
            int newCrew = list.size() + 1;
            System.out.println(newCrew + ".) Lägg till " + crewType);
            System.out.println("Välj ett alternativ ovan!");
            String crewChoice = scan.nextLine();
            int choice = Integer.parseInt(crewChoice);
            if (choice < newCrew) {
                addExistingCrewToMovie(choice, list, folder, crew);
                inputOk = true;
            } else if (choice == newCrew) {
                addNewCrewToMovie(list, folder, crew);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addNewCrewToMovie(List<BaseObject> list, String folder, MovieObjects crew) {
        inputName();
        switch (crew) {
            case actor:
                app.getActors().add(new Actor(firstName, lastName, app.getMovies().get(app.getMovies().size() - 1)));
                app.getMovies().get(app.getMovies().size() - 1).addToCast(app.getActors().get(app.getActors().size() - 1));
                break;
            case director:
                app.getDirectors().add(new Director(firstName, lastName, app.getMovies().get(app.getMovies().size() - 1)));
                app.getMovies().get(app.getMovies().size() - 1).addToDirector(app.getDirectors().get(app.getDirectors().size() - 1));
                break;
        }
        fileManager.writeToFile(folder + list.get(list.size() - 1).getId() + ".txt", list.get(list.size() - 1));
    }

    private void addExistingCrewToMovie(int choice, List<BaseObject> list, String folder, MovieObjects crew) {
        switch (crew) {
            case director:
                Director director = (Director) list.get(choice - 1);
                director.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                app.getMovies().get(app.getMovies().size() - 1).addToDirector(director);
                break;
            case actor:
                Actor actor = (Actor) list.get(choice - 1);
                actor.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                app.getMovies().get(app.getMovies().size() - 1).addToCast(actor);
                break;
        }
        fileManager.writeToFile(folder + list.get(choice - 1).getId() + ".txt", list.get(choice - 1));
    }

    void addNewCrew(List<BaseObject> list, String folder, MovieObjects crew) {
        inputName();
        switch (crew) {
            case director:
                app.getDirectors().add(new Director(firstName, lastName));
                break;
            case actor:
                app.getActors().add(new Actor(firstName, lastName));
                break;
        }
        fileManager.writeToFile(folder + list.get(list.size() - 1).getId() + ".txt", list.get(list.size() - 1));
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