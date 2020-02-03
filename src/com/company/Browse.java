package com.company;

import java.util.Scanner;

public class Browse {

    private Scanner scan = new Scanner(System.in);
    App app;
    Check checker;
    private boolean inputOk = false;

    public Browse(App app) {
        this.app = app;
        this.checker = new Check(app);
    }
}
