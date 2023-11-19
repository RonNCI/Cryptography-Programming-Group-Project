package com.example.passwordencryptionap;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class PM_Menu extends AppCompatActivity {
    private PasswordManager passwordManager;
    private CustomAdapter adapter; // Custom adapter to display only descriptions
    private static final int ADD_ENTRY_REQUEST_CODE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        passwordManager = new PasswordManager();
        adapter = new CustomAdapter(this, android.R.layout.simple_list_item_1, PasswordManager.getPasswordEntries());

        ListView listView = findViewById(R.id.listView);
        listView.setAdapter(adapter);

        Button addButton = findViewById(R.id.addButton);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open PasswordEntry activity when the Add button is clicked
                Intent intent = new Intent(PM_Menu.this, PM_PasswordEntryActivity.class);
                startActivityForResult(intent, ADD_ENTRY_REQUEST_CODE);
            }
        });

        // Handle item clicks to display details or perform other actions
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PM_PasswordEntry selectedEntry = adapter.getItem(position);
                // Implement the action you want when an item is clicked
            }
        });
        // Handle item clicks to display details or perform other actions
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                PM_PasswordEntry selectedEntry = adapter.getItem(position);
                showDetailsPopup(selectedEntry);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            if (resultCode == RESULT_OK) {
                // Retrieve the new PasswordEntry from the PasswordEntry activity
                PM_PasswordEntry newEntry = data.getParcelableExtra("newEntry");

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
    // Custom adapter to display only descriptions
    private class CustomAdapter extends ArrayAdapter<PM_PasswordEntry> {
        private int layoutResource;

        public CustomAdapter(Context context, int resource, List<PM_PasswordEntry> entries) {
            super(context, resource, entries);
            layoutResource = resource;
        }
        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(layoutResource, parent, false);
            }

            PM_PasswordEntry entry = getItem(position);

            if (entry != null) {
                TextView textView = convertView.findViewById(android.R.id.text1);
                textView.setText(entry.getDescription());
            }

            return convertView;
        }
    }

    // Method to show a popup with full details
    private void showDetailsPopup(PM_PasswordEntry entry) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        View popupView = getLayoutInflater().inflate(R.layout.popup_layout, null);
        builder.setView(popupView);

        TextView loginTextView = popupView.findViewById(R.id.popupLoginTextView);
        TextView passwordTextView = popupView.findViewById(R.id.popupPasswordTextView);

        loginTextView.setText("Login: " + entry.getLogin());
        passwordTextView.setText("Password: " + entry.getPassword());

        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Do nothing here, just close the dialog
            }
        });

        builder.show();
    }

}