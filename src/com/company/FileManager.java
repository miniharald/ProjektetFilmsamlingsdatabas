package com.company;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FileManager<D> {

    public FileManager() {
    }

    public File[] readFromFolder(File folderPath) {
        File[] fileList = folderPath.listFiles();
        return fileList;
    }

    public Object readFromFile(String path) {
        Object object = null;
        try {
            FileInputStream file = new FileInputStream(path);
            ObjectInputStream fileObject = new ObjectInputStream(file);
            object = fileObject.readObject();
            fileObject.close();
            file.close();
        } catch (IOException i) {
            System.out.println("Något blev fel vid inläsningen!");
            i.printStackTrace();
        } catch (ClassNotFoundException c) {
            System.out.println("Filen hittades inte!");
            c.printStackTrace();
        }
        return object;
    }

    public void deleteFiles(Path path) {
        File file = path.toFile();
        if (!file.exists()) {
            System.out.println("Filen existerar inte, försök igen!");
        } else {
            try {

                Files.delete(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public String idGenerator() {
        Random random = new Random();
        int number = random.nextInt(99999999);
        return String.format("%08d", number);
    }

    public boolean checkForDuplicateFileNames (File folderPath, String fileName) {
        File[] fileList = folderPath.listFiles();
        for (File file : fileList) {
            final Path path = file.toPath();
            String strPath = String.valueOf(path);
            if(strPath.contains(fileName)){
                return true;
            }
        }
        return false;
    }

    public void writeToFile(String fileName, Object object) {
        try {
            FileOutputStream file =
                    new FileOutputStream(fileName);
            ObjectOutputStream output = new ObjectOutputStream(file);
            output.writeObject(object);
            output.close();
            file.close();
        } catch (IOException i) {
            System.out.println("Någon blev fel när filen skulle skapas!");
            i.printStackTrace();
        }
    }

    public List<D> load(List<D> list, String pathName) {
        File folderPath = new File(pathName);
        for (File file : readFromFolder(folderPath)) {
            String path = String.valueOf(file);
            list.add((D) readFromFile(path));
        }
        if(readFromFolder(folderPath).length < 1) {
            list = new ArrayList<>();
        }
        return list;
    }
}
