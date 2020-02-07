package com.company.methods.addmovie;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.dbmaker.InputChecker;
import com.company.objects.Format;
import com.company.objects.Movie;

import java.util.Scanner;

public class MovieAdder {

    private Scanner scan = new Scanner(System.in);
    private App app;
    private InputChecker checker;
    private CrewToMovieAdder addCrew;
    private GenreAndAwardToMovieAdder addGenreAndAwardsToMovie;
    private FileManager fileManager = new FileManager();
    private boolean inputOk = false;
    private String fileName;
    private String title;
    private String year;
    private String format;
    private String lengthMinutes;

    public MovieAdder(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
        this.addCrew = new CrewToMovieAdder(app);
        this.addGenreAndAwardsToMovie = new GenreAndAwardToMovieAdder(app);
    }

    public void run() {
        title = title();
        year = year();
        lengthMinutes = lengthMinutes();
        format = format();
        app.getMovies().add(new Movie(title, year, format, lengthMinutes));
        addGenreAndAwardsToMovie.addGenre();
        addMore("genre");
        addGenreAndAwardsToMovie.areYouAddingAward();
        addMore("Oscar");
        addCrew.addDirector();
        addMore("regissör");
        addCrew.addActor();
        addMore("skådespelare");
        Movie movie = app.getMovies().get(app.getMovies().size() - 1);
        fileName = app.getMovies().get(app.getMovies().size() - 1).getId();
        fileManager.writeToFile("database/movies/" + fileName + ".txt", movie);
        System.out.println("Filmen är inlagd");
    }

    /*private String fileName() {
        do {
            fileName = movieObj.getId();
            File folderPath = new File("database/movies/");
            isDuplicate = fileManager.checkForDuplicateFileNames(folderPath, fileName);
        } while (isDuplicate);
        return fileName;
    }*/

    private String title() {
        System.out.println("Titel: ");
        title = scan.nextLine();
        return title;
    }

    private String year() {
        do {
            System.out.println("Årtal: ");
            year = scan.nextLine();
            inputOk = checker.checkIfStringOfNumbers(year);
            if (year.length() != 4 || year.isBlank()) {
                System.out.println("Årtalet måste innehålla 4 siffror!");
                inputOk = false;
            }
        } while (!inputOk);
        return year;
    }

    private String lengthMinutes() {
        do {
            System.out.println("Filmens längd: ");
            year = scan.nextLine();
            inputOk = checker.checkIfStringOfNumbers(year);
            if (year.length() < 2 || year.isBlank()) {
                System.out.println("Filmen måste vara minst 10 minuter lång!");
                inputOk = false;
            }
        } while (!inputOk);
        return year;
    }

    private String format() {
        do {
            int formatCounter = 1;
            for (Format formatObj : app.getFormats()) {
                System.out.println("[" + formatCounter + "]" + " " + formatObj.getName());
                formatCounter++;
            }
            int newFormat = app.getFormats().size() + 1;
            System.out.println("[" + newFormat + "]" + " " + "Lägg till nytt format");
            System.out.println("Välj ett alternativ ovan!");
            String formatChoice = scan.nextLine();
            int choice = Integer.parseInt(formatChoice);
            if (choice < formatCounter) {
                format = addExistingFormat(choice);
                inputOk = true;
            } else if (choice == formatCounter) {
                format = addNewFormat();
                inputOk = true;
            }
        } while (!inputOk);
        return format;
    }

    private String addExistingFormat(int choice) {
        for (int i = 0; i < app.getFormats().size(); i++) {
            if (choice - 1 == i) {
                format = app.getFormats().get(i).getId();
            }
        }
        return format;
    }

    private String addNewFormat() {
        do {
            System.out.println("Nytt Format:");
            format = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(format);
            if (format.length() < 1 || format.isBlank()) {
                System.out.println("Formatet måste innehålla minst en bokstav!");
                inputOk = false;
            } else {
                app.getFormats().add(new Format(format));
                Format formatObj = app.getFormats().get(app.getFormats().size() - 1);
                format = app.getFormats().get(app.getFormats().size() - 1).getId();
                fileManager.writeToFile("database/formats/" + formatObj.getId() + ".txt", formatObj);
            }
        } while (!inputOk);
        return format;
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
            }
            else if(input.equals("1") && choice.equals("Oscar")) {
                addGenreAndAwardsToMovie.addAward();
            } else {
                addMore = false;
            }
        }while (addMore);
    }
}