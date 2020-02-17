package com.company.methods.update;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.objects.Actor;
import com.company.objects.Director;
import com.company.objects.Genre;
import com.company.objects.Movie;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MovieObjectsUpdater {
    App app;
    FileManager fileManager = new FileManager<>();
    Scanner scan = new Scanner (System.in);
    String firstName = "";
    String lastName = "";
    String name = "";
    int counter = 0;
    int choice = 0;

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
                for (Actor actor : movie.getCast())
                    if (actor.getId().equals(app.getActors().get(choice).getId())) {
                        movie.removeFromCast(actor);
                        movie.addToCast(app.getActors().get(choice));
                        fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
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
                for (Director director : movie.getDirector())
                    if (director.getId().equals(app.getDirectors().get(choice).getId())) {
                        movie.removeFromDirector(director);
                        movie.addToDirector(app.getDirectors().get(choice));
                        fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
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
                for (Genre genre : movie.getGenre())
                    if (genre.getId().equals(app.getActors().get(choice).getId())) {
                        movie.removeFromGenre(genre);
                        movie.addToGenre(app.getGenres().get(choice));
                        fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                    }
            }
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

    public void chooseObject(List<BaseObject> list) {
        counter = fileManager.showListOfOptions(list);
        String input = scan.nextLine();
        choice = Integer.parseInt(input) - 1;
    }

    public void inputCrewsName() {
        System.out.println("Förnamn:");
        firstName = scan.nextLine();
        System.out.println("Efternamn:");
        lastName = scan.nextLine();
    }

    public void inputNameOfObject() {
        System.out.println("Namn:");
        name = scan.nextLine();
    }
}
