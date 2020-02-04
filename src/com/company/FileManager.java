package com.company;

import java.util.Random;

public class FileManager {

    public FileManager() {
    }

    public String idGenerator() {
        Random random = new Random();
        int number = random.nextInt(99999999);
        return String.format("%08d", number);
    }
}
