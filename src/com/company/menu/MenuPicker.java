package com.company.menu;

import java.util.function.Consumer;

public class MenuPicker {
    private char key;
    private String title;
    private Consumer function;

    public MenuPicker(String title, char key, Consumer function) {
        this.key = key;
        this.title = title;
        this.function = function;
    }

    public Consumer getFunction() {
        return function;
    }

    public char getKey() {
        return key;
    }

    protected String getTitle() {
        return title;
    }

    protected String getKeyAndTitle() {
        return getKey() + ".) " + getTitle();
    }
}