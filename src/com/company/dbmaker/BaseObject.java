package com.company.dbmaker;

import java.io.Serializable;

public class BaseObject implements Serializable {
    private String id;

    public BaseObject() {
        this.id = generateId();
    }

    public String getId() {
        return id;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
