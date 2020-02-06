package com.company.objects;

import com.company.dbmaker.BaseObject;

import java.util.ArrayList;

public class Actor extends BaseObject {
    private String firstName;
    private String lastName;
    private ArrayList<Movie> filmography = new ArrayList<>();

    public Actor(String firstName, String lastName, Movie newMovie) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmography = new ArrayList<>();
        this.filmography.add(newMovie);
    }




    public void addToFilmography(Movie newMovie) {
        filmography.add(newMovie);
    }

    public void removeFromFilmography(Movie movie) {
        filmography.remove(movie);
    }

    public ArrayList<Movie> getFilmography() {
        return this.filmography;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public String getWholeName() {
        return this.firstName + " " + this.lastName;
    }

    public String toString() {
        String movies = "";
        for (Movie movie : this.filmography) {
            movies = movies.concat(movie.getId() + " ");
        }
        return "Id: " +  getId() + "\nTilltalsnamn: " + this.firstName + "\nEfternamn: " + this.lastName +
                "\nFilmer: " + movies;

    }
}