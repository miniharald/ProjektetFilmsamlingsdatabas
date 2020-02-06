package com.company.objects;

import com.company.dbmaker.BaseObject;

public class AcademyAward extends BaseObject {

    private String name;

    public AcademyAward(String name) {
        super();
        this.name = name;
    }

    public AcademyAward() {
        super();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public String writeToString() {
        return "Id: " + this.getId() + "\nOscar: " + this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}