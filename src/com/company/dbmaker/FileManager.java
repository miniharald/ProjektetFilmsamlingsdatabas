package com.company.dbmaker;

import com.company.objects.Movie;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class FileManager<D> {

    public FileManager() {
    }

    public File[] readFromFolder(File folderPath) {
        File[] fileList = folderPath.listFiles();
        return fileList;
    }

    public Object readFromFile(String path) {
        Object object = null;
        try (ObjectInputStream fileObject = new ObjectInputStream(new FileInputStream(path))) {
            object = fileObject.readObject();
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

    public void writeToFile(String fileName, Object object) {
        Field[] fields = object.getClass().getDeclaredFields();
        String test = "";
        for (Field field : fields) {
            LengthChecker annotation = field.getAnnotation(LengthChecker.class);
            if (annotation != null /*&& field.getClass().equals(String.class)*/) {
                try {
                    field.setAccessible(true);
                    test = (String) field.get(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

                if (test.length() != annotation.length()) {
                    System.out.println("Rätt tecken i " + annotation.name() + " möttes ej. Krävs " + annotation.length() + ".");
                    return;
                }
            }
        }

        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(fileName))) {
            output.writeObject(object);
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
        if (readFromFolder(folderPath).length < 1) {
            list = new ArrayList<>();
        }
        return list;
    }

    public int showListOfOptions(List<BaseObject> list) {
        int counter = 1;
        for (BaseObject baseObject : list) {
            System.out.println(counter + ".) " + baseObject.ToStringForList());
            counter++;
        }
        return counter;
    }

    public void removeDbObject(int choice, List<BaseObject> list, String folder) {
        list.remove(list.get(choice));
        deleteFiles(Paths.get(folder + list.get(choice).getId() + ".txt"));
    }

    public List<BaseObject> search(String input, List<BaseObject> list) {
        List<BaseObject> newList = list.stream()
                .filter(o -> o.getSearchTerms().toLowerCase().contains(input.toLowerCase()))
                .sorted(Comparator.comparing(BaseObject::getPrimary))
                .collect(Collectors.toList());
        return newList;
    }
}