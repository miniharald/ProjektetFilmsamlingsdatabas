package com.company.objects;

import com.company.dbmaker.BaseObject;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

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
    public String getSearchTerms() {
        StringBuilder info = new StringBuilder(this.firstName + " " + lastName);
        for (AcademyAward award : this.awards) {
            info.append(" ").append(award.getName());
        }
        for (Movie movie : this.filmography) {
            info.append(movie.getSearchTerms());
        }
        return info.toString();
    }

    public String ToStringForList() {
        return this.firstName + " " + this.lastName;
    }

    @Override
    public String toString() {
        if (awards.size() < 1) {
            return printIfAwardIsEmpty();
        } else {
            return printIfAwardIsNotEmpty();
        }
    }

    private String printIfAwardIsEmpty() {
        return String.format("%s %s\nOscars: %s\nFilmer:\n%s", this.firstName, this.lastName, this.awards.size(), this.filmography.stream()
                .sorted(Comparator.comparing(Movie::getTitle))
                .map(Movie::getTitle).collect(Collectors.joining("\n")));
    }

    private String printIfAwardIsNotEmpty() {
        return String.format("%s %s\nOscars: %s (%s)\nFilmer:\n%s", this.firstName, this.lastName, this.awards.size(), awards.stream()
                .sorted(Comparator.comparing(AcademyAward::getName))
                .map(AcademyAward::getName).collect(Collectors.joining(", ")),
                this.filmography.stream()
                .sorted(Comparator.comparing(Movie::getTitle))
                .map(Movie::getTitle).collect(Collectors.joining("\n")));
    }
}