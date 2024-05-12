package com.example.blood_donation_app;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RequestActivity extends AppCompatActivity {

    private DBHelper dbHelper;

    private EditText etName, etContactNumber, etHospitalName, etAdditionalNotes;
    private Spinner spinnerBloodType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);

        // Initialize views
        etName = findViewById(R.id.etName);
        etContactNumber = findViewById(R.id.etContactNumber);
        etHospitalName = findViewById(R.id.etHospitalName);
        etAdditionalNotes = findViewById(R.id.etAdditionalNotes);
        spinnerBloodType = findViewById(R.id.spinnerBloodType);
        Button btnSubmit = findViewById(R.id.btnSubmit);

        // Initialize DBHelper
        dbHelper = new DBHelper(this);

        btnSubmit.setOnClickListener(v -> {
            // Get data from EditText and Spinner
            String name = etName.getText().toString().trim();
            String contactNumber = etContactNumber.getText().toString().trim();
            String hospitalName = etHospitalName.getText().toString().trim();
            String additionalNotes = etAdditionalNotes.getText().toString().trim();
            String bloodType = spinnerBloodType.getSelectedItem().toString();

            // Insert data into the database
            boolean inserted = dbHelper.insertBloodRequestData(name, bloodType, contactNumber, hospitalName, additionalNotes);

            if (inserted) {
                // Display success message
                Toast.makeText(this, "Request submitted successfully", Toast.LENGTH_SHORT).show();
                // Optionally, you can navigate to another activity or perform any other action here
            } else {
                // Display failure message
                Toast.makeText(this, "Failed to submit request", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
