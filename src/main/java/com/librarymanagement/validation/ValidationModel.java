package com.librarymanagement.validation;

import org.jetbrains.annotations.NotNull;

public class ValidationModel {

    public static ValidationModel validationModel;

    private ValidationModel() {
    }

    public static ValidationModel getInstance() {
        if(validationModel == null) {
            validationModel = new ValidationModel();
        }
        return validationModel;
    }

    public boolean validateEmail(@NotNull String email) {
        return email.matches("^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$");
    }

    public boolean validateUserName(@NotNull String name) {
        return name.matches("^[a-zA-Z0-9]*$");
    }

    public boolean validatePassword(@NotNull String password) {
        return password.matches("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    public boolean validatePhoneNo(@NotNull String phoneNumber) {
        return phoneNumber.matches("^[0-9]{10}$");
    }
}
