package com.librarymanagement.auth;

import com.librarymanagement.Main;
import com.librarymanagement.MainMenu.MainMenuView;
import com.librarymanagement.models.Admin;
import com.librarymanagement.repository.AdminDatabase;
import com.librarymanagement.repository.AdminToCredentialsDatabase;
import com.librarymanagement.repository.CacheMemory;
import com.librarymanagement.repository.CredentialsDatabase;

import java.util.Scanner;

public class LoginView {
    private final LoginModel loginModel;
    public LoginView() {
        loginModel = new LoginModel(this);
    }

    public void init() throws InterruptedException {
        CredentialsDatabase.getInstance().pullDataFromJSON();
        int attempts = 3;
        Scanner sc = new Scanner(System.in);
        CredentialsDatabase.getInstance().pullDataFromJSON();
        do {

            System.out.print("Enter the username: ");
            String username = sc.nextLine();
            System.out.print("Enter the password: ");
            String password = sc.nextLine();
            if(loginModel.authenticateUser(username, password)) {
                if(loginModel.checkIfSuperAdmin(username)) {
                    CacheMemory.getInstance().setCurrentAdmin(-1);
                    onSuccess();
                    return;
                }
                int credId = CredentialsDatabase.getInstance().getCredentialsId(username);
                CacheMemory.getInstance().setCurrentAdmin(AdminToCredentialsDatabase.getInstance().getAdminId(credId));
                onSuccess();
                return;
            } else {
                System.out.println("Invalid credentials. Please try again.");
            }
        } while(attempts --  > 1);
        System.out.println("You have exceeded the number of attempts. Please try again later.");
    }
    public void onSuccess() throws InterruptedException {
        System.out.flush();
        System.out.println("\n\nLogin successful...\n\n ---- welcome to " + Main.getInstance().getAppName()
                + " - v" + Main.getInstance().getAppVersion() + "----");
        int currentAdminId = CacheMemory.getInstance().getCurrentAdmin();
        if (currentAdminId != -1) {
            Admin admin = AdminDatabase.getInstance().getAdmin(currentAdminId);
            if (admin != null) {
                System.out.println("Hello!" + admin.getName());
            } else {
                System.out.println("Hello!");
            }
        } else {
            System.out.println("Hello!");
        }
        MainMenuView mainMenuView = new MainMenuView();
        mainMenuView.init();
    }


    public void showAlert(String message) {
        System.out.println(message);
    }
}
