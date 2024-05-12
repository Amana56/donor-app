package com.example.blood_donation_app;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize your views
        Button btnDonateBlood = findViewById(R.id.btnDonateBlood);
        Button btnRequestBlood = findViewById(R.id.btnRequestBlood);
        Button btnViewDonors = findViewById(R.id.btnViewDonors);
        Button btnViewRequests = findViewById(R.id.btnViewRequests);

        // Set click listener for Donate Blood button
        btnDonateBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Donate Blood button click
                Intent intent = new Intent(MainActivity.this, DonationActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for Request Blood button
        btnRequestBlood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle Request Blood button click
                Intent intent = new Intent(MainActivity.this, RequestActivity.class);
                startActivity(intent);
            }
        });

        // Set click listener for View Donors button
        btnViewDonors.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle View Donors button click
                Intent intent = new Intent(MainActivity.this, DonorViewActivity.class);
                startActivity(intent);
            }
        });

        btnViewRequests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RequestViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
