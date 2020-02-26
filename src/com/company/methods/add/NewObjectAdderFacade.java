package com.company.methods.add;

import com.company.App;
import com.company.objects.MovieObjects;

import java.util.Collections;

public class NewObjectAdderFacade {

    private GenreAndAwardAdder genreAndAwardAdder;
    private CrewAdder crewAdder;
    private MovieAdder movieAdder;
    private App app;

    public NewObjectAdderFacade(App app) {
        this.app = app;
        this.genreAndAwardAdder = new GenreAndAwardAdder(app);
        this.movieAdder = new MovieAdder(app);
        this.crewAdder = new CrewAdder(app);
    }

    public void addNewGenre(Object o) {
        genreAndAwardAdder.addNewMovieObject(Collections.unmodifiableList(app.getGenres()), App.GENREFOLDER, MovieObjects.genre, "genre");
    }

    public void addNewAward(Object o) {
        genreAndAwardAdder.addNewMovieObject(Collections.unmodifiableList(app.getAwards()), App.AWARDFOLDER, MovieObjects.Oscars, "Oscars");
    }

    public void addNewFormat(Object o) {
        genreAndAwardAdder.addNewMovieObject(Collections.unmodifiableList(app.getFormats()), App.FORMATFOLDER, MovieObjects.format, "format");
    }

    public void addNewActor(Object o) {
        crewAdder.addNewCrew(Collections.unmodifiableList(app.getActors()), App.ACTORFOLDER, MovieObjects.actor);
    }

    public void addNewDirector(Object o) {
        crewAdder.addNewCrew(Collections.unmodifiableList(app.getDirectors()), App.DIRECTORFOLDER, MovieObjects.director);
    }
}
