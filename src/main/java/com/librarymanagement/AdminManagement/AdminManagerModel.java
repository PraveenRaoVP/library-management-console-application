package com.librarymanagement.AdminManagement;

import com.librarymanagement.models.Admin;
import com.librarymanagement.repository.AdminDatabase;
import com.librarymanagement.repository.AdminToLibraryDatabase;

import java.util.List;

public class AdminManagerModel {
    private final AdminManagerView adminManagerView;

    public AdminManagerModel(AdminManagerView adminManagerView) {
        this.adminManagerView = adminManagerView;
    }

    public void addAdmin(int libraryId, String name, String email, String address) {
        int adminId = AdminDatabase.getInstance().getAdminList().size()+1;
        Admin admin = new Admin(adminId, name, email, address);
        AdminDatabase.getInstance().insertAdmin(admin);
        AdminToLibraryDatabase.getInstance().addLibraryAdmin(libraryId, adminId);
        adminManagerView.showAlert("Admin added successfully");
    }

    public void removeAdmin(int adminId) {
        AdminDatabase.getInstance().deleteAdmin(adminId);
        adminManagerView.showAlert("Admin removed successfully");
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
