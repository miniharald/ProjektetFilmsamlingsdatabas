package com.company.objects;

import com.company.dbmaker.BaseObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.groupingBy;

public class Movie extends BaseObject {

    private String title;
    private String year;
    private List<Genre> genre = new ArrayList<>();
    private List<Director> director = new ArrayList<>();
    private List<Actor> cast = new ArrayList<>();
    private List<AcademyAward> awards = new ArrayList<>();
    private Format format;
    private String lengthMinutes;

    public Movie() {
    }

    public Movie(String title, String year, Format format, String lengthMinutes) {
        super();
        this.title = title;
        this.year = year;
        this.genre = new ArrayList<>();
        this.director = new ArrayList<>();
        this.cast = new ArrayList<>();
        this.awards = new ArrayList<>();
        this.format = format;
        this.lengthMinutes = lengthMinutes;

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<Director> getDirector() {
        return director;
    }

    public void setDirector(List<Director> director) {
        this.director = director;
    }

    public List<Actor> getCast() {
        return cast;
    }

    public void setCast(List<Actor> cast) {
        this.cast = cast;
    }

    public List<AcademyAward> getAwards() {
        return awards;
    }

    public void setAwards(ArrayList<AcademyAward> awards) {
        this.awards = awards;
    }

    public Format getFormat() {
        return format;
    }

    public void setFormat(Format format) {
        this.format = format;
    }

    public String getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(String lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public void addToGenre(Genre newGenre) {
        genre.add(newGenre);
    }

    public void removeFromGenre(Genre newGenre) {
        genre.remove(newGenre);
    }

    public void addToCast(Actor newActor) {
        cast.add(newActor);
    }

    public void removeFromCast(Actor newActor) {
        cast.remove(newActor);
    }

    public void addToDirector(Director newDirector) {
        director.add(newDirector);
    }

    public void removeFromDirector(Director newDirector) {
        director.remove(newDirector);
    }

    public void addToAwards(AcademyAward newAward) {
        awards.add(newAward);
    }

    public void removeFromAwards(AcademyAward newAward) {
        awards.remove(newAward);
    }

    public String getPrimary() {
        return this.title;
    }

    public String writeToString() {
        String movieCast = "";
        String movieGenre = "";
        String movieDirector = "";
        String movieAwards = "";
        for (Director director : this.director) {
            movieDirector = movieDirector.concat(director.getId() + " ");
        }
        for (Actor actor : this.cast) {
            movieCast = movieCast.concat(actor.getId() + " ");
        }
        for (Genre genre : this.genre) {
            movieGenre = movieGenre.concat(genre.getId() + " ");
        }
        for (AcademyAward award : this.awards) {
            movieAwards = movieAwards.concat(award.getId() + " ");
        }
        return String.format("Id: %s\nTitel: %s\nÅr: %s\nGenre: %s\nLängd: %s minuter\nOscars: %s\nRegissör: %s\nSkådespelare: %s\nFormat: %s",
                getId(), title, year, movieGenre, lengthMinutes, movieAwards, movieDirector, movieCast, format);
    }

    public String listToString() {
        if(director.size() > 1) {
            return String.format("%s (%s) regisserad av %s", title, year, director.stream().map(Director::getWholeName).collect(Collectors.joining(" & ")));
        }
        return String.format("%s (%s) regisserad av %s %s", title, year, director.get(0).getFirstName(), director.get(0).getLastName());
    }

    @Override
    public String toString() {
        String time;
        /*int hours;
        int minutes;

        hours = Integer.parseInt(lengthMinutes) / 60;
        minutes = Integer.parseInt(lengthMinutes) % 60;*/
        time = String.format("%d timmar och %d minuter", Integer.parseInt(lengthMinutes) / 60, Integer.parseInt(lengthMinutes) % 60);

        if (awards.size() < 1) {
            return String.format("%s\nUtgiven: %s\nGenre: %s\nLängd: %s\nRegissör: %s\nSkådespelare: %s\nOscars: %s\nFormat: %s",
                    title, year, genre.stream()
                            .sorted(Comparator.comparing(Genre::getName))
                            .map(genre -> genre.getName()).collect(Collectors.joining(", "))
                    , time, director.stream()
                            .sorted(Comparator.comparing(Director::getWholeName))
                            .map(director -> director.getWholeName()).collect(Collectors.joining(" & "))
                    , cast.stream()
                            .sorted(Comparator.comparing(Actor::getWholeName))
                            .map(actor -> actor.getWholeName()).collect(Collectors.joining(", "))
                    , awards.size(), format);
        } else {
            return String.format("%s\nUtgiven: %s\nGenre: %s\nLängd: %s\nRegissör: %s\nSkådespelare: %s\nOscars: %s (%s)\nFormat: %s",
                    title, year, genre.stream()
                            .sorted(Comparator.comparing(Genre::getName))
                            .map(genre -> genre.getName()).collect(Collectors.joining(", "))
                    , time, director.stream()
                            .sorted(Comparator.comparing(Director::getWholeName))
                            .map(director -> director.getWholeName()).collect(Collectors.joining(" & "))
                    , cast.stream()
                            .sorted(Comparator.comparing(Actor::getWholeName))
                            .map(actor -> actor.getWholeName()).collect(Collectors.joining(", "))
                    , awards.size(), awards.stream()
                            .sorted(Comparator.comparing(AcademyAward::getName))
                            .map(award -> award.getName()).collect(Collectors.joining(", ")), format);
        }
    }
}