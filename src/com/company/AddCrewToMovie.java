package com.company;

import java.io.File;
import java.nio.file.Paths;
import java.util.Scanner;

public class AddCrewToMovie {

    AddMovie addMovie;
    private FileManager fileManager = new FileManager();
    private Scanner scan = new Scanner(System.in);
    private boolean inputOk = false;
    private boolean isDuplicate;
    private String id ="";

    public AddCrewToMovie(AddMovie addMovie) {
        this.addMovie = addMovie;
    }

    private void addDirector() {
        do {
            int counter = 1;
            for (Director directorObj : app.getDirectors()) {
                System.out.println("[" + counter + "]" + " " + directorObj.getFirstName() + " " + directorObj.getLastName());
                counter++;
            }
            System.out.println("[" + counter + "] Lägg till regissör");
            System.out.println("Välj ett alternativ ovan!");
            String directorChoice = scan.nextLine();
            int choice = Integer.parseInt(directorChoice);
            if (choice < counter) {
                addExistingDirector(choice);
                inputOk = true;
            } else if (choice == counter) {
                addNewDirector();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingDirector(int choice) {
        for (int i = 0; i < app.getDirectors().size(); i++) {
            if (choice - 1 == i) {
                id = app.getDirectors().get(i).getId();
                for (Director director : app.getDirectors()) {
                    if (director.getId().equals(id)) {
                        director.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        app.getMovies().get(app.getMovies().size() - 1).addToDirector(director);
                        director.deleteFiles(Paths.get("database/directors/" + id + ".txt"));
                        director.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(i));
                    }
                }
            }
        }
    }

    private void addNewDirector() {
        String directorFname;
        do {
            System.out.println("Förnamn:");
            directorFname = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(directorFname);
            if (directorFname.length() < 1 || directorFname.isBlank()) {
                System.out.println("Namnet måste innehålla minst en bokstav!");
                inputOk = false;
            }
        }while (!inputOk);
        String directorLname;
        do {
            System.out.println("Efternamn:");
            directorLname = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(directorLname);
            if (directorLname.length() < 1 || directorLname.isBlank()) {
                System.out.println("Namnet måste innehålla minst en bokstav!");
                inputOk = false;
            }
        }while (!inputOk);
        do {
            Director directorObj = new Director();
            id = directorObj.getId();
            File folderPath = new File("database/directors/");
            isDuplicate = directorObj.checkForDuplicateFileNames(folderPath, id);
        } while (isDuplicate);
        app.getDirectors().add(new Director(directorFname, directorLname, id, app.getMovies().get(app.getMovies().size() - 1)));
        Director directorObj = app.getDirectors().get(app.getDirectors().size() - 1);
        fileManager.writeToFile("database/directors/" + id + ".txt", app.getDirectors().get(app.getDirectors().size() - 1));
        app.getMovies().get(app.getMovies().size() - 1).addToDirector(directorObj);
    }

    private void addActor() {
        do {
            int counter = 1;
            for (Actor actorObj : app.getActors()) {
                System.out.println("[" + counter + "]" + " " + actorObj.getFirstName() + " " + actorObj.getLastName());
                counter++;
            }
            System.out.println("[" + counter + "] Lägg till skådespelare");
            System.out.println("Välj ett alternativ ovan!:");
            String actorChoice = scan.nextLine();
            int choice = Integer.parseInt(actorChoice);
            if (choice < counter) {
                addExistingActor(choice);
                inputOk = true;
            } else if (choice == counter) {
                addNewActor();
                inputOk = true;
            }
        } while (!inputOk);
    }

    private void addExistingActor(int choice) {
        for (int i = 0; i < app.getActors().size(); i++) {
            if (choice - 1 == i) {
                actorId = app.getActors().get(i).getId();
                for (Actor actor : app.getActors()) {
                    if (actor.getId().equals(actorId)) {
                        String actorsMovies = "";
                        actor.addToFilmography(app.getMovies().get(app.getMovies().size() - 1));
                        for (Movie movie : actor.getFilmography()) {
                            actorsMovies = actorsMovies.concat(movie.getId() + " ");
                        }
                        app.getMovies().get(app.getMovies().size() - 1).addToCast(actor);
                        actor.editFile("database/actors/" + actorId + ".txt", "Filmer", "Filmer: " + actorsMovies);
                    }
                }
            }
        }
    }

    private void addNewActor() {
        String actorFname;
        do {
            System.out.println("Förnamn:");
            actorFname = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(actorFname);
            if (actorFname.length() < 1 || actorFname.isBlank()) {
                System.out.println("Namnet måste innehålla minst en bokstav!");
                inputOk = false;
            }
        } while (!inputOk);
        String actorLname;
        do {
            System.out.println("Efternamn:");
            actorLname = scan.nextLine();
            inputOk = checker.checkIfStringOfLetters(actorLname);
            if (actorLname.length() < 1 || actorLname.isBlank()) {
                System.out.println("Namnet måste innehålla minst en bokstav!");
                inputOk = false;
            }
        } while (!inputOk);
        do {
            Actor actorObj = new Actor();
            actorId = actorObj.getId();
            File folderPath = new File("database/actors/");
            isDuplicate = fileManager.checkForDuplicateFileNames(folderPath, actorId);
        } while (isDuplicate);
        app.getActors().add(new Actor(actorFname, actorLname, actorId, app.getMovies().get(app.getMovies().size() - 1)));
        Actor actorObj = app.getActors().get(app.getActors().size() - 1);
        actorObj.writeToFile(("database/actors/" + actorObj.getId()), (actorObj.toString()));
        app.getMovies().get(app.getMovies().size() - 1).addToCast(actorObj);
    }
}
