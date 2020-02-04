package com.company;

import java.util.ArrayList;
import java.util.List;

public class Actor extends FileManager{

    private String id;
    private String firstName;
    private String lastName;
    private List<AcademyAward> awards = new ArrayList<>();
    private List<Movie> filmography = new ArrayList<>();

    public Actor(String firstName, String lastName, String id, Movie newMovie) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.id = id;
        this.filmography = new ArrayList<>();
        this.filmography.add(newMovie);
    }

    public Actor(List<String> readFromFile, List<Movie> movies) {
        int i = 0;
        String[] stringsInfo = new String[5];
        for (String content : readFromFile) {
            String trim = content.substring(content.indexOf(":") + 1).trim();
            stringsInfo[i] = trim;
            i++;
        }
        this.id = stringsInfo[0];
        this.firstName = stringsInfo[1];
        this.lastName = stringsInfo[2];
        String[] awardArray = stringsInfo[3].split(" ");
        for (String awardId : awardArray) {
            for (AcademyAward award : awards) {
                if (award.getId().equals(awardId)) {
                    this.awards.add(award);
                }
            }
        }
        String[] movieArray = stringsInfo[4].split(" ");
        for (String movieId : movieArray) {
            for (Movie movie : movies) {
                if (movie.getId().equals(movieId)) {
                    this.filmography.add(movie);
                }
            }
        }
    }

    public Actor() {
        this.id = idGenerator();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String WriteToString() {
        String movies = "";
        String awardsWon = "";
        for (Movie movie : this.filmography) {
            movies = movies.concat(movie.getId() + " ");
        }
        for (AcademyAward award : this.awards) {
            awardsWon = awardsWon.concat(award.getId() + " ");
        }
        return String.format("Id: %s\nTilltalsnamn: %s\nEfternamn: %s\nOscars: %s\nFilmer: %s",
                this.id, this.firstName, this.lastName, awardsWon, movies);
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }
}