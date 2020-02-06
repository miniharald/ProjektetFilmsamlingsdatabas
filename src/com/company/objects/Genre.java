package com.company.objects;

import com.company.dbmaker.BaseObject;

public class Genre extends BaseObject {

    private String name;

    public Genre(String name) {
        super();
        this.name = name;
    }

    public Genre() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }



    public String writeToString() {
        return "Id: " + getId() + "\nGenre: " + this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}
