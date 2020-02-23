package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.objects.Movie;

import java.util.List;
import java.util.Scanner;

public class SearchFinder {

    private FileManager fileManager = new FileManager();
    private App app;
    private Scanner scan = new Scanner(System.in);

    public SearchFinder(App app) {
        this.app = app;
    }

    public void search(Object o) {
        String input = scan.nextLine();
        List<Movie> searchResult = (List<Movie>) fileManager.search(input, app.getMovies());
        fileManager.showListOfOptions(searchResult);
    }
}
