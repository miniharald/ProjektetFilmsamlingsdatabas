package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.FileManager;
import com.company.objects.Director;
import com.company.objects.MovieObjects;

import java.util.Collections;

public class NewObjectAdder {

    private GenreAndAwardAdder genreAndAwardAdder;
    private CrewAdder crewAdder;
    private MovieAdder movieAdder;
    private App app;

    public NewObjectAdder(App app) {
        this.app = app;
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

    public void addNewActor(Object o) {
        crewAdder.addNewCrew(Collections.unmodifiableList(app.getActors()), App.ACTORFOLDER, MovieObjects.actor);
    }

    public void addNewDirector(Object o) {
        crewAdder.addNewCrew(Collections.unmodifiableList(app.getDirectors()), App.DIRECTORFOLDER, MovieObjects.director);
    }
}
