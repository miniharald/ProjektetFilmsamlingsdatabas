package com.company.objects;

import com.company.dbmaker.BaseObject;

public class Format extends BaseObject {

    private String name;

    public Format(String name) {
        super();
        this.name = name;
    }

    public Format() {
        super();
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

    public String listToString() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}