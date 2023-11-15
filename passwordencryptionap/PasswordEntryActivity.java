package com.example.passwordencryptionap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class PasswordEntryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_entry);

        final EditText loginEditText = findViewById(R.id.loginEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        final EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        Button addPasswordButton = findViewById(R.id.addPasswordButton);

        addPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Get the entered values
                String login = loginEditText.getText().toString();
                String password = passwordEditText.getText().toString();
                String description = descriptionEditText.getText().toString();

                // Create a new PasswordEntry
                PasswordEntry newEntry = new PasswordEntry(login, password, description);

                // Return the new entry to the MainMenu activity
                Intent resultIntent = new Intent();
                resultIntent.putExtra("newEntry", newEntry);
                setResult(RESULT_OK, resultIntent);
                finish(); // Close the PasswordEntry activity
            }
        });
    }
}