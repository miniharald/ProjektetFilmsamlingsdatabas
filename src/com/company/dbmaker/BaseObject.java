package com.company.dbmaker;

import java.io.Serializable;

public abstract class BaseObject implements Serializable {
    private String id;
    private static final long serialVersionUID = 42L;

    public BaseObject() {
        this.id = generateId();
    }

    abstract public String getPrimary();

    abstract public String getSearchTerms();

    abstract public String ToStringForList();

    abstract public String toString();

    public String getId() {
        return id;
    }

    private String generateId() {
        return String.valueOf(System.currentTimeMillis());
    }
}
