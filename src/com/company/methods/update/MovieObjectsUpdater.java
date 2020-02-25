package com.company.methods.update;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.objects.*;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MovieObjectsUpdater {
    private App app;
    private FileManager fileManager = new FileManager<>();
    private Scanner scan = new Scanner (System.in);
    private String firstName = "";
    private String lastName = "";
    private String name = "";
    private int counter = 0;
    private int choice = 0;

    public MovieObjectsUpdater(App app) {
        this.app = app;
    }

    public void updateActor(Object o) {
        chooseObject(Collections.unmodifiableList(app.getActors()));
        if (choice < counter) {
            inputCrewsName();
            app.getActors().get(choice).setFirstName(firstName);
            app.getActors().get(choice).setLastName(lastName);
            fileManager.writeToFile(App.ACTORFOLDER + app.getActors().get(choice).getId() + ".txt", app.getActors().get(choice));
            for (Movie movie : app.getMovies()) {
                for (Actor actor : movie.getCast()) {
                    if (actor.getId().equals(app.getActors().get(choice).getId())) {
                        movie.removeFromCast(actor);
                        movie.addToCast(app.getActors().get(choice));
                        fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                    }
                }
            }
        }
    }

    public void updateDirector(Object o) {
        chooseObject(Collections.unmodifiableList(app.getDirectors()));
        if (choice < counter) {
            inputCrewsName();
            app.getDirectors().get(choice).setFirstName(firstName);
            app.getDirectors().get(choice).setLastName(lastName);
            fileManager.writeToFile(App.DIRECTORFOLDER + app.getDirectors().get(choice).getId() + ".txt", app.getDirectors().get(choice));
            for (Movie movie : app.getMovies()) {
                for (Director director : movie.getDirector()) {
                    if (director.getId().equals(app.getDirectors().get(choice).getId())) {
                        movie.removeFromDirector(director);
                        movie.addToDirector(app.getDirectors().get(choice));
                        fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                    }
                }
            }
        }
    }

    public void updateGenre(Object o) {
        chooseObject(Collections.unmodifiableList(app.getGenres()));
        if (choice < counter) {
            inputNameOfObject();
            app.getGenres().get(choice).setName(name);
            fileManager.writeToFile(App.GENREFOLDER + app.getGenres().get(choice).getId() + ".txt", app.getGenres().get(choice));
            for (Movie movie : app.getMovies()) {
                for (Genre genre : movie.getGenre()) {
                    if (genre.getId().equals(app.getActors().get(choice).getId())) {
                        movie.removeFromGenre(genre);
                        movie.addToGenre(app.getGenres().get(choice));
                        fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                    }
                }
            }
        }
    }

    public void updateAward(Object o) {
        chooseObject(Collections.unmodifiableList(app.getAwards()));
        if (choice < counter) {
            inputNameOfObject();
            app.getAwards().get(choice).setName(name);
            fileManager.writeToFile(App.AWARDFOLDER + app.getAwards().get(choice).getId() + ".txt", app.getAwards().get(choice));
            updateAwardInMovie();
            updateAwardInActor();
            updateAwardInDirector();
        }
    }

    public void updateFormat(Object o) {
        chooseObject(Collections.unmodifiableList(app.getFormats()));
        if (choice < counter) {
            inputNameOfObject();
            app.getFormats().get(choice).setName(name);
            fileManager.writeToFile(App.FORMATFOLDER + app.getFormats().get(choice).getId() + ".txt", app.getFormats().get(choice));
            for (Movie movie : app.getMovies()) {
                if (movie.getFormat().getId().equals(app.getFormats().get(choice).getId())) {
                    movie.setFormat(app.getFormats().get(choice));
                    fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                }
            }
        }
    }

    public void updateTitleOfMovie(Object o) {
        chooseObject(Collections.unmodifiableList(app.getMovies()));
        if (choice < counter) {
            System.out.println("Titel:");
            String title = scan.nextLine();
            app.getMovies().get(choice).setTitle(title);
            fileManager.writeToFile(App.MOVIEFOLDER + app.getMovies().get(choice).getId() + ".txt", app.getMovies().get(choice));
        }
    }

    public void updateYearOfMovie(Object o) {
        chooseObject(Collections.unmodifiableList(app.getMovies()));
        if (choice < counter) {
            System.out.println("År:");
            String year = scan.nextLine();
            app.getMovies().get(choice).setYear(year);
            fileManager.writeToFile(App.MOVIEFOLDER + app.getMovies().get(choice).getId() + ".txt", app.getMovies().get(choice));
        }
    }

    public void updateLengthOfMovie(Object o) {
        chooseObject(Collections.unmodifiableList(app.getMovies()));
        if (choice < counter) {
            System.out.println("Filmens längd i minuter:");
            String length = scan.nextLine();
            app.getMovies().get(choice).setLengthMinutes(length);
            fileManager.writeToFile(App.MOVIEFOLDER + app.getMovies().get(choice).getId() + ".txt", app.getMovies().get(choice));
        }
    }

    private void chooseObject(List<BaseObject> list) {
        counter = fileManager.showListOfOptions(list);
        String input = scan.nextLine();
        choice = Integer.parseInt(input) - 1;
    }

    private void inputCrewsName() {
        System.out.println("Förnamn:");
        firstName = scan.nextLine();
        System.out.println("Efternamn:");
        lastName = scan.nextLine();
    }

    private void inputNameOfObject() {
        System.out.println("Namn:");
        name = scan.nextLine();
    }

    private void updateAwardInMovie() {
        for (Movie movie : app.getMovies()) {
            for (AcademyAward award : movie.getAwards()) {
                if (award.getId().equals(app.getAwards().get(choice).getId())) {
                    movie.removeFromAwards(award);
                    movie.addToAwards(app.getAwards().get(choice));
                    fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                }
            }
        }
    }

    private void updateAwardInActor() {
        for (Actor actor : app.getActors()) {
            for (AcademyAward award : actor.getAwards()) {
                if (award.getId().equals(app.getAwards().get(choice).getId())) {
                    actor.removeFromAwards(award);
                    actor.addToAwards(app.getAwards().get(choice));
                    fileManager.writeToFile(App.ACTORFOLDER + actor.getId() + ".txt", actor);
                }
            }
        }
    }

    private void updateAwardInDirector() {
        for (Director director : app.getDirectors()) {
            for (AcademyAward award : director.getAwards()) {
                if (award.getId().equals(app.getAwards().get(choice).getId())) {
                    director.removeFromAwards(award);
                    director.addToAwards(app.getAwards().get(choice));
                    fileManager.writeToFile(App.DIRECTORFOLDER + director.getId() + ".txt", director);
                }
            }
        }
    }
}
