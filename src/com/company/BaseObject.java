package com.company;

public class BaseObject implements java.io.Serializable {
    private String id;

    public BaseObject() {
        this.id = generateId();
    }

    public String getId() {
        return id;
    }

    private String generateId() {
        String newId = String.valueOf(System.currentTimeMillis());
        return newId;
    }
}
