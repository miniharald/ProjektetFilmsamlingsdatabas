package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.objects.*;

import java.nio.file.Paths;
import java.util.Scanner;

public class ObjectRemover {

    private App app;
    private FileManager fileManager = new FileManager();
    private ObjectLister objectLister = new ObjectLister(app);
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;

    public ObjectRemover(App app) {
        this.app = app;
    }

    private void listMoviesToRemove() {
        do {
            objectLister.listMovies();
            int goBack = app.getMovies().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeMovieFromCrew(choice);
                removeMovie(choice);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listGenresToRemove() {
        do {
            objectLister.listGenres();
            int goBack = app.getGenres().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeGenreFromMovie(choice);
                removeGenre(choice);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listActorsToRemove() {
        do {
            objectLister.listActors();
            int goBack = app.getActors().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeActor(choice);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listDriectorsToRemove() {
        do {
            objectLister.listDirectors();
            int goBack = app.getDirectors().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeDirector(choice);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private void listAwardToRemove() {
        do {
            objectLister.listAwards();
            int goBack = app.getAwards().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeAwardFromMovieAndCrew(choice);
                removeAward(choice);
                inputOk = true;
            } else if (choice == goBack) {
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
                removeFormat(choice);
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

    private void removeAwardFromMovieAndCrew(int choice) {
        String id = app.getAwards().get(choice - 1).getId();
        for (Movie movie : app.getMovies()) {
            for (AcademyAward award : movie.getAwards()) {
                if (award.getId().equals(id)) {
                    movie.getAwards().remove(award);
                    fileManager.deleteFiles(Paths.get("database/movies/" + movie.getId() + ".txt"));
                    fileManager.writeToFile("database/movies/" + movie.getId() + ".txt", movie);
                }
            }
        }
        for (Director director : app.getDirectors()) {
            for (AcademyAward award : director.getAwards()) {
                if (award.getId().equals(id)) {
                    director.getFilmography().remove(award);
                    fileManager.deleteFiles(Paths.get("database/directors/" + director.getId() + ".txt"));
                    fileManager.writeToFile("database/directors/" + director.getId() + ".txt", director);
                }
            }
        }
        for (Actor actor : app.getActors()) {
            for (AcademyAward award : actor.getAwards()) {
                if (award.getId().equals(id)) {
                    actor.getFilmography().remove(award);
                    fileManager.deleteFiles(Paths.get("database/actors/" + actor.getId() + ".txt"));
                    fileManager.writeToFile("database/actors/" + actor.getId() + ".txt", actor);
                }
            }
        }
    }

    private void removeGenreFromMovie(int choice) {
        String id = app.getGenres().get(choice - 1).getId();
        for (Movie movie : app.getMovies()) {
            for (Genre genre : movie.getGenre()) {
                if (genre.getId().equals(id)) {
                    movie.getGenre().remove(genre);
                    fileManager.deleteFiles(Paths.get("database/movies/" + movie.getId() + ".txt"));
                    fileManager.writeToFile("database/movies/" + movie.getId() + ".txt", movie);
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

    private void removeActor(int choice) {
        app.getActors().remove(app.getActors().get(choice - 1));
        fileManager.deleteFiles(Paths.get("database/actors/" + app.getActors().get(choice - 1).getId() + ".txt"));
    }

    private void removeDirector(int choice) {
        app.getActors().remove(app.getActors().get(choice - 1));
        fileManager.deleteFiles(Paths.get("database/actors/" + app.getActors().get(choice - 1).getId() + ".txt"));
    }
}
