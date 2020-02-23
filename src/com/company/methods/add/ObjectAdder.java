package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.FileManager;

public class ObjectAdder {

    private GenreAndAwardAdder genreAndAwardAdder;
    private CrewAdder crewAdder;
    private MovieAdder movieAdder;

    public ObjectAdder(App app) {
        this.genreAndAwardAdder = new GenreAndAwardAdder(app);
        this.movieAdder = new MovieAdder(app);
        this.crewAdder = new CrewAdder(app);
    }

    public void addNewGenre(Object o) {
        genreAndAwardAdder.addNewGenre();
    }

    public void addNewAward(Object o) {
        genreAndAwardAdder.addNewAward();
    }

    public void addNewFormat(Object o) {
        movieAdder.addNewFormat();
    }

    public void addNewActor(Object o) { crewAdder.addNewActor(); }

    public void addNewDirector(Object o) { crewAdder.addNewDirector(); }
}
