package com.example.blood_donation_app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final long SPLASH_DELAY = 2000; // Splash screen delay time in milliseconds

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Using a Handler to delay the start of the sign-in activity
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                // Start the sign-in activity after the delay
                Intent intent = new Intent(SplashScreenActivity.this, SignInActivity.class);
                startActivity(intent);
                finish(); // Finish the splash screen activity so the user can't go back to it
            }
        }, SPLASH_DELAY);
    }
}
