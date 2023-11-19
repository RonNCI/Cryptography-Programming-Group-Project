package com.example.passwordencryptionap;

import android.os.Parcel;
import android.os.Parcelable;

public class PM_PasswordEntry implements Parcelable {
    private String login;
    private String password;
    private String description;

    public PM_PasswordEntry(String login, String password, String description) {
        this.login = login;
        this.password = password;
        this.description = description;
    }

    public PM_PasswordEntry() {
        this.login = "";
        this.password = "";
        this.description = "";
    }
    // Getters and setters for login, password, and description
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    // Parcelable implementation
    protected PM_PasswordEntry(Parcel in) {
        login = in.readString();
        password = in.readString();
        description = in.readString();
    }

    public static final Creator<PM_PasswordEntry> CREATOR = new Creator<PM_PasswordEntry>() {
        @Override
        public PM_PasswordEntry createFromParcel(Parcel in) {
            return new PM_PasswordEntry(in);
        }

        @Override
        public PM_PasswordEntry[] newArray(int size) {
            return new PM_PasswordEntry[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(login);
        dest.writeString(password);
        dest.writeString(description);
    }
}