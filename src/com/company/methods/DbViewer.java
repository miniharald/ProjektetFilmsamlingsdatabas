package com.company.methods;

import com.company.App;
import com.company.dbmaker.InputChecker;
import com.company.objects.Format;
import com.company.objects.Genre;
import com.company.objects.Movie;

import java.util.Comparator;
import java.util.Scanner;

public class DbViewer {

    private Scanner scan = new Scanner(System.in);
    App app;
    ObjectLister objectLister;
    InputChecker checker;
    private boolean inputOk = false;

    public DbViewer(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
        this.objectLister = new ObjectLister(app);
    }

    public void browseByMovies(Object o) {
        int counter = 1;
        app.getMovies().sort(Comparator.comparing(Movie::getTitle));
        for (Movie movie : app.getMovies()) {
            System.out.println("[" + counter + "] " + movie.listToString());
            counter++;
            }
        String input = scan.nextLine();
        int choice = Integer.parseInt(input) - 1;
        if (choice < counter) {
            System.out.println(app.getMovies().get(choice).toString());
        }
    }

    public void browseByGenre(Object o) {
        int counter = 1;
        app.getGenres().sort(Comparator.comparing(Genre::getName));
        for (Genre genre : app.getGenres()) {
            System.out.println("[" + counter + "] " + genre.getName());
            counter++;
        }
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
            input = scan.nextLine();
            choice = Integer.parseInt(input) - 1;
            if (choice < counter) {
                System.out.println(app.getMovies().get(choice).toString());
            }
        }
    }

    public void browseByFormat(Object o) {
        int counter = 1;
        app.getFormats().sort(Comparator.comparing(Format::getName));
        for (Format format : app.getFormats()) {
            System.out.println("[" + counter + "] " + format.getName());
            counter++;
        }
        String input = scan.nextLine();
        int choice = Integer.parseInt(input) - 1;
        if (choice < counter) {
            app.getMovies().sort(Comparator.comparing(Movie::getTitle));
            counter = 1;
            for (Movie movie : app.getMovies()) {
                    if (movie.getFormat().getId().equals(app.getFormats().get(choice).getId())) {
                        System.out.println("[" + counter + "] " + movie.listToString());
                        counter++;
                    }
            }
            input = scan.nextLine();
            choice = Integer.parseInt(input) - 1;
            if (choice < counter) {
                System.out.println(app.getMovies().get(choice).toString());
            }
        }
    }
}
