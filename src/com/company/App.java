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

    public App() {
        this.awards = (List<AcademyAward>) fileManager.load(awards, "database/awards/");
        this.genres = (List<Genre>) fileManager.load(genres, "database/genres/");
        this.formats = (List<Format>) fileManager.load(formats, "database/formats/");
        this.movies = (List<Movie>) fileManager.load(movies, "database/movies/");
        this.directors = (List<Director>) fileManager.load(directors, "database/directors/");
        this.actors = (List<Actor>) fileManager.load(actors, "database/actors/");
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