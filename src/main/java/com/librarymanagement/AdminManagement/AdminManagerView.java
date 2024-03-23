package com.librarymanagement.AdminManagement;

import com.librarymanagement.models.Admin;
import com.librarymanagement.repository.*;
import com.librarymanagement.setup.LibrarySetupView;
import com.librarymanagement.validation.ValidationModel;

import java.util.List;
import java.util.Scanner;

public class AdminManagerView {
    private final AdminManagerModel adminManagerModel;

    public AdminManagerView() {
        this.adminManagerModel = new AdminManagerModel(this);
    }
    Scanner sc = new Scanner(System.in);

    public void addAdminView() {
        System.out.println("Do you wish to see the list of libraries? (Y/N): ");
        char choice = sc.next().charAt(0);
        if(choice == 'Y' || choice == 'y') {
            System.out.println("List of libraries: ");
            LibrarySetupView librarySetupView = new LibrarySetupView();
            librarySetupView.viewLibraries();
        }
        System.out.println("Enter the library ID: ");
        int libraryId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the details of the admin: ");
        System.out.println("Enter the admin name: ");
        String name = sc.nextLine();
        String email;
        while(true) {
            System.out.println("Enter the email of the admin: ");
            email = sc.nextLine();
            if(!ValidationModel.getInstance().validateEmail(email)) {
                System.out.println("Invalid email id! Enter the email id again!");
            } else break;
        }
        System.out.println("Enter the address of the admin: ");
        String address = sc.nextLine();

        adminManagerModel.addAdmin(libraryId, name, email, address);
        AdminDatabase.getInstance().pushDataToJSON();
        AdminToLibraryDatabase.getInstance().pushDataToJSON();
    }

    public void showAlert(String message) {
        System.out.println(message);
    }

    public void removeAdminView() {
        AdminDatabase.getInstance().pullDataFromJSON();
        LibraryDatabase.getInstance().pullDataFromJSON();
        System.out.println("Do you wish to see the list of libraries? (Y/N): ");
        char choice = sc.next().charAt(0);
        if(choice == 'Y') {
            System.out.println("List of libraries: ");
            LibrarySetupView librarySetupView = new LibrarySetupView();
            librarySetupView.viewLibraries();
        }
        System.out.println("Enter the library ID: ");
        int libraryId = sc.nextInt();
        sc.nextLine();
        System.out.println("The admin present in the library:-");
        Admin admin = adminManagerModel.getAdminForLibrary(libraryId);
        System.out.println("Admin ID: "+admin.getAdminId());
        System.out.println("Admin Name: "+admin.getName());
        System.out.println("Admin Email: "+admin.getEmailId());
        System.out.println("Admin Address: "+admin.getAddress());
        System.out.println("Do you wish to remove this admin? (Y/N): ");
        char ch = sc.next().charAt(0);
        if(ch == 'Y') {
            adminManagerModel.removeAdmin(admin.getAdminId());
        } else {
            System.out.println("Admin not removed.");
        }
        AdminDatabase.getInstance().pushDataToJSON();
        AdminToLibraryDatabase.getInstance().pushDataToJSON();
    }

    public void viewAdmins() {
        AdminDatabase.getInstance().pullDataFromJSON();
        List<Admin> adminList= adminManagerModel.getAllAdminList();
        if(adminList.size() == 0) {
            System.out.println("No admins present.");
            return;
        }
        System.out.println("AdminId \t Admin Name \t Admin Email \t Admin Address");
        for(Admin admin: adminList) {
            System.out.println(admin.getAdminId()+"\t"+admin.getName()+"\t"+admin.getEmailId()+"\t"+admin.getAddress());
        }
    }

    public void updateAdminView() {
        AdminDatabase.getInstance().pullDataFromJSON();
        System.out.println("Enter the admin ID: ");
        int adminId = sc.nextInt();
        sc.nextLine();
        System.out.println("Enter the new name of the admin: ");
        String name = sc.nextLine();
        String email;
        while(true) {
            System.out.println("Enter the email of the admin: ");
            email = sc.nextLine();
            if(!ValidationModel.getInstance().validateEmail(email)) {
                System.out.println("Invalid email id! Enter the email id again!");
            } else break;
        }
        System.out.println("Enter the new address of the admin: ");
        String address = sc.nextLine();
        adminManagerModel.updateAdmin(adminId, name, email, address);
        AdminDatabase.getInstance().pushDataToJSON();
    }

    public void changePassword() {
        AdminDatabase.getInstance().pullDataFromJSON();
        CredentialsDatabase.getInstance().pullDataFromJSON();
        AdminToCredentialsDatabase.getInstance().pullDataFromJSON();
        int adminId = CacheMemory.getInstance().getCurrentAdmin();
        System.out.println("Enter old password: ");
        String oldPassword = sc.nextLine();
        if(!CredentialsDatabase.getInstance().checkPassword(adminId, oldPassword)) {
            System.out.println("Incorrect password.");
            return;
        }
        System.out.println("Enter new password: ");
        String newPassword = sc.nextLine();
        System.out.println("Re-enter new password: ");
        String reEnterPassword = sc.nextLine();
        if(!newPassword.equals(reEnterPassword)) {
            System.out.println("Passwords do not match.");
            return;
        }
        CredentialsDatabase.getInstance().updatePassword(adminId, newPassword);
    }
}
