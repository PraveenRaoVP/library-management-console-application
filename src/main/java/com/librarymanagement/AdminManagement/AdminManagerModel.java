package com.librarymanagement.AdminManagement;

import com.librarymanagement.models.Admin;
import com.librarymanagement.models.Credentials;
import com.librarymanagement.repository.AdminDatabase;
import com.librarymanagement.repository.AdminToCredentialsDatabase;
import com.librarymanagement.repository.AdminToLibraryDatabase;
import com.librarymanagement.repository.CredentialsDatabase;

import java.util.List;

public class AdminManagerModel {
    private final AdminManagerView adminManagerView;

    public AdminManagerModel(AdminManagerView adminManagerView) {
        this.adminManagerView = adminManagerView;
    }

    public void addAdmin(int libraryId, String name, String email, String address) {
        /**
         * The below code snippet is to add an admin to the database.
         */
        int adminId = AdminDatabase.getInstance().getAdminList().size()+1;
        Admin admin = new Admin(adminId, name, email, address);
        AdminDatabase.getInstance().insertAdmin(admin);
        int credId = CredentialsDatabase.getInstance().getCredentialsList().size()+1;
        AdminDatabase.getInstance().pushDataToJSON();
        CredentialsDatabase.getInstance().insertCredentials(new Credentials(credId , email, "admin"));
        AdminToCredentialsDatabase.getInstance().addAdminCred(adminId, credId);
        AdminToLibraryDatabase.getInstance().addLibraryAdmin(libraryId, adminId);
        AdminDatabase.getInstance().pushDataToJSON();
        AdminToCredentialsDatabase.getInstance().pushDataToJSON();
        AdminToLibraryDatabase.getInstance().pushDataToJSON();
        adminManagerView.showAlert("Admin added successfully");
    }

    public void removeAdmin(int adminId) {
        AdminDatabase.getInstance().pullDataFromJSON();
        AdminToLibraryDatabase.getInstance().pullDataFromJSON();
        AdminToCredentialsDatabase.getInstance().deleteAdminCred(adminId);
        AdminDatabase.getInstance().deleteAdmin(adminId);
        adminManagerView.showAlert("Admin removed successfully");
        AdminDatabase.getInstance().pushDataToJSON();
        AdminToCredentialsDatabase.getInstance().pushDataToJSON();
    }

    public void updateAdmin(int adminId, String name, String email, String address) {
        AdminDatabase.getInstance().updateAdmin(adminId, name, email, address);
        adminManagerView.showAlert("Admin updated successfully");
    }

    public List<Admin> getAllAdminList() {
        return AdminDatabase.getInstance().getAdminList();
    }

    public Admin getAdminForLibrary(int libraryId) {
        int adminId = AdminToLibraryDatabase.getInstance().getAdminByLibraryId(libraryId);
        return AdminDatabase.getInstance().getAdmin(adminId);
    }
}
