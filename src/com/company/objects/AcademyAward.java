package com.company.objects;

import com.company.dbmaker.BaseObject;

public class AcademyAward extends BaseObject {

    private String name;

    public AcademyAward(String name) {
        super();
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrimary() {
        return this.name;
    }

    @Override
    public String getKeyWords() {
        return this.name;
    }

    public String listToString() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}