package com.company.methods.update;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.objects.Actor;
import com.company.objects.Movie;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class MovieObjectsUpdater {
    App app;
    FileManager fileManager = new FileManager<>();
    Scanner scan = new Scanner (System.in);
    String firstName = "";
    String lastName = "";
    int counter = 0;
    int choice = 0;

    public MovieObjectsUpdater(App app) {
        this.app = app;
    }

    public void updateActor(Object o) {
        chooseCrew(Collections.unmodifiableList(app.getActors()));
        if (choice < counter) {
            inputCrewsName();
            app.getActors().get(choice).setFirstName(firstName);
            app.getActors().get(choice).setLastName(lastName);
            fileManager.writeToFile(App.ACTORFOLDER + app.getActors().get(choice).getId() + ".txt", app.getActors().get(choice));
            for (Movie movie : app.getMovies()) {
                for (Actor actor : movie.getCast())
                if (actor.getId().equals(app.getActors().get(choice).getId())) {
                    movie.removeFromCast(actor);
                    movie.addToCast(app.getActors().get(choice));
                    fileManager.writeToFile(App.MOVIEFOLDER + movie.getId() + ".txt", movie);
                }
            }
        }
    }

    public void chooseCrew(List<BaseObject> list) {
        counter = fileManager.showListOfOptions(list);
        String input = scan.nextLine();
        choice = Integer.parseInt(input) - 1;
    }

    public void inputCrewsName() {
        System.out.println("Förnamn:");
        firstName = scan.nextLine();
        System.out.println("Efternamn:");
        lastName = scan.nextLine();
    }
}
