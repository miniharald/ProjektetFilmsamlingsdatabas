package com.company;

import com.company.dbmaker.FileManager;
import com.company.methods.addmovie.MovieAdder;
import com.company.objects.*;

import java.util.List;

public class App {

    private List<AcademyAward> awards;
    private List<Movie> movies;
    private List<Director> directors;
    private List<Actor> actors;
    private List<Genre> genres;
    private List<Format> formats;
    private FileManager fileManager = new FileManager();

    public App() {
        this.awards = (List<AcademyAward>) fileManager.load(awards, "database/awards/");
        this.genres = (List<Genre>) fileManager.load(genres, "database/genres/");
        this.formats = (List<Format>) fileManager.load(formats, "database/formats/");
        this.movies = (List<Movie>) fileManager.load(movies, "database/movies/");
        this.directors = (List<Director>) fileManager.load(directors, "database/directors/");
        this.actors = (List<Actor>) fileManager.load(actors, "database/actors/");
        MovieAdder test = new MovieAdder(this);
        test.run();
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