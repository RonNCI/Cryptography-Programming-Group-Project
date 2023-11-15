package com.example.passwordencryptionap;

import android.os.Parcel;
import android.os.Parcelable;

public class PasswordEntry implements Parcelable {
    private String login;
    private String password;
    private String description;

    public PasswordEntry(String login, String password, String description) {
        this.login = login;
        this.password = password;
        this.description = description;
    }

    // Getters and setters for login, password, and description

    // Parcelable implementation
    protected PasswordEntry(Parcel in) {
        login = in.readString();
        password = in.readString();
        description = in.readString();
    }

    public static final Creator<PasswordEntry> CREATOR = new Creator<PasswordEntry>() {
        @Override
        public PasswordEntry createFromParcel(Parcel in) {
            return new PasswordEntry(in);
        }

        @Override
        public PasswordEntry[] newArray(int size) {
            return new PasswordEntry[size];
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