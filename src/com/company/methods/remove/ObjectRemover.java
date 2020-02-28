package com.company.methods.remove;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.objects.*;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ObjectRemover {

    private App app;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;

    public ObjectRemover(App app) {
        this.app = app;
    }

    public void removeMovie(Object o) {
        do {
            fileManager.showListOfOptions(app.getMovies());
            int goBack = app.getMovies().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeMovieFromCrew(choice);
                fileManager.removeDbObject(choice, app.getMovies(), App.MOVIEFOLDER);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    public void removeGenre(Object o) {
        do {
            fileManager.showListOfOptions(app.getGenres());
            int goBack = app.getGenres().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeGenreFromMovie(choice);
                fileManager.removeDbObject(choice, app.getGenres(), App.GENREFOLDER);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    public void removeActor(Object o) {
        do {
            fileManager.showListOfOptions(app.getActors());
            int goBack = app.getActors().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeCrewFromMovie(choice, Collections.unmodifiableList(app.getActors()), MovieObjects.actor);
                fileManager.removeDbObject(choice, app.getActors(), App.ACTORFOLDER);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    public void removeDirector(Object o) {
        do {
            fileManager.showListOfOptions(app.getDirectors());
            int goBack = app.getDirectors().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeCrewFromMovie(choice, Collections.unmodifiableList(app.getDirectors()), MovieObjects.director);
                fileManager.removeDbObject(choice, app.getDirectors(), App.DIRECTORFOLDER);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    public void removeAward(Object o) {
        do {
            fileManager.showListOfOptions(app.getAwards());
            int goBack = app.getAwards().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeAwardFromMovieAndCrew(choice);
                fileManager.removeDbObject(choice, app.getAwards(), App.AWARDFOLDER);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    public void removeFormat(Object o) {
        do {
            int goBack = app.getFormats().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                fileManager.removeDbObject(choice, app.getFormats(), App.FORMATFOLDER);
                inputOk = true;
            } else if (choice == goBack) {
                inputOk = true;
                return;
            }
        } while (!inputOk);
    }

    private int getChoice(int goBack) {
        System.out.println(goBack + ".) Gå tillbaka");
        System.out.println("Välj ett alternativ ovan!");
        String inputChoice = scan.nextLine();
        return Integer.parseInt(inputChoice) - 1;
    }

    private void removeMovieFromCrew(int choice) {
        String id = app.getMovies().get(choice).getId();
        for (Actor actor : app.getActors()) {
            for (Movie movie : actor.getFilmography()) {
                if (movie.getId().equals(id)) {
                    actor.getFilmography().remove(movie);
                    fileManager.writeToFile(App.ACTORFOLDER + actor.getId() + ".txt", actor);
                }
            }
        }
        for (Director director : app.getDirectors()) {
            for (Movie movie : director.getFilmography()) {
                if (movie.getId().equals(id)) {
                    director.getFilmography().remove(movie);
                    fileManager.writeToFile(App.DIRECTORFOLDER + director.getId() + ".txt", director);
                }
            }
        }
    }

    private void removeAwardFromMovieAndCrew(int choice) {
        String id = app.getAwards().get(choice).getId();
        for (Movie movie : app.getMovies()) {
            for (AcademyAward award : movie.getAwards()) {
                if (award.getId().equals(id)) {
                    movie.getAwards().remove(award);
                    fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                }
            }
        }
        for (Director director : app.getDirectors()) {
            for (AcademyAward award : director.getAwards()) {
                if (award.getId().equals(id)) {
                    director.getFilmography().remove(award);
                    fileManager.writeToFile(App.DIRECTORFOLDER + director.getId() + ".txt", director);
                }
            }
        }
        for (Actor actor : app.getActors()) {
            for (AcademyAward award : actor.getAwards()) {
                if (award.getId().equals(id)) {
                    actor.getFilmography().remove(award);
                    fileManager.writeToFile(App.ACTORFOLDER + actor.getId() + ".txt", actor);
                }
            }
        }
    }

    private void removeGenreFromMovie(int choice) {
        String id = app.getGenres().get(choice).getId();
        for (Movie movie : app.getMovies()) {
            for (Genre genre : movie.getGenre()) {
                if (genre.getId().equals(id)) {
                    movie.getGenre().remove(genre);
                    fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                }
            }
        }
    }

    private void removeCrewFromMovie(int choice, List<BaseObject> list, MovieObjects job) {
        String id = list.get(choice - 1).getId();
        for (Movie movie : app.getMovies()) {
            switch (job) {
                case actor:
                    for (Actor actor : movie.getCast()) {
                        if (actor.getId().equals(id)) {
                            movie.removeFromCast(actor);
                            fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                            break;
                        }
                    }
                case director:
                    for (Director director : movie.getDirector()) {
                        if (director.getId().equals(id)) {
                            movie.removeFromDirector(director);
                            fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                            break;
                        }
                    }
            }
        }
    }
}
