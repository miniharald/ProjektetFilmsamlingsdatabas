package com.company;

import java.util.Scanner;

public class AddMovie {

    private Scanner scan = new Scanner(System.in);
    private App app;
    private Check checker;
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
    }
}
