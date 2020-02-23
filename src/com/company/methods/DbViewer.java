package com.company.methods;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.objects.*;

import java.util.*;

public class DbViewer {

    private Scanner scan = new Scanner(System.in);
    App app;
    InputChecker checker;
    private int counter;
    private List<Movie> newMovieList = new ArrayList<>();
    private FileManager fileManager = new FileManager();

    public DbViewer(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
    }

    public void browseByMovies(Object o) {
        newMovieList.clear();
        app.getMovies().sort(Comparator.comparing(Movie::getTitle));
        counter = fileManager.showListOfOptions(Collections.unmodifiableList(app.getMovies()));
        printMovieChoice(true);
    }

    public void browseByGenre(Object o) {
        newMovieList.clear();
        app.getGenres().sort(Comparator.comparing(Genre::getName));
        counter = fileManager.showListOfOptions(Collections.unmodifiableList(app.getGenres()));
        printMovieChoiceFromOtherObject("Genre");
    }

    public void browseByFormat(Object o) {
        newMovieList.clear();
        app.getFormats().sort(Comparator.comparing(Format::getName));
        counter = fileManager.showListOfOptions(Collections.unmodifiableList(app.getFormats()));
        String input = scan.nextLine();
        int choice = Integer.parseInt(input) - 1;
        if (choice < counter) {
            Format chosenFormat = app.getFormats().get(choice);
            app.getMovies().sort(Comparator.comparing(Movie::getTitle));
            counter = 1;
            for (Movie movie : app.getMovies()) {
                printFormatIfMatch(chosenFormat, movie);
            }
            printMovieChoice(false);
        }
    }

    public void browseByDirector(Object o) {
        newMovieList.clear();
        app.getDirectors().sort(Comparator.comparing(Director::getLastName));
        counter = fileManager.showListOfOptions(Collections.unmodifiableList(app.getDirectors()));
        printMovieChoiceFromOtherObject("Director");
    }

    public void browseByActor(Object o) {
        newMovieList.clear();
        app.getActors().sort(Comparator.comparing(Actor::getLastName));
        counter = fileManager.showListOfOptions(Collections.unmodifiableList(app.getActors()));
        printMovieChoiceFromOtherObject("Actor");
    }

    public void printMovieChoice(boolean isMovie) {
        String input = scan.nextLine();
        int choice = Integer.parseInt(input) - 1;
        if (choice < counter) {
            if (isMovie) {
                System.out.println(app.getMovies().get(choice).toString());
            } else {
                System.out.println(newMovieList.get(choice).toString());
            }
        }
    }

    private void printMovieChoiceFromOtherObject(String object) {
        String input = scan.nextLine();
        int choice = Integer.parseInt(input) - 1;
        if (choice < counter) {
            app.getMovies().sort(Comparator.comparing(Movie::getTitle));
            counter = 1;
            for (Movie movie : app.getMovies()) {
                printIfMatch(object, movie, choice);
            }
            printMovieChoice(false);
        }
    }

    private void printFormatIfMatch(Format chosenFormat, Movie movie) {
        app.getFormats().sort(Comparator.comparing(Format::getPrimary));
        if (movie.getFormat().getId().equals(chosenFormat.getId())) {
            newMovieList.add(movie);
            System.out.println(counter + ".) " + movie.listToString());
            counter++;
        }
    }

    private void printIfMatch(String object, Movie movie, int choice) {
        if (object.equals("Genre")) {
            app.getGenres().sort(Comparator.comparing(Genre::getPrimary));
            for (Genre genre : movie.getGenre()) {
                if (genre.getId().equals(app.getGenres().get(choice).getId())) {
                    newMovieList.add(movie);
                    System.out.println(counter + ".) " + movie.listToString());
                    counter++;
                }
            }
        }
        if (object.equals("Director")) {
            app.getDirectors().sort(Comparator.comparing(Director::getLastName));
            for (Director director: movie.getDirector()) {
                printCrewIfMatch(Collections.unmodifiableList(app.getDirectors()), choice, director, movie);
            }
        }
        if (object.equals("Actor")) {
            app.getActors().sort(Comparator.comparing(Actor::getLastName));
            for (Actor actor: movie.getCast()) {
                printCrewIfMatch(Collections.unmodifiableList(app.getActors()), choice, actor, movie);
            }
        }
    }

    private void printCrewIfMatch(List<BaseObject> list, int choice, BaseObject baseObject, Movie movie) {
            if (baseObject.getId().equals(list.get(choice).getId())) {
                newMovieList.add(movie);
                System.out.println(counter + ".) " + movie.listToString());
                counter++;
            }
    }
}
