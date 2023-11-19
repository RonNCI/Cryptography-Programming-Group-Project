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

        //calls key generation method
        try {
            secretKey = PM_EncryptionActivity.generateKey();
        } catch (Exception e) {
            e.printStackTrace();
        }

        final EditText loginEditText = findViewById(R.id.loginEditText);
        final EditText passwordEditText = findViewById(R.id.passwordEditText);
        final EditText descriptionEditText = findViewById(R.id.descriptionEditText);
        Button addPasswordButton = findViewById(R.id.addPasswordButton);

        addPasswordButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    // Get the password through .getTest and encrypts the password
                    String login = loginEditText.getText().toString();
                    //Encrypts the users password by calling encrypt method from PM_EncryptionActivity encrypt()
                    String password = PM_EncryptionActivity.encrypt(passwordEditText.getText().toString(), secretKey);
                    String description = descriptionEditText.getText().toString();

                    //Encrypts the users password by calling encrypt method from PM_EncryptionActivity encrypt()



                    PM_PasswordEntry newEntry = new PM_PasswordEntry(login, password, description);

                    // Return the new entry to the MainMenu activity
                    Intent resultIntent = new Intent();
                    resultIntent.putExtra("newEntry", newEntry);
                    setResult(RESULT_OK, resultIntent);
                    finish(); // Closes the PasswordEntry activity
                } catch (Exception e) {
                    e.printStackTrace(); //Throws exceptions

                }
            }
        });
    }
}