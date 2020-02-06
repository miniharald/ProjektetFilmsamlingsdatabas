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

    public String writeToString() {
        return "Id: " + getId() + "\nFormat: " + this.name;
    }

    @Override
    public String toString() {
        return name;
    }
}