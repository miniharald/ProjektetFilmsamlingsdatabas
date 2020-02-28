package com.company;

import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;

import com.company.menu.Menu;
import com.company.methods.DbViewer;
import com.company.methods.SearchFinder;
import com.company.methods.add.CrewsAwardAdder;
import com.company.methods.add.MovieAdder;
import com.company.methods.add.NewObjectAdderFacade;
import com.company.methods.remove.CrewsAwardRemover;
import com.company.methods.remove.ObjectRemover;
import com.company.methods.remove.ObjectsInMovieRemover;
import com.company.methods.update.MovieObjectsUpdater;
import com.company.methods.update.MovieUpdater;
import com.company.objects.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class App {

    private List<AcademyAward> awards = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<Director> directors = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private List<Format> formats = new ArrayList<>();
    private MovieAdder movieAdder;
    private NewObjectAdderFacade newObjectAdderFacade;
    private DbViewer dbViewer;
    private MovieUpdater movieUpdater;
    private MovieObjectsUpdater movieObjectsUpdater;
    private ObjectRemover objectRemover;
    private ObjectsInMovieRemover objectsInMovieRemover;
    private SearchFinder searchFinder;
    private CrewsAwardRemover crewsAwardRemover;
    private CrewsAwardAdder crewsAwardAdder;
    public static final String DIRECTORFOLDER = "database/directors/";
    public static final String ACTORFOLDER = "database/actors/";
    public static final String MOVIEFOLDER = "database/movies/";
    public static final String GENREFOLDER = "database/genres/";
    public static final String AWARDFOLDER = "database/awards/";
    public static final String FORMATFOLDER = "database/formats/";

    public App() {
        FileManager fileManager = new FileManager();
        this.awards = (List<AcademyAward>) fileManager.load(awards, AWARDFOLDER);
        this.genres = (List<Genre>) fileManager.load(genres, GENREFOLDER);
        this.formats = (List<Format>) fileManager.load(formats, FORMATFOLDER);
        this.movies = (List<Movie>) fileManager.load(movies, MOVIEFOLDER);
        this.directors = (List<Director>) fileManager.load(directors, DIRECTORFOLDER);
        this.actors = (List<Actor>) fileManager.load(actors, ACTORFOLDER);
        movieAdder = new MovieAdder(this);
        dbViewer = new DbViewer(this);
        newObjectAdderFacade = new NewObjectAdderFacade(this);
        movieUpdater = new MovieUpdater(this);
        movieObjectsUpdater = new MovieObjectsUpdater(this);
        objectRemover = new ObjectRemover(this);
        objectsInMovieRemover = new ObjectsInMovieRemover(this);
        searchFinder = new SearchFinder(this);
        crewsAwardRemover = new CrewsAwardRemover(this);
        crewsAwardAdder = new CrewsAwardAdder(this);
        Menu menu = new Menu(this);
        menu.handleMenu();
    }

    public List<AcademyAward> getAwards() {
        return awards;
    }

    public List<Movie> getMovies() {
        return movies;
    }

    public List<Director> getDirectors() {
        return directors;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public List<Format> getFormats() {
        return formats;
    }

    public MovieAdder getMovieAdder() {
        return movieAdder;
    }

    public NewObjectAdderFacade getNewObjectAdderFacade() {
        return newObjectAdderFacade;
    }

    public DbViewer getDbViewer() {
        return dbViewer;
    }

    public MovieUpdater getMovieUpdater() {
        return movieUpdater;
    }

    public MovieObjectsUpdater getMovieObjectsUpdater() {
        return movieObjectsUpdater;
    }

    public ObjectRemover getObjectRemover() {
        return objectRemover;
    }

    public ObjectsInMovieRemover getObjectsInMovieRemover() {
        return objectsInMovieRemover;
    }

    public SearchFinder getSearchFinder() {
        return searchFinder;
    }

    public CrewsAwardRemover getCrewsAwardRemover() {
        return crewsAwardRemover;
    }

    public CrewsAwardAdder getCrewsAwardAdder() {
        return crewsAwardAdder;
    }
}