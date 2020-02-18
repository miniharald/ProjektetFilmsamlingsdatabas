package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.objects.Actor;
import com.company.objects.Movie;
import com.company.objects.MovieObjects;

import java.util.Scanner;

public class ObjectsInMovieRemover {

    App app;
    FileManager fileManager = new FileManager();
    Scanner scan = new Scanner(System.in);

    public ObjectsInMovieRemover(App app) {
        this.app = app;
    }

    public void removeDirectorFromMovie(Object o) {
        removeObjectsFromMovie(MovieObjects.director);
    }

    public void removeActorFromMovie(Object o) {
        removeObjectsFromMovie(MovieObjects.actor);
    }

    public void removeObjectsFromMovie(MovieObjects movieObjects) {
        fileManager.showListOfOptions(app.getMovies());
        int goBack = app.getMovies().size() + 1;
        int movieChoice = getChoice(goBack) - 1;
        if (movieChoice < goBack) {
            showObjects(movieObjects, movieChoice);
            goBack = app.getMovies().size() + 1;
            int choice = getChoice(goBack) - 1;
            if (choice < goBack) {
                removeObjectFromMovie(movieObjects, movieChoice, choice);
                fileManager.writeToFile(App.MOVIEFOLDER + app.getMovies().get(movieChoice).getId() + ".txt", app.getMovies().get(movieChoice));
            } else if (choice == goBack) {
                return;
            }
        } else if (movieChoice == goBack) {
            return;
        }
    }

    private int getChoice(int goBack) {
        System.out.println(goBack + ".) Gå tillbaka");
        System.out.println("Välj ett alternativ ovan!");
        String inputChoice = scan.nextLine();
        int choice = Integer.parseInt(inputChoice) - 1;
        return choice;
    }

    private void showObjects(MovieObjects movieObjects, int movieChoice) {
        switch (movieObjects) {
            case director:
                fileManager.showListOfOptions(app.getMovies().get(movieChoice).getDirector());
            case actor:
                fileManager.showListOfOptions(app.getMovies().get(movieChoice).getCast());
        }
    }

    private void removeObjectFromMovie(MovieObjects movieObjects, int movieChoice, int choice) {
        switch (movieObjects) {
            case director:
                app.getMovies().get(movieChoice).removeFromDirector(app.getMovies().get(movieChoice).getDirector().get(choice));
            case actor:
                app.getMovies().get(movieChoice).removeFromCast(app.getMovies().get(movieChoice).getCast().get(choice));
        }
    }
}
