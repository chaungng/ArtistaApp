package com.example.artistaapp.login;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.artistaapp.R;
import com.example.artistaapp.objects.Artist;
import com.example.artistaapp.objects.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.UUID;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "RegisterActivity";

    // Context
    private Context mContext = RegisterActivity.this;

    // UI Components
    private TextInputEditText editTextEmail, editTextFirstName, editTextLastName, editTextPassword, editTextConfirmPassword;
    private AppCompatButton registerButton;

    // Database
    DatabaseHelper mDatabaseHelper = new DatabaseHelper(mContext);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Log.d(TAG, "onCreate: started");

        editTextEmail = (TextInputEditText) findViewById(R.id.input_email);
        editTextFirstName = (TextInputEditText) findViewById(R.id.input_firstName);
        editTextLastName = (TextInputEditText) findViewById(R.id.input_lastName);
        editTextPassword = (TextInputEditText) findViewById(R.id.input_password);
        editTextConfirmPassword = (TextInputEditText) findViewById(R.id.input_confirm_password);

        registerButton = findViewById(R.id.btn_register);
        registerButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == registerButton) {
            registerUser();
        }
    }

    private void registerUser() {
        String email = editTextEmail.getText().toString().trim();
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();

        // Validate the user input
        checkUserEmail(email);
        checkUserName(firstName, lastName);
        checkUserPassword(password, confirmPassword);

        // Register
        User user = new User();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setEmail(email);
        user.setPassword(password);
        mDatabaseHelper.insertUser(user);

    }

    private void checkUserEmail(String email) {
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required*");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please enter a valid email address!",
                    Toast.LENGTH_LONG);
            toast.show();

            editTextEmail.requestFocus();
            return;
        }
    }

    private void checkUserPassword(String password, String confirmPassword) {
        if (password.isEmpty()) {
            editTextPassword.setError("Password is required*");
            editTextPassword.requestFocus();
            return;
        }

        if (confirmPassword.isEmpty()) {
            editTextConfirmPassword.setError("Please confirm your password!*");
            editTextConfirmPassword.requestFocus();
            return;
        }

        if (!password.equals(confirmPassword)) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Passwords don't match. Please type again!",
                    Toast.LENGTH_LONG);
            toast.show();

            editTextConfirmPassword.setError("Please confirm your password!*");
            editTextConfirmPassword.requestFocus();
            return;
        }
    }

    private void checkUserName(String firstName, String lastName) {
        if (firstName.isEmpty()) {
            editTextFirstName.setError("First Name is required*");
            editTextFirstName.requestFocus();
            return;
        }

        if (lastName.isEmpty()) {
            editTextLastName.setError("Last Name is required*");
            editTextLastName.requestFocus();
            return;
        }
    }
}