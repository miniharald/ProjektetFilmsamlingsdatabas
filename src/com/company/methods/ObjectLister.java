package com.company.methods;

import com.company.App;
import com.company.objects.*;

public class ObjectLister {

    private App app;

    public ObjectLister(App app) {
        this.app = app;
    }

    public void listMovies(int counter) {
        for (Movie movie : app.getMovies()) {
            System.out.println("[" + counter + "]" + " " + movie.getTitle());
            counter++;
        }
    }

    public void listActors(int counter) {
        for (Actor actor : app.getActors()) {
            System.out.println("[" + counter + "]" + " " + actor.getWholeName());
            counter++;
        }
    }

    public void listDirectors(int counter) {
        for (Director director : app.getDirectors()) {
            System.out.println("[" + counter + "]" + " " + director.getWholeName());
            counter++;
        }
    }

    public void listAwards(int counter) {
        for (AcademyAward award : app.getAwards()) {
            System.out.println("[" + counter + "]" + " " + award.getName());
            counter++;
        }
    }

    public void listGenres(int counter) {
        for (Genre genre : app.getGenres()) {
            System.out.println("[" + counter + "]" + " " + genre.getName());
            counter++;
        }
    }

    public void listFormats(int counter) {}
}
