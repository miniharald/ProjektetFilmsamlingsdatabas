package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.objects.*;

import java.nio.file.Paths;
import java.util.Scanner;

public class ObjectRemover {

    private App app;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;

    public ObjectRemover(App app) {
        this.app = app;
    }

    private void listMoviesToRemove() {
        do {
            int counter = 1;
            for (Movie movie : app.getMovies()) {
                System.out.println("[" + counter + "]" + " " + movie.getTitle());
                counter++;
            }
            int goBack = app.getMovies().size() + 1;
            int choice = getChoice(goBack);
            if (choice < counter) {
                removeMovieFromCrew(choice);
                removeMovie(choice);
                inputOk = true;
            } else if (choice == counter) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listGenresToRemove() {
        do {
            int counter = 1;
            for (Genre genre : app.getGenres()) {
                System.out.println("[" + counter + "]" + " " + genre.getName());
                counter++;
            }
            int goBack = app.getGenres().size() + 1;
            int choice = getChoice(goBack);
            if (choice < counter) {
                removeGenre(choice);
                inputOk = true;
            } else if (choice == counter) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listActorsToRemove() {
        do {
            int counter = 1;
            for (Actor actor : app.getActors()) {
                System.out.println("[" + counter + "]" + " " + actor.getName());
                counter++;
            }
            int goBack = app.getActors().size() + 1;
            int choice = getChoice(goBack);
            if (choice < counter) {
                removeActorschoice);
                inputOk = true;
            } else if (choice == counter) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listAwardToRemove() {
        do {
            int counter = 1;
            for (AcademyAward award : app.getAwards()) {
                System.out.println("[" + counter + "]" + " " + award.getName());
                counter++;
            }
            int goBack = app.getAwards().size() + 1;
            int choice = getChoice(goBack);
            if (choice < counter) {
                removeAward(choice);
                inputOk = true;
            } else if (choice == counter) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listFormatToRemove() {
        do {
            int counter = 1;
            for (Format format : app.getFormats()) {
                System.out.println("[" + counter + "]" + " " + format.getName());
                counter++;
            }
            int goBack = app.getFormats().size() + 1;
            int choice = getChoice(goBack);
            if (choice < counter) {
                removeAward(choice);
                inputOk = true;
            } else if (choice == counter) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private int getChoice(int goBack) {
        System.out.println("[" + goBack + "]" + " " + "Gå tillbaka");
        System.out.println("Välj ett alternativ ovan!");
        String inputChoice = scan.nextLine();
        int choice = Integer.parseInt(inputChoice);
        return choice;
    }

    private void removeMovieFromCrew(int choice) {
        String id = app.getMovies().get(choice - 1).getId();
        for (Actor actor : app.getActors()) {
            for (Movie movie : actor.getFilmography()) {
                if (movie.getId().equals(id)) {
                    actor.getFilmography().remove(movie);
                    fileManager.deleteFiles(Paths.get("database/actors/" + actor.getId() + ".txt"));
                    fileManager.writeToFile("database/actors/" + actor.getId() + ".txt", actor);
                }
            }
        }
        for (Director director : app.getDirectors()) {
            for (Movie movie : director.getFilmography()) {
                if (movie.getId().equals(id)) {
                    director.getFilmography().remove(movie);
                    fileManager.deleteFiles(Paths.get("database/directors/" + director.getId() + ".txt"));
                    fileManager.writeToFile("database/directors/" + director.getId() + ".txt", director);
                }
            }
        }
    }

    private void removeMovie(int choice) {
        app.getMovies().remove(app.getMovies().get(choice - 1));
        fileManager.deleteFiles(Paths.get("database/movies/" + app.getMovies().get(choice - 1).getId() + ".txt"));
    }

    private void removeGenre(int choice) {
        app.getGenres().remove(app.getGenres().get(choice - 1));
        fileManager.deleteFiles(Paths.get("database/genres/" + app.getGenres().get(choice - 1).getId() + ".txt"));
    }

    private void removeAward(int choice) {
        app.getAwards().remove(app.getAwards().get(choice - 1));
        fileManager.deleteFiles(Paths.get("database/awards/" + app.getAwards().get(choice - 1).getId() + ".txt"));
    }

    private void removeFormat(int choice) {
        app.getFormats().remove(app.getFormats().get(choice - 1));
        fileManager.deleteFiles(Paths.get("database/formats/" + app.getFormats().get(choice - 1).getId() + ".txt"));
    }
}
