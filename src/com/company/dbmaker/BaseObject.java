package com.company.dbmaker;

import com.company.dbmaker.annotations.Entity;
import com.company.dbmaker.annotations.Id;

import java.io.Serializable;

@Entity
public abstract class BaseObject implements Serializable {
    @Id
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
