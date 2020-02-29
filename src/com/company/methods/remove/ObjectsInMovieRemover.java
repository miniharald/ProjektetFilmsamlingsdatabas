package com.company.methods.remove;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.objects.Actor;
import com.company.objects.Movie;
import com.company.objects.MovieObjects;

import java.util.List;
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

    public void removeAwardFromMovie(Object o) {
        removeObjectsFromMovie(MovieObjects.Oscars);
    }

    public void removeGenreFromMovie(Object o) {
        removeObjectsFromMovie(MovieObjects.genre);
    }

    public void removeObjectsFromMovie(MovieObjects movieObject) {
        int goBack;
        if (app.getMovies().size() > 10) {
            System.out.println("Gör en sökning på den film du ska ta bort filmobjekt från:");
            String input = scan.nextLine();
            List<BaseObject> searchResult = fileManager.searchSpecific(input, app.getMovies());
            fileManager.showListOfOptions(searchResult);
            goBack = searchResult.size() + 1;
        } else {
            fileManager.showListOfOptions(app.getMovies());
            goBack = app.getMovies().size() + 1;
        }
        int movieChoice = getChoice(goBack);
        if (movieChoice < goBack) {
            showObjects(movieObject, movieChoice);
            goBack = app.getMovies().size() + 1;
            int choice = getChoice(goBack);
            if (choice < goBack) {
                removeObjectFromMovie(movieObject, movieChoice, choice);
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
        String input = "";
        if (app.getMovies().size() > 10) {
            System.out.println("Gör en sökning på det du ska ta bort filmobjekt från:");
            input = scan.nextLine();
        }
        switch (movieObjects) {
            case director:
                if (app.getMovies().size() > 10) {
                    List<BaseObject> searchResult = fileManager.searchSpecific(input, app.getMovies().get(movieChoice).getDirector());
                    fileManager.showListOfOptions(searchResult);
                } else {
                    fileManager.showListOfOptions(app.getMovies().get(movieChoice).getDirector());
                }
                break;
            case actor:
                if (app.getMovies().size() > 10) {
                    List<BaseObject> searchResult = fileManager.searchSpecific(input, app.getMovies().get(movieChoice).getCast());
                    fileManager.showListOfOptions(searchResult);
                } else {
                    fileManager.showListOfOptions(app.getMovies().get(movieChoice).getCast());
                }
                break;
            case Oscars:
                if (app.getMovies().size() > 10) {
                    List<BaseObject> searchResult = fileManager.searchSpecific(input, app.getMovies().get(movieChoice).getAwards());
                    fileManager.showListOfOptions(searchResult);
                } else {
                    fileManager.showListOfOptions(app.getMovies().get(movieChoice).getAwards());
                }
                break;
            case genre:
                if (app.getMovies().size() > 10) {
                    List<BaseObject> searchResult = fileManager.searchSpecific(input, app.getMovies().get(movieChoice).getGenre());
                    fileManager.showListOfOptions(searchResult);
                } else {
                    fileManager.showListOfOptions(app.getMovies().get(movieChoice).getGenre());
                }
                break;
        }
    }

    private void removeObjectFromMovie(MovieObjects movieObjects, int movieChoice, int choice) {
        switch (movieObjects) {
            case director:
                app.getMovies().get(movieChoice).removeFromDirector(app.getMovies().get(movieChoice).getDirector().get(choice));
                break;
            case actor:
                app.getMovies().get(movieChoice).removeFromCast(app.getMovies().get(movieChoice).getCast().get(choice));
                break;
            case Oscars:
                app.getMovies().get(movieChoice).removeFromAwards(app.getMovies().get(movieChoice).getAwards().get(choice));
                break;
            case genre:
                app.getMovies().get(movieChoice).removeFromGenre(app.getMovies().get(movieChoice).getGenre().get(choice));
                break;
        }
    }
}
