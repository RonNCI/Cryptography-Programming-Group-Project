package com.example.passwordencryptionap;

import java.util.ArrayList;
import java.util.List;

// PasswordManager.java
public class PasswordManager {
    private static List<PM_PasswordEntry> passwordEntries;

    public PasswordManager() {

        this.passwordEntries = new ArrayList<>();
    }

    public void addPasswordEntry(PM_PasswordEntry entry) {

        passwordEntries.add(entry);
    }

    public static List<PM_PasswordEntry> getPasswordEntries() {

        return passwordEntries;
    }

}
