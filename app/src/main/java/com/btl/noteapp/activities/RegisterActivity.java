package com.btl.noteapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.btl.noteapp.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class RegisterActivity extends AppCompatActivity {

    private TextInputEditText inputSignUpEmail, inputSignUpPass, inputConfirmPass;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mAuth = FirebaseAuth.getInstance();

        inputSignUpEmail = findViewById(R.id.inputSignUpEmail);
        inputSignUpPass = findViewById(R.id.inputSignUpPass);
        inputConfirmPass = findViewById(R.id.inputConfirmPass);
        TextView textSignUp = findViewById(R.id.textSignUp);
        TextView textLogin = findViewById(R.id.textLogin);

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                finish();
            }
        });
    }

    private void registerUser() {
        String email = inputSignUpEmail.getText().toString().trim();
        String password = inputSignUpPass.getText().toString().trim();
        String confirmPassword = inputConfirmPass.getText().toString().trim();

        if (TextUtils.isEmpty(email)) {
            inputSignUpEmail.setError("Email is required.");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            inputSignUpPass.setError("Password is required.");
            return;
        }

        if (!password.equals(confirmPassword)) {
            inputConfirmPass.setError("Passwords do not match.");
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, task -> {
                    if (task.isSuccessful()) {
                        FirebaseUser user = mAuth.getCurrentUser();
                        Toast.makeText(RegisterActivity.this, "Registration successful.", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                        finish();
                    } else {
                        Toast.makeText(RegisterActivity.this, "Registration failed.", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}