package com.company;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.util.Random;

public class FileManager {

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

    public String idGenerator() {
        Random random = new Random();
        int number = random.nextInt(99999999);
        return String.format("%08d", number);
    }
}
