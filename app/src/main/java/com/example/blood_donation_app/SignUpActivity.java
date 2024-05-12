package com.example.blood_donation_app;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText mNameEditText;
    private EditText mEmailEditText;
    private EditText mPasswordEditText;
    private EditText mConfirmPasswordEditText;
    private Button mSignUpButton;
    private TextView mSignInOption;
    private DBHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        dbHelper = new DBHelper(this);

        mNameEditText = findViewById(R.id.etName);
        mEmailEditText = findViewById(R.id.etEmail);
        mPasswordEditText = findViewById(R.id.etPassword);
        mConfirmPasswordEditText = findViewById(R.id.etConfirmPassword);
        mSignUpButton = findViewById(R.id.btnSignUp);
        mSignInOption = findViewById(R.id.tvSignInOption);

        mSignUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mNameEditText.getText().toString().trim();
                String email = mEmailEditText.getText().toString().trim();
                String password = mPasswordEditText.getText().toString().trim();
                String confirmPassword = mConfirmPasswordEditText.getText().toString().trim();

                if (password.equals(confirmPassword)) {
                    // Passwords match, proceed with sign-up
                    boolean signUpSuccess = dbHelper.insertUserData(name, email, password);

                    if (signUpSuccess) {
                        // Show success message
                        Toast.makeText(SignUpActivity.this, "Sign-up successful", Toast.LENGTH_SHORT).show();
                        // Optionally, you can navigate to another activity or perform other actions here
                    } else {
                        // Show error message if sign-up failed
                        Toast.makeText(SignUpActivity.this, "Sign-up failed", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    // Passwords do not match, show error message
                    Toast.makeText(SignUpActivity.this, "Passwords do not match", Toast.LENGTH_SHORT).show();
                }
            }
        });

        mSignInOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SignUpActivity.this, SignInActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }
}
