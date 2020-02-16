package com.company;

import com.company.dbmaker.FileManager;

import com.company.menu.Menu;
import com.company.objects.*;
import java.util.ArrayList;
import java.util.List;

public class App {

    private List<AcademyAward> awards = new ArrayList<>();
    private List<Movie> movies = new ArrayList<>();
    private List<Director> directors = new ArrayList<>();
    private List<Actor> actors = new ArrayList<>();
    private List<Genre> genres = new ArrayList<>();
    private List<Format> formats = new ArrayList<>();
    private FileManager fileManager = new FileManager();
    public static final String DIRECTORFOLDER = "database/directors/";
    public static final String ACTORFOLDER = "database/actors/";
    public static final String MOVIEFOLDER = "database/movies/";
    public static final String GENREFOLDER = "database/genres/";
    public static final String AWARDFOLDER = "database/awards/";
    public static final String FORMATFOLDER = "database/formats/";

    public App() {
        this.awards = (List<AcademyAward>) fileManager.load(awards, AWARDFOLDER);
        this.genres = (List<Genre>) fileManager.load(genres, GENREFOLDER);
        this.formats = (List<Format>) fileManager.load(formats, FORMATFOLDER);
        this.movies = (List<Movie>) fileManager.load(movies, MOVIEFOLDER);
        this.directors = (List<Director>) fileManager.load(directors, DIRECTORFOLDER);
        this.actors = (List<Actor>) fileManager.load(actors, ACTORFOLDER);
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
}