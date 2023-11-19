package com.example.passwordencryptionap;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.util.Map;

public class PM_RegistrationActivity extends AppCompatActivity {

    private EditText pm_regUser;
    private EditText pm_regPassword;
    private Button pm_registerBttn;
    public PM_userINFO pm_userInformation;
    SharedPreferences pm_Accounts;
    SharedPreferences.Editor sharedPreferencesEditor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        pm_regUser = findViewById(R.id.pm_registerEmail);
        pm_regPassword = findViewById(R.id.pm_registerPassword);
        pm_registerBttn = findViewById(R.id.pm_registerButton);

        pm_userInformation = new PM_userINFO();

        pm_Accounts = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = pm_Accounts.edit();

        if(pm_Accounts != null){

            Map<String, ?> preferencesMap = pm_Accounts.getAll();

            if(preferencesMap.size() != 0){
                pm_userInformation.loadUserInformation(preferencesMap);
            }
        }

        pm_registerBttn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String regUsername = pm_regUser.getText().toString();
                String regPassword = pm_regPassword.getText().toString();

                if(validate(regUsername, regPassword)) {

                    if(pm_userInformation.checkUsername(regUsername)){
                        Toast.makeText(PM_RegistrationActivity.this, "Username already taken!", Toast.LENGTH_SHORT).show();
                    }else{

                        pm_userInformation.addAccount(regUsername, regPassword);

                        // Store user info
                        sharedPreferencesEditor.putString(regUsername, regPassword);
                        sharedPreferencesEditor.putString("LastSavedUsername", "");
                        sharedPreferencesEditor.putString("LastSavedPassword", "");
                        // save and add to DB
                        sharedPreferencesEditor.apply();

                        startActivity(new Intent(PM_RegistrationActivity.this, PM_Login.class));
                    }
                }
            }
        });

    }

    private boolean validate(String regUsername, String regPassword) {
        if(regUsername.isEmpty() || regPassword.length() < 8){
            Toast.makeText(this, "Please enter all the details, password should be atleast 8 characters!", Toast.LENGTH_SHORT).show();
            return false;
        }

        return true;
    }

}