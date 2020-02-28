package com.company.methods.update;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.objects.*;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MovieUpdater {

    private App app;
    private InputChecker checker;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private String id = "";
    private String firstName;
    private String lastName;
    private String input;

    public MovieUpdater(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
    }

    public void addDirector(Object o) {
        addCrew(Collections.unmodifiableList(app.getDirectors()), "regissör", MovieObjects.director, App.DIRECTORFOLDER);
    }

    public void addActor(Object o) {
        addCrew(Collections.unmodifiableList(app.getActors()), "skådespelare", MovieObjects.actor, App.ACTORFOLDER);
    }

    public void addCrew(List<BaseObject> list, String crewType, MovieObjects crew, String folder) {
        int movieChoice = chooseMovie();
        do {
            int newCrew = addNewOptionForList(list, crewType);
            String crewChoice = scan.nextLine();
            int choice = Integer.parseInt(crewChoice);
            if (choice < newCrew) {
                addExistingMovieObjects(choice, movieChoice, list, crew);
                inputOk = true;
            } else if (choice == newCrew) {
                addNewCrew(movieChoice, list, folder, crew);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addNewCrew(int movieChoice, List<BaseObject> list, String folder, MovieObjects crew) {
        inputCrewName();
        switch (crew) {
            case actor:
                app.getActors().add(new Actor(firstName, lastName, app.getMovies().get(movieChoice)));
                app.getMovies().get(movieChoice).addToCast(app.getActors().get(app.getActors().size() - 1));
                break;
            case director:
                app.getDirectors().add(new Director(firstName, lastName, app.getMovies().get(movieChoice)));
                app.getMovies().get(movieChoice).addToDirector(app.getDirectors().get(app.getDirectors().size() - 1));
                break;
        }
        fileManager.writeToFile(folder + list.get(list.size() - 1).getId() + ".txt", list.get(list.size() - 1));
        updateMovieFile(movieChoice);
    }

    public void addGenre(Object o) {
        int movieChoice = chooseMovie();
        do {
            int newGenre = addNewOptionForList(Collections.unmodifiableList(app.getGenres()), "genre");
            String genreChoice = scan.nextLine();
            int choice = Integer.parseInt(genreChoice);
            if (choice < newGenre) {
                addExistingMovieObjects(choice, movieChoice, Collections.unmodifiableList(app.getGenres()), MovieObjects.genre);
                inputOk = true;
            } else if (choice == newGenre) {
                addNewGenre(movieChoice, MovieObjects.genre, Collections.unmodifiableList(app.getGenres()));
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addNewGenre(int movieChoice, MovieObjects movieObjects, List<BaseObject> list) {
        inputMovieObjectNames(movieObjects);
        app.getGenres().add(new Genre(input));
        Genre genre = app.getGenres().get(app.getGenres().size() - 1);
        id = app.getGenres().get(app.getGenres().size() - 1).getId();
        fileManager.writeToFile(App.GENREFOLDER + id + ".txt", genre);
        app.getMovies().get(movieChoice).addToGenre(genre);
        updateMovieFile(movieChoice);
    }

    public void addAward(Object o) {
        int movieChoice = chooseMovie();
        do {
            int newAward = addNewOptionForList(Collections.unmodifiableList(app.getAwards()), "Oscars");
            String awardChoice = scan.nextLine();
            int choice = Integer.parseInt(awardChoice);
            if (choice < newAward) {
                addExistingMovieObjects(choice, movieChoice, Collections.unmodifiableList(app.getAwards()), MovieObjects.Oscars);
                inputOk = true;
            } else if (choice == newAward) {
                addNewAward(movieChoice, MovieObjects.Oscars);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addNewAward(int movieChoice, MovieObjects movieObjects) {
        inputMovieObjectNames(movieObjects);
        app.getAwards().add(new AcademyAward(input));
        AcademyAward award = app.getAwards().get(app.getAwards().size() - 1);
        id = app.getAwards().get(app.getAwards().size() - 1).getId();
        fileManager.writeToFile(App.AWARDFOLDER + id + ".txt", award);
        app.getMovies().get(movieChoice).addToAwards(award);
        updateMovieFile(movieChoice);
    }

    private void addExistingMovieObjects(int choice, int movieChoice, List<BaseObject> list, MovieObjects movieObjects) {
        for (int i = 0; i < list.size(); i++) {
            if (choice - 1 == i) {
                id = list.get(i).getId();
                for (BaseObject baseObject : list) {
                    if (baseObject.getId().equals(id)) {
                        switch (movieObjects) {
                            case genre:
                                app.getMovies().get(movieChoice).addToGenre((Genre) baseObject);
                                break;
                            case Oscars:
                                app.getMovies().get(movieChoice).addToAwards((AcademyAward) baseObject);
                                break;
                            case actor:
                                Actor actor = (Actor) baseObject;
                                actor.addToFilmography(app.getMovies().get(movieChoice));
                                app.getMovies().get(movieChoice).addToCast(actor);
                                fileManager.writeToFile(App.ACTORFOLDER + id + ".txt", app.getActors().get(i));
                                break;
                            case director:
                                Director director = (Director) baseObject;
                                director.addToFilmography(app.getMovies().get(movieChoice));
                                app.getMovies().get(movieChoice).addToDirector(director);
                                fileManager.writeToFile(App.DIRECTORFOLDER + id + ".txt", app.getDirectors().get(i));
                                break;
                        }
                        updateMovieFile(movieChoice);
                    }
                }
            }
        }
    }

    public void changeFormat(Object o) {
        int movieChoice = chooseMovie();
        do {
            int newFormat = addNewOptionForList(Collections.unmodifiableList(app.getFormats()), "format");
            String formatChoice = scan.nextLine();
            int choice = Integer.parseInt(formatChoice);
            if (choice < newFormat) {
                addExistingFormat(choice, movieChoice);
                inputOk = true;
            } else if (choice == newFormat) {
                addNewFormat(movieChoice, MovieObjects.format);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingFormat(int choice, int movieChoice) {
        Format format;
        for (int i = 0; i < app.getFormats().size(); i++) {
            if (choice - 1 == i) {
                format = app.getFormats().get(i);
                app.getMovies().get(movieChoice).setFormat(format);
                updateMovieFile(movieChoice);
            }
        }
    }

    private void addNewFormat(int movieChoice, MovieObjects movieObjects) {
        Format format;
        inputMovieObjectNames(movieObjects);
        app.getFormats().add(new Format(input));
        format = app.getFormats().get(app.getFormats().size() - 1);
        fileManager.writeToFile(App.FORMATFOLDER + format.getId() + ".txt", format);
        app.getMovies().get(movieChoice).setFormat(format);
        updateMovieFile(movieChoice);
    }

    private void inputCrewName() {
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

    private void inputMovieObjectNames(MovieObjects movieObjects) {
        input = "";
        do {
            System.out.println("Ny " + movieObjects + ":");
            input = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(input);
            if (input.length() < 1 || input.isBlank()) {
                System.out.println(movieObjects + " måste innehålla minst en bokstav!");
                inputOk = false;
            }
        } while (!inputOk);
    }

    private int chooseMovie() {
        fileManager.showListOfOptions(app.getMovies());
        String input = scan.nextLine();
        return Integer.parseInt(input) - 1;
    }

    private int addNewOptionForList(List<BaseObject> list, String movieObject) {
        fileManager.showListOfOptions(list);
        int addOption = list.size() + 1;
        System.out.println(addOption + ".) Lägg till " + movieObject);
        System.out.println("Välj ett alternativ ovan!");
        return addOption;
    }

    private void updateMovieFile(int movieChoice) {
        fileManager.writeToFile(App.MOVIEFOLDER + app.getMovies().get(movieChoice).getId() + ".txt", app.getMovies().get(movieChoice));
    }
}
