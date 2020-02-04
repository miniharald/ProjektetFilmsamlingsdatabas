package com.company;

import java.io.File;
import java.util.Scanner;

public class AddMovie {

    private Scanner scan = new Scanner(System.in);
    private App app;
    private Check checker;
    private AddCrewToMovie addCrew;
    private AddGenreAndAwardsToMovie addGenreAndAwardsToMovie;
    private FileManager fileManager = new FileManager();
    private boolean inputOk = false;
    private boolean isDuplicate;
    private String fileName;
    private String title;
    private String actorId = null;
    private String genreId = null;
    private String directorId = null;
    private String year;
    private String format;
    private Movie movieObj = new Movie();

    public AddMovie(App app) {
        this.app = app;
        this.checker = new Check(app);
        this.addCrew = new AddCrewToMovie(app);
        this.addGenreAndAwardsToMovie = new AddGenreAndAwardsToMovie(app);
    }

    public void run(Object o) {
        fileName = fileName();
        title = title();
        year = year();
        format(fileName, title, year);
        addGenreAndAwardsToMovie.addGenre();
        addMore("genre");
        addCrew.addDirector();
        addMore("regissör");
        addCrew.addActor();
        addMore("skådespelare");
        app.getMovies().get(app.getMovies().size() - 1).writeToFile(("database/movies/" + fileName), (app.getMovies().get(app.getMovies().size() - 1).toString()));
        Movie movie = app.getMovies().get(app.getMovies().size() - 1);
        movie.writeToFile(movie.getId(), movie);
        System.out.println("Filmen är inlagd");
    }

    private String fileName() {
        do {
            fileName = movieObj.idGenerator();
            File folderPath = new File("database/movies/");
            isDuplicate = fileManager.checkForDuplicateFileNames(folderPath, fileName);
        } while (isDuplicate);
        return fileName;
    }

    private String title() {
        System.out.println("Titel: ");
        title = scan.nextLine();
        return title;
    }

    private String year() {
        boolean yearCheck;
        do {
            System.out.println("Årtal: ");
            year = scan.nextLine();
            inputOk = checker.checkIfStringOfNumbers(year);
            if (year.length() != 4 || year.isBlank()) {
                System.out.println("The publishing date for the book must be 4 digits!");
                yearCheck = false;
            } else {
                yearCheck = true;
            }
        } while (!yearCheck);
        return year;
    }

    private void addMore(String choice) {
        boolean addMore = true;
        String input;
        do {
            System.out.printf("Tryck 1 för att lägga till en till %s, tryck annars valfri siffra eller bokstav!", choice);
            input = scan.nextLine();
            if(input.equals("1") && choice.equals("regissör")) {
                addCrew.addDirector();
            }
            else if(input.equals("1") && choice.equals("genre")) {
                addGenreAndAwardsToMovie.addGenre();
            }
            else if(input.equals("1") && choice.equals("skådespelare")) {
                addCrew.addActor();
            } else {
                addMore = false;
            }
        }while (addMore);
    }
}
