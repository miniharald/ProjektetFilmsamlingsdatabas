package com.company.methods.add;

import com.company.App;
import com.company.dbmaker.BaseObject;
import com.company.dbmaker.FileManager;
import com.company.objects.MovieObjects;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class CrewsAwardAdder {

    private App app;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);

    public CrewsAwardAdder(App app) {
        this.app = app;
    }

    public void addAwardToActor(Object o) {
        addAwardToCrew(Collections.unmodifiableList(app.getActors()), App.ACTORFOLDER, MovieObjects.actor);
    }

    public void addAwardToDirector(Object o) {
        addAwardToCrew(Collections.unmodifiableList(app.getDirectors()), App.DIRECTORFOLDER, MovieObjects.director);
    }

    private void addAwardToCrew(List<BaseObject> list, String folder, MovieObjects crew) {
        fileManager.showListOfOptions(list);
        int goBack = list.size() + 1;
        int crewChoice = getChoice(goBack);
        if (crewChoice < goBack) {
            fileManager.showListOfOptions(app.getAwards());
            goBack = app.getAwards().size() + 1;
            int choice = getChoice(goBack);
            if (crewChoice < goBack) {
                switch (crew) {
                    case actor:
                        app.getActors().get(crewChoice).addToAwards(app.getAwards().get(choice));
                        break;
                    case director:
                        app.getDirectors().get(crewChoice).addToAwards(app.getAwards().get(choice));
                        break;
                }
                fileManager.writeToFile(folder + list.get(crewChoice).getId() +
                        ".txt", list.get(crewChoice));
            }
        }
    }

    private int getChoice(int goBack) {
        System.out.println(goBack + ".) Gå tillbaka");
        System.out.println("Välj ett alternativ ovan!");
        String inputChoice = scan.nextLine();
        return Integer.parseInt(inputChoice) - 1;
    }
}
