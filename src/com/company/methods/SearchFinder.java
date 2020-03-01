package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.objects.Actor;
import com.company.objects.Director;
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

    public void searchForMovie(Object o) {
        System.out.println("Sök på titel, årtal, genre, Oscars, skådespelare eller regissör för att få fram film du söker efter:");
        String input = scan.nextLine();
        List<Movie> searchResult = (List<Movie>) fileManager.searchAll(input, app.getMovies());
        fileManager.showListOfOptions(searchResult);
    }

    public void searchForActor(Object o) {
        System.out.println("Sök på titel, årtal, genre, Oscars, skådespelare eller regissör för att få fram skådespelare du söker efter:");
        String input = scan.nextLine();
        List<Actor> searchResult = (List<Actor>) fileManager.searchAll(input, app.getActors());
        fileManager.showListOfOptions(searchResult);
    }

    public void searchForDirector(Object o) {
        System.out.println("Sök på titel, årtal, genre, Oscars, skådespelare eller regissör för att få fram regissör du du söker efter:");
        String input = scan.nextLine();
        List<Director> searchResult = (List<Director>) fileManager.searchAll(input, app.getDirectors());
        fileManager.showListOfOptions(searchResult);
    }
}
