package com.example.passwordencryptionap;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainMenu extends AppCompatActivity {
    private PasswordManager passwordManager;
    private ArrayAdapter<PasswordEntry> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordManager = new PasswordManager();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, passwordManager.getPasswordEntries());

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open PasswordEntry activity when the Add button is clicked
                Intent intent = new Intent(MainMenu.this, PasswordEntryActivity.class);
                startActivityForResult(intent, 1);
            }
        });

        // Handle item clicks to display details or perform other actions
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PasswordEntry selectedEntry = adapter.getItem(position);
                // Implement the action you want when an item is clicked
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Retrieve the new PasswordEntry from the PasswordEntry activity
                PasswordEntry newEntry = data.getParcelableExtra("newEntry");

                // Check if the new entry is not null before adding it
                if (newEntry != null) {
                    passwordManager.addPasswordEntry(newEntry);
                    adapter.notifyDataSetChanged(); // Update the ListView
                } else {
                    Toast.makeText(this, "Error: New entry is null or not a PasswordEntry", Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}