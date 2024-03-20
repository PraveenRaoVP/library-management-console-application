package com.librarymanagement;

import com.librarymanagement.auth.LoginView;

public class Main {
    private static  Main libraryManagement;

    private String appName = "Library Management System";

    private String appVersion = "0.0.1";

    //default constructor should be private so that we cannot create an instance from other class.
    private Main() {

    }

    //Creating a single instance of this application class.
    //So that we access the application info(appName, appVersion) from any where in the application.
    public static Main getInstance() {
        if(libraryManagement == null) {
            libraryManagement = new Main();
        }
        return libraryManagement;
    }


    private void create() throws InterruptedException {
        LoginView loginView = new LoginView();
        loginView.init();
    }

    public String getAppName() {
        return appName;
    }

    public String getAppVersion() {
        return appVersion;
    }

    //Application execution starts from here.
    public static void main(String arg[]) throws InterruptedException {

        //Application created and started from here.
        Main.getInstance().create();
    }
}