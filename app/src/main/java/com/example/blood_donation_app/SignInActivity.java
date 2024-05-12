package com.example.blood_donation_app;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {

    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        dbHelper = new DBHelper(this);

        mEmailEditText = findViewById(R.id.etEmail);
        mPasswordEditText = findViewById(R.id.etPassword);

        Button signInButton = findViewById(R.id.signInButton);
        signInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();

                // Check if email and password are empty
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(SignInActivity.this, "Please enter email and password", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Check if user exists in the database and if password matches
                boolean signInSuccess = dbHelper.checkUser(email, password);

                if (signInSuccess) {
                    // Sign-in successful, navigate to MainActivity
                    Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                    startActivity(intent);
                    finish(); // Finish SignInActivity to prevent going back when pressing back button
                } else {
                    // Sign-in failed, display error message
                    Toast.makeText(SignInActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                }
            }
        });

        TextView signUpOption = findViewById(R.id.tvSignUpOption);
        signUpOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Open SignUpActivity when "Sign Up" text is clicked
                Intent intent = new Intent(SignInActivity.this, SignUpActivity.class);
                startActivity(intent);
            }
        });
    }
}
