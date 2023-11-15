package com.example.passwordencryptionap;

import java.util.ArrayList;
import java.util.List;

// PasswordManager.java
public class PasswordManager {
    private List<PasswordEntry> passwordEntries;

    public PasswordManager() {
        this.passwordEntries = new ArrayList<>();
    }

    public void addPasswordEntry(PasswordEntry entry) {
        passwordEntries.add(entry);
    }

    public List<PasswordEntry> getPasswordEntries() {
        return passwordEntries;
    }

}
