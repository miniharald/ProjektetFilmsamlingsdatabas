package com.company;

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
