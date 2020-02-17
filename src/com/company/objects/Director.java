package com.company.objects;

import com.company.dbmaker.BaseObject;

import java.util.ArrayList;
import java.util.List;

public class Director extends BaseObject {

    private String firstName;
    private String lastName;
    private List<AcademyAward> awards = new ArrayList<>();
    private List<Movie> filmography = new ArrayList<>();

    public Director(String firstName, String lastName, Movie newMovie) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.filmography = new ArrayList<>();
        this.filmography.add(newMovie);
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<AcademyAward> getAwards() {
        return awards;
    }

    public void setAwards(List<AcademyAward> awards) {
        this.awards = awards;
    }

    public List<Movie> getFilmography() {
        return filmography;
    }

    public void setFilmography(List<Movie> filmography) {
        this.filmography = filmography;
    }

    public void addToFilmography(Movie newMovie) {
        filmography.add(newMovie);
    }

    public void removeFromFilmography(Movie movie) {
        filmography.remove(movie);
    }

    public void addToAwards(AcademyAward newAward) {
        awards.add(newAward);
    }

    public void removeFromAwards(AcademyAward newAward) {
        awards.remove(newAward);
    }

    public String getWholeName() {
        return this.firstName + " " + this.lastName;
    }

    public String getPrimary() {
        return this.firstName + " " + this.lastName;
    }

    public String listToString() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}