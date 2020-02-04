package com.company;

import java.io.File;
import java.util.Random;

public class FileManager {

    public FileManager() {
    }

    public File[] readFromFolder(File folderPath) {
        File[] fileList = folderPath.listFiles();
        return fileList;
    }

    public String idGenerator() {
        Random random = new Random();
        int number = random.nextInt(99999999);
        return String.format("%08d", number);
    }
}
