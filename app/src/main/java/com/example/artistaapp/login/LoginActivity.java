package com.example.artistaapp.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.example.artistaapp.R;
import com.example.artistaapp.home.HomeActivity;
import com.example.artistaapp.love.LoveActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.w3c.dom.Text;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";

    // For the layout
    private Context mContext;
    private EditText mEmail, mPassword;
    private TextView mTextLinkSignUp;
    private AppCompatButton mLoginButton;

    // Database helper to check password
    DatabaseHelper mDatabaseHelper;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Context
        mContext = LoginActivity.this;

        // Database
        mDatabaseHelper = new DatabaseHelper(mContext);

        // Login UI
        mEmail = (EditText) findViewById(R.id.login_email);
        mPassword = (EditText) findViewById(R.id.login_password);
        mTextLinkSignUp = (TextView) findViewById(R.id.textlink_signup);
        mLoginButton = findViewById(R.id.btn_login);

        // Setting up listener
        mTextLinkSignUp.setOnClickListener(this);
        mLoginButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v == mTextLinkSignUp) {
            startActivity(new Intent(mContext, RegisterActivity.class));
        }

        if (v == mLoginButton) {
            Log.d(TAG, "onClick: attempting to login");
            String email = mEmail.getText().toString().trim();
            String password = mPassword.getText().toString().trim();
            signIn(email, password);
        }
    }

    private void signIn(String email, String password) {
        String correctPassword = mDatabaseHelper.searchPassword(email);
        if (password.equals(correctPassword)) {
            Intent intent = new Intent(mContext, HomeActivity.class);
            intent.putExtra("email", email);
            startActivity(intent);
        } else {
            Toast toast = Toast.makeText(mContext,
                    "Email and password don't match. Please type again!",
                    Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
