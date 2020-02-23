package com.company.dbmaker;

import java.io.Serializable;
import java.time.Instant;

public abstract class BaseObject implements Serializable {
    private String id;
    private static final long serialVersionUID = 42L;

    public BaseObject() {
        this.id = generateId();
    }



    abstract public String getPrimary();

    abstract public String getAll();

    abstract public String listToString();

    abstract public String toString();

    public String getId() {
        return id;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
