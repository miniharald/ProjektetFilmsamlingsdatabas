package com.company.dbmaker;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {
    private String id;
    private static final long serialVersionUID = 42L;

    public BaseObject() {
        this.id = generateId();
    }



    abstract public String getPrimary();

    abstract public String listToString();

    public String getId() {
        return id;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
