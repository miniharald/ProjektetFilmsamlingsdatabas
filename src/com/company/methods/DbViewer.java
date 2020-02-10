package com.company.methods;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.InputChecker;
import com.company.objects.Format;
import com.company.objects.Genre;
import com.company.objects.Movie;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Function;

public class DbViewer<D> {

    private Scanner scan = new Scanner(System.in);
    App app;
    ObjectLister objectLister;
    InputChecker checker;
    private boolean inputOk = false;
    private int counter;

    public DbViewer(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
        this.objectLister = new ObjectLister(app);
    }

    public void browseByMovies(Object o) {
        app.getMovies().sort(Comparator.comparing(Movie::getTitle));
        showListOfOptions(Collections.unmodifiableList(app.getMovies()));
        printMovieChoice();
    }

    public void browseByGenre(Object o) {
        app.getGenres().sort(Comparator.comparing(Genre::getName));
        showListOfOptions(Collections.unmodifiableList(app.getGenres()));
        printMovieChoiceFromOtherObject();
    }

    public void browseByFormat(Object o) {
        app.getFormats().sort(Comparator.comparing(Format::getName));
        showListOfOptions(Collections.unmodifiableList(app.getFormats()));
        printMovieChoiceFromOtherObject();
    }

    public void showListOfOptions(List<BaseObject> list) {
        counter = 1;
        for (BaseObject baseObject : list) {
            System.out.println("[" + counter + "] " + baseObject.listToString());
            counter++;
        }
    }

    public void printMovieChoice() {
        String input = scan.nextLine();
        int choice = Integer.parseInt(input) - 1;
        if (choice < counter) {
            System.out.println(app.getMovies().get(choice).toString());
        }
    }

    private void printMovieChoiceFromOtherObject() {
        String input = scan.nextLine();
        int choice = Integer.parseInt(input) - 1;
        if (choice < counter) {
            app.getMovies().sort(Comparator.comparing(Movie::getTitle));
            counter = 1;
            for (Movie movie : app.getMovies()) {
                for (Genre genre : movie.getGenre()) {
                    if (genre.getId().equals(app.getGenres().get(choice).getId())) {
                        System.out.println("[" + counter + "] " + movie.listToString());
                        counter++;
                    }
                }
            }
            printMovieChoice();
        }
    }
}
