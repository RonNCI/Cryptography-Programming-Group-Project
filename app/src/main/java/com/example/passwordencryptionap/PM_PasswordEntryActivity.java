package com.example.passwordencryptionap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import javax.crypto.SecretKey;

public class PM_PasswordEntryActivity extends AppCompatActivity {
    private SecretKey secretKey;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_entry);

        // Initialize or retrieve your AES key here
        try {
            secretKey = PM_EncryptionActivity.generateKey(); // In a real app, manage this key securely
        } catch (Exception e) {
            e.printStackTrace(); // Handle exceptions properly
        }

        final EditText loginEditText = findViewById(R.id.loginEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        final EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        Button addPasswordButton = findViewById(R.id.addPasswordButton);

        addPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get the entered values and encrypt them
                    String login = PM_EncryptionActivity.encrypt(loginEditText.getText().toString(), secretKey);
                    String password = PM_EncryptionActivity.encrypt(passwordEditText.getText().toString(), secretKey);
                    String description = PM_EncryptionActivity.encrypt(descriptionEditText.getText().toString(), secretKey);

                    // Create a new PasswordEntry with encrypted data
                    PM_PasswordEntry newEntry = new PM_PasswordEntry(login, password, description);

                    // Return the new entry to the MainMenu activity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newEntry", newEntry);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // Close the PasswordEntry activity
                } catch (Exception e) {
                    e.printStackTrace(); // Handle encryption exceptions
                    // Optionally, show a user-friendly error message
                }
            }
        });
    }
}