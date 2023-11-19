package com.example.passwordencryptionap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.content.SharedPreferences;
import android.widget.CheckBox;
import java.util.Map;

public class PM_Login extends AppCompatActivity {
    //declare variables
    private EditText pm_editTextUsername;
    private EditText pm_editTextPassword;
    private Button pm_buttonLogin;
    private Button pm_buttonRegister;
    private CheckBox pm_rememberMe;
    private int pm_chance = 5;
    boolean pm_valid = false;
    public PM_userINFO PMuserINFO;

    SharedPreferences sharedPreferences;
    SharedPreferences.Editor sharedPreferencesEditor;

    //set view activity_login
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //linking variables into layout ID
        pm_editTextUsername = findViewById(R.id.pm_loginEmail);
        pm_editTextPassword = findViewById(R.id.pm_loginPassword);
        pm_buttonLogin = findViewById(R.id.pm_signinButton);
        pm_buttonRegister = findViewById(R.id.pm_registerBttn);
        pm_rememberMe = findViewById(R.id.cbRememberMe);

        PMuserINFO = new PM_userINFO();

        sharedPreferences = getApplicationContext().getSharedPreferences("CredentialsDB", MODE_PRIVATE);
        sharedPreferencesEditor = sharedPreferences.edit();

        if(sharedPreferences != null){

            Map<String, ?> preferencesMap = sharedPreferences.getAll();

            if(preferencesMap.size() != 0){
                PMuserINFO.loadUserInformation(preferencesMap);
            }

            String savedUsername = sharedPreferences.getString("LastSavedUsername", "");
            String savedPassword = sharedPreferences.getString("LastSavedPassword", "");

            if(sharedPreferences.getBoolean("RememberMeCheckbox", false)){
                pm_editTextUsername.setText(savedUsername);
                pm_editTextPassword.setText(savedPassword);
                pm_rememberMe.setChecked(true);
            }
        }

        pm_rememberMe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                sharedPreferencesEditor.putBoolean("RememberMeCheckbox", pm_rememberMe.isChecked());
                sharedPreferencesEditor.apply();
            }
        });

        //on click method
        pm_buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String inputName = pm_editTextUsername.getText().toString();
                String inputPassword = pm_editTextPassword.getText().toString();

                if(inputName.isEmpty() || inputPassword.isEmpty())
                {
                    Toast.makeText(PM_Login.this, "Please enter all the details correctly!", Toast.LENGTH_SHORT).show();
                }else{
                    pm_valid = pm_Validate(inputName, inputPassword);
                    if(!pm_valid){
                        pm_chance--;
                        Toast.makeText(PM_Login.this, "Incorrect credentials entered!", Toast.LENGTH_SHORT).show();

                        if(pm_chance == 0){
                            pm_buttonLogin.setEnabled(false);
                        }
                    }else{

                        Toast.makeText(PM_Login.this, "Login successful!", Toast.LENGTH_SHORT).show();

                        sharedPreferencesEditor.putString("LastSavedUsername", inputName);
                        sharedPreferencesEditor.putString("LastSavedPassword", inputPassword);
                        sharedPreferencesEditor.apply();

                        // Add the code to go to new activity
                        Intent intent = new Intent(PM_Login.this, PM_Menu.class);
                        startActivity(intent);
                    }
                }
            }
        });

        pm_buttonRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PM_Login.this, PM_RegistrationActivity.class);
                startActivity(intent);
            }
        });
    }
    //validates username and password
    private boolean pm_Validate(String inputName, String inputPassword) {
        return PMuserINFO.checkInformation(inputName, inputPassword);
    }
}