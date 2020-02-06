package com.company.methods;

import com.company.App;

import java.util.Scanner;

public class DbViewer {

    private Scanner scan = new Scanner(System.in);
    App app;
    Checker checker;
    private boolean inputOk = false;

    public DbViewer(App app) {
        this.app = app;
        this.checker = new Checker(app);
    }
}
