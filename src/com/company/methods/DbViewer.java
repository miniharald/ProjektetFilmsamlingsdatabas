package com.company.methods;

import com.company.App;
import com.company.dbmaker.InputChecker;
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

    public void listMovies(Object o) {
        int counter = 1;
        boolean isInt = true;
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
}
