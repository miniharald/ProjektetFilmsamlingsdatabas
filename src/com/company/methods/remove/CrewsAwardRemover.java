package com.company.methods.remove;

import com.company.App;
import com.company.dbmaker.FileManager;

import java.util.Scanner;

public class CrewsAwardRemover {

    private App app;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);

    public CrewsAwardRemover(App app) {
        this.app = app;
    }

    public void removeAwardFromActor(Object o) {
        fileManager.showListOfOptions(app.getActors());
        int goBack = app.getActors().size() + 1;
        int actorChoice = getChoice(goBack);
        if (actorChoice < goBack) {
            fileManager.showListOfOptions(app.getActors().get(actorChoice).getAwards());
            goBack = app.getActors().get(actorChoice).getAwards().size() + 1;
            int choice = getChoice(goBack);
            if (actorChoice < goBack) {
                app.getActors().get(actorChoice).removeFromAwards(app.getActors()
                        .get(actorChoice)
                        .getAwards().get(choice));
                fileManager.writeToFile(App.ACTORFOLDER + app.getActors().get(actorChoice).getId() +
                        ".txt", app.getActors().get(actorChoice));
            } else if (choice == goBack) {
                return;
            }
        } else if (actorChoice == goBack) {
            return;
        }
    }

    public void removeAwardFromDirector(Object o) {
        fileManager.showListOfOptions(app.getDirectors());
        int goBack = app.getDirectors().size() + 1;
        int directorChoice = getChoice(goBack);
        if (directorChoice < goBack) {
            fileManager.showListOfOptions(app.getDirectors().get(directorChoice).getAwards());
            goBack = app.getDirectors().get(directorChoice).getAwards().size() + 1;
            int choice = getChoice(goBack);
            if (directorChoice < goBack) {
                app.getDirectors().get(directorChoice).removeFromAwards(app.getDirectors()
                        .get(directorChoice)
                        .getAwards().get(choice));
                fileManager.writeToFile(App.DIRECTORFOLDER + app.getDirectors().get(directorChoice).getId() +
                        ".txt", app.getDirectors().get(directorChoice));
            } else if (choice == goBack) {
                return;
            }
        } else if (directorChoice == goBack) {
            return;
        }
    }

    private int getChoice(int goBack) {
        System.out.println(goBack + ".) Gå tillbaka");
        System.out.println("Välj ett alternativ ovan!");
        String inputChoice = scan.nextLine();
        int choice = Integer.parseInt(inputChoice) - 1;
        return choice;
    }
}
