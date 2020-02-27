package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.objects.Format;
import com.company.objects.Movie;
import com.company.objects.MovieObjects;

import java.util.Collections;
import java.util.Scanner;

public class MovieAdder {

    private Scanner scan = new Scanner(System.in);
    private App app;
    private InputChecker checker;
    private CrewAdder addCrew;
    private MovieObjectsAdder movieObjectsAdder;
    private FileManager fileManager = new FileManager();
    private boolean inputOk = false;
    private String title;
    private String year;
    private String lengthMinutes;

    public MovieAdder(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
        this.addCrew = new CrewAdder(app);
        this.movieObjectsAdder = new MovieObjectsAdder(app);
    }

    public void run(Object o) {
        title = getTitle();
        year = getYear();
        lengthMinutes = getLengthMinutes();
        app.getMovies().add(new Movie(title, year, lengthMinutes));
        movieObjectsAdder.addMovieObjectToMovie(Collections.unmodifiableList(app.getFormats()), App.FORMATFOLDER, MovieObjects.format, "format");
        movieObjectsAdder.addMovieObjectToMovie(Collections.unmodifiableList(app.getGenres()), App.GENREFOLDER, MovieObjects.genre, "genre");
        addMore("genre");
        movieObjectsAdder.askToAddAwardToMovie();
        addCrew.addCrewToMovie(Collections.unmodifiableList(app.getDirectors()), App.DIRECTORFOLDER, MovieObjects.director, "regissör");
        addMore("regissör");
        addCrew.addCrewToMovie(Collections.unmodifiableList(app.getActors()), App.ACTORFOLDER, MovieObjects.actor, "skådespelare");
        addMore("skådespelare");
        Movie movie = app.getMovies().get(app.getMovies().size() - 1);
        String fileName = app.getMovies().get(app.getMovies().size() - 1).getId();
        fileManager.writeToFile(App.MOVIEFOLDER + fileName + ".txt", movie);
        System.out.println("Filmen är inlagd");
    }

    private String getTitle() {
        System.out.println("Titel: ");
        title = scan.nextLine();
        return title;
    }

    private String getYear() {
        do {
            System.out.println("Årtal: ");
            year = scan.nextLine();
            inputOk = checker.checkIfStringOfNumbers(year);
            //;
            //int yearGivenOut = Integer.parseInt(year);
            /*if (year.length() != 4 || year.isBlank()) {
                System.out.println("Årtalet måste innehålla 4 siffror!");
                inputOk = false;
            }*/
        } while (!inputOk);
        return year;
    }

    private String getLengthMinutes() {
        do {
            System.out.println("Filmens längd: ");
            lengthMinutes = scan.nextLine();
            inputOk = checker.checkIfStringOfNumbers(lengthMinutes);
            if (lengthMinutes.length() < 2 || lengthMinutes.isBlank()) {
                System.out.println("Filmen måste vara minst 10 minuter lång!");
                inputOk = false;
            }
        } while (!inputOk);
        return lengthMinutes;
    }

    void addMore(String choice) {
        boolean addMore = true;
        String input;
        do {
            System.out.printf("Tryck 1 för att lägga till en till %s, tryck annars valfri siffra eller bokstav!", choice);
            input = scan.nextLine();
            if(input.equals("1") && choice.equals("regissör")) {
                addCrew.addCrewToMovie(Collections.unmodifiableList(app.getDirectors()), App.DIRECTORFOLDER, MovieObjects.director, "regissör");
            }
            else if(input.equals("1") && choice.equals("genre")) {
                movieObjectsAdder.addMovieObjectToMovie(Collections.unmodifiableList(app.getGenres()), App.GENREFOLDER, MovieObjects.genre, "genre");
            }
            else if(input.equals("1") && choice.equals("skådespelare")) {
                addCrew.addCrewToMovie(Collections.unmodifiableList(app.getActors()), App.ACTORFOLDER, MovieObjects.actor, "skådespelare");
            }
            else if(input.equals("1") && choice.equals("Oscar")) {
                movieObjectsAdder.addNewMovieObjectToMovie(Collections.unmodifiableList(app.getAwards()), App.AWARDFOLDER, MovieObjects.Oscars, "Oscars");
            } else {
                addMore = false;
            }
        }while (addMore);
    }
}