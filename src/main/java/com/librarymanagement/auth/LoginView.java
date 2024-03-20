package com.librarymanagement.auth;

import com.librarymanagement.Main;
import com.librarymanagement.MainMenu.MainMenuView;

import java.util.Scanner;

public class LoginView {
    private final LoginModel loginModel;
    public LoginView() {
        loginModel = new LoginModel(this);
    }

    public void init() throws InterruptedException {
        int attempts = 3;
        Scanner sc = new Scanner(System.in);
        do {

            System.out.print("Enter the username: ");
            String username = sc.nextLine();
            System.out.print("Enter the password: ");
            String password = sc.nextLine();
            if(loginModel.authenticateUser(username, password)) {
                onSuccess();
                return;
            }
        } while(attempts --  > 1);
        System.out.println("You have exceeded the number of attempts. Please try again later.");
    }
        public void onSuccess() throws InterruptedException {
            System.out.flush();
            System.out.println("\n\nLogin successful...\n\n ---- welcome to " + Main.getInstance().getAppName()
                    + " - v" + Main.getInstance().getAppVersion() + "----");
            MainMenuView mainMenuView = new MainMenuView();
            mainMenuView.init();
        }


    public void showAlert(String message) {
        System.out.println(message);
    }
}
