package com.company.objects;

import com.company.dbmaker.BaseObject;

import java.util.ArrayList;
import java.util.List;

public class Director extends BaseObject {

    private String firstName;
    private String lastName;
    private List<AcademyAward> awards;
    private List<Movie> filmography;

    public Director(String firstName, String lastName, Movie newMovie) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.awards = new ArrayList<>();
        this.filmography = new ArrayList<>();
        this.filmography.add(newMovie);
    }

    public Director(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.awards = new ArrayList<>();
        this.filmography = new ArrayList<>();
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

    public List<Movie> getFilmography() {
        return filmography;
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

    @Override
    public String getKeyWords() {
        StringBuilder info = new StringBuilder(this.firstName + " " + lastName);
        for (AcademyAward award : this.awards) {
            info.append(" ").append(award.getName());
        }
        for (Movie movie : this.filmography) {
            info.append(movie.getKeyWords());
        }
        return info.toString();
    }

    public String listToString() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}