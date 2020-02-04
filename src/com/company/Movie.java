package com.company;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Movie extends FileManager implements java.io.Serializable {

    private String id;
    private String title;
    private String year;
    private List<Genre> genre = new ArrayList<>();
    private List<Director> director = new ArrayList<>();
    private List<Actor> cast = new ArrayList<>();
    private List<AcademyAward> awards = new ArrayList<>();
    private String format;
    private String lengthMinutes;
    String[] stringsInfo = new String[9];

    public Movie() {
    }

    public Movie(String id, String title, String year, String format, String lengthMinutes) {
        this.id = id;
        this.title = title;
        this.year = year;
        this.genre = new ArrayList<>();
        this.director = new ArrayList<>();
        this.cast = new ArrayList<>();
        this.awards = new ArrayList<>();
        this.format = format;
        this.lengthMinutes = lengthMinutes;

    }

    public Movie(List<String> readFromFile, List<Genre> genres) {
        int i = 0;
        for (String content : readFromFile) {
            String trim = content.substring(content.indexOf(":") + 1).trim();
            stringsInfo[i] = trim;
            i++;
        }
        this.id = stringsInfo[0];
        this.title = stringsInfo[1];
        this.year = stringsInfo[2];
        String[] genreArray = stringsInfo[3].split(" ");
        for (String genreId : genreArray) {
            for (Genre genre : genres) {
                String matchId = genre.getId();
                if (matchId.equals(genreId)) {
                    this.genre.add(genre);
                }
            }
        }
        String[] awardArray = stringsInfo[5].split(" ");
        for (String genreId : genreArray) {
            for (AcademyAward award : awards) {
                String matchId = award.getId();
                if (matchId.equals(genreId)) {
                    this.awards.add(award);
                }
            }
        }
        this.director = new ArrayList<>();
        this.cast = new ArrayList<>();
        this.format = stringsInfo[8];
        this.lengthMinutes = stringsInfo[6];
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getLengthMinutes() {
        return lengthMinutes;
    }

    public void setLengthMinutes(String lengthMinutes) {
        this.lengthMinutes = lengthMinutes;
    }

    public String[] getStringsInfo() {
        return stringsInfo;
    }

    public void setStringsInfo(String[] stringsInfo) {
        this.stringsInfo = stringsInfo;
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
                id, title, year, movieGenre, lengthMinutes, movieAwards, movieDirector, movieCast, format);
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", year='" + year + '\'' +
                ", genre=" + genre +
                ", director=" + director +
                ", cast=" + cast +
                ", awards=" + awards +
                ", format='" + format + '\'' +
                ", lengthMinutes='" + lengthMinutes + '\'' +
                ", stringsInfo=" + Arrays.toString(stringsInfo) +
                '}';
    }
}
