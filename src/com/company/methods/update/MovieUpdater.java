package com.company.methods.update;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.methods.CrewAdder;
import com.company.objects.*;

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
    private String input;

    public MovieUpdater(App app) {
        this.app = app;
        this.crewAdder = new CrewAdder(app);
        this.checker = new InputChecker(app);
    }

    private int chooseMovie() {
        fileManager.showListOfOptions(app.getMovies());
        String input = scan.nextLine();
        int movieChoice = Integer.parseInt(input) - 1;
        return movieChoice;
    }

    private void updateMovieFile(int movieChoice) {
        fileManager.deleteFiles(Paths.get("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt"));
        fileManager.writeToFile("database/movies/" + app.getMovies().get(movieChoice).getId() + ".txt", app.getMovies().get(movieChoice));
    }

    public void addDirectorToMovie(Object o) {
        int movieChoice = chooseMovie();
        do {
            fileManager.showListOfOptions(app.getDirectors());
            int newDirector = app.getDirectors().size() + 1;
            System.out.println(newDirector + ". Lägg till regissör");
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
                        updateMovieFile(movieChoice);
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
        updateMovieFile(movieChoice);
    }

    public void addActorToMovie(Object o) {
        int movieChoice = chooseMovie();
        do {
            fileManager.showListOfOptions(app.getActors());
            int newActor = app.getActors().size() + 1;
            System.out.println(newActor + ". Lägg till skådespelare");
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
                        updateMovieFile(movieChoice);
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
        updateMovieFile(movieChoice);
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

    public void addGenreToMovie(Object o) {
        int movieChoice = chooseMovie();
        do {
            fileManager.showListOfOptions(app.getGenres());
            int newGenre = app.getGenres().size() + 1;
            System.out.println("[" + newGenre + "]" + " " + "Lägg till genre");
            System.out.println("Välj ett alternativ ovan!");
            String genreChoice = scan.nextLine();
            int choice = Integer.parseInt(genreChoice);
            if (choice < newGenre) {
                addExistingGenreToMovie(choice, movieChoice);
                inputOk = true;
            } else if (choice == newGenre) {
                addNewGenre(movieChoice);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingGenreToMovie(int choice, int movieChoice) {
        for (int i = 0; i < app.getGenres().size(); i++) {
            if (choice - 1 == i) {
                id = app.getGenres().get(i).getId();
                for (Genre genre : app.getGenres()) {
                    if (genre.getId().equals(id)) {
                        app.getMovies().get(movieChoice).addToGenre(genre);
                        updateMovieFile(movieChoice);
                    }
                }
            }
        }
    }

    public void addNewGenre(int movieChoice) {
        inputNames("Genre");
        app.getGenres().add(new Genre(input));
        Genre genre = app.getGenres().get(app.getGenres().size() - 1);
        id = app.getGenres().get(app.getGenres().size() - 1).getId();
        fileManager.writeToFile("database/genres/" + id + ".txt", genre);
        app.getMovies().get(movieChoice).addToGenre(genre);
        updateMovieFile(movieChoice);
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

    public void changeFormatToMovie(Object o) {
        int movieChoice = chooseMovie();
        do {
            fileManager.showListOfOptions(app.getFormats());
            int newFormat = app.getFormats().size() + 1;
            System.out.println("[" + newFormat + "]" + " " + "Lägg till nytt format");
            System.out.println("Välj ett alternativ ovan!");
            String formatChoice = scan.nextLine();
            int choice = Integer.parseInt(formatChoice);
            if (choice < newFormat) {
                addExistingFormat(choice, movieChoice);
                inputOk = true;
            } else if (choice == newFormat) {
                addNewFormat(movieChoice);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingFormat(int choice, int movieChoice) {
        Format format =  null;
        for (int i = 0; i < app.getFormats().size(); i++) {
            if (choice - 1 == i) {
                format = app.getFormats().get(i);
                app.getMovies().get(movieChoice).setFormat(format);
                updateMovieFile(movieChoice);
            }
        }
    }

    private void addNewFormat(int movieChoice) {
        Format format = null;
        inputNames("Format");
        app.getFormats().add(new Format(input));
        format = app.getFormats().get(app.getFormats().size() - 1);
        fileManager.writeToFile("database/formats/" + format.getId() + ".txt", format);
        app.getMovies().get(movieChoice).setFormat(format);
        updateMovieFile(movieChoice);
    }

    public void addAwardToMovie(Object o) {
        int movieChoice = chooseMovie();
        do {
            fileManager.showListOfOptions(app.getAwards());
            int newAward = app.getAwards().size() + 1;
            System.out.println("[" + newAward + "]" + " " + "Lägg till Oscar");
            System.out.println("Välj ett alternativ ovan!");
            String awardChoice = scan.nextLine();
            int choice = Integer.parseInt(awardChoice);
            if (choice < newAward) {
                addExistingAwardToMovie(choice, movieChoice);
                inputOk = true;
            } else if (choice == newAward) {
                addNewAward(movieChoice);
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingAwardToMovie(int choice, int movieChoice) {
        for (int i = 0; i < app.getAwards().size(); i++) {
            if (choice - 1 == i) {
                id = app.getAwards().get(i).getId();
                for (AcademyAward award : app.getAwards()) {
                    if (award.getId().equals(id)) {
                        app.getMovies().get(movieChoice).addToAwards(award);
                        updateMovieFile(movieChoice);
                    }
                }
            }
        }
    }

    public void addNewAward(int movieChoice) {
        inputNames("Oscar");
        app.getAwards().add(new AcademyAward(input));
        AcademyAward award = app.getAwards().get(app.getAwards().size() - 1);
        id = app.getAwards().get(app.getAwards().size() - 1).getId();
        fileManager.writeToFile("database/awards/" + id + ".txt", award);
        app.getMovies().get(movieChoice).addToAwards(award);
        updateMovieFile(movieChoice);
    }
}
