package com.example.blood_donation_app;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DonationActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private EditText etName, etAge, etWeight, etContactNumber;
    private Spinner spinnerBloodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donation);

        // Initialize views
        etName = findViewById(R.id.etName);
        etAge = findViewById(R.id.etAge);
        etWeight = findViewById(R.id.etWeight);
        etContactNumber = findViewById(R.id.etContactNumber);
        spinnerBloodType = findViewById(R.id.spinnerBloodType);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        btnSubmit.setOnClickListener(v -> {
            // Get data from EditText and Spinner
            String name = etName.getText().toString().trim();
            String age = etAge.getText().toString().trim();
            String weight = etWeight.getText().toString().trim();
            String contactNumber = etContactNumber.getText().toString().trim();
            String bloodType = spinnerBloodType.getSelectedItem().toString();

            // Insert data into the database
            insertData(name, age, weight, contactNumber, bloodType);

            // Display success message
            Toast.makeText(this, "Data saved successfully", Toast.LENGTH_SHORT).show();
        });
    }

    private void insertData(String name, String age, String weight, String contactNumber, String bloodType) {
        // Get a writable database
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a ContentValues object to store column names and values
        ContentValues contentValues = new ContentValues();
        contentValues.put(DBHelper.BloodDonationEntry.COLUMN_NAME, name);
        contentValues.put(DBHelper.BloodDonationEntry.COLUMN_AGE, age);
        contentValues.put(DBHelper.BloodDonationEntry.COLUMN_WEIGHT, weight);
        contentValues.put(DBHelper.BloodDonationEntry.COLUMN_CONTACT_NUMBER, contactNumber);
        contentValues.put(DBHelper.BloodDonationEntry.COLUMN_BLOOD_TYPE, bloodType);

        // Insert data into the database
        long result = db.insert(DBHelper.BloodDonationEntry.TABLE_NAME, null, contentValues);

        // Check if data is inserted successfully
        if (result == -1) {
            // Failed to insert data
            Toast.makeText(this, "Failed to save data", Toast.LENGTH_SHORT).show();
        }

        // Close the database connection
        db.close();
    }
}
