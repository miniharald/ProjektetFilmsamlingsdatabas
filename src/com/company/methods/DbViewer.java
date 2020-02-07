package com.company.methods;

import com.company.App;
import com.company.dbmaker.InputChecker;

import java.util.Scanner;

public class DbViewer {

    private Scanner scan = new Scanner(System.in);
    App app;
    InputChecker checker;
    private boolean inputOk = false;

    public DbViewer(App app) {
        this.app = app;
        this.checker = new InputChecker(app);
    }
}
