package com.company.objects;

import com.company.FileManager;

import java.util.List;

public class AcademyAward extends FileManager implements java.io.Serializable {

    private String name;
    private String id;

    public AcademyAward(String name) {
        this.name = name;
        this.id = idGenerator();
    }

    public AcademyAward(String name, String id) {
        this.name = name;
        this.id = id;
    }

    public AcademyAward(List<String> readFromFile) {
        int i = 0;
        String[] stringsInfo = new String[2];
        for (String content : readFromFile) {
            String trim = content.substring(content.indexOf(":") + 1).trim();
            stringsInfo[i] = trim;
            i++;
        }
        this.id = stringsInfo[0];
        this.name = stringsInfo[1];
    }

    public AcademyAward() {
        this.id = idGenerator();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String writeToString() {
        return "Id: " + this.id + "\nOscar: " + this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}