package com.company.methods;

import com.company.App;
import com.company.dbmaker.FileManager;

public class ObjectAdder {

    private App app;
    private FileManager fileManager = new FileManager();
    private GenreAndAwardAdder genreAndAwardAdder;
    String input;

    public ObjectAdder(App app) {
        this.app = app;
        this.genreAndAwardAdder = new GenreAndAwardAdder(app);
    }

    public void addNewGenre(Object o) {
        genreAndAwardAdder.addNewGenre();
    }

    public void addNewAward(Object o) {
        genreAndAwardAdder.addNewAward();
    }
}
