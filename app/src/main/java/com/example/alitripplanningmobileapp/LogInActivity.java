// Name : Ali Feras Sudqi Hamza
// ID : 1220220
// Sec : Sec #1

package com.example.alitripplanningmobileapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class LogInActivity extends AppCompatActivity {

    public EditText edittextLogInUserName;
    public EditText edittextLogInPassword;
    public Button buttonLogInContinue;
    public TextView textviewLogInContinue;

    public SharedPreferences sharedPreferences;
    public static final String PREF_NAME = "loginPref";
    public static final String KEY_USER = "user";
    public static final String KEY_PASS = "pass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        edittextLogInUserName = findViewById(R.id.edittextLogInUserName);
        edittextLogInPassword = findViewById(R.id.edittextLogInPassword);
        buttonLogInContinue = findViewById(R.id.buttonLogInContinue);
        textviewLogInContinue = findViewById(R.id.textviewLogInContinue);

        sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);

        if(sharedPreferences.contains(KEY_USER) && sharedPreferences.contains(KEY_PASS)) {

            Intent intent = new Intent(LogInActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        buttonLogInContinue.setOnClickListener(v -> {

            String user = edittextLogInUserName.getText().toString();
            String password = edittextLogInPassword.getText().toString();

            if(user.equalsIgnoreCase("ali") && password.equalsIgnoreCase("ali")) {

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(KEY_USER, user);
                editor.putString(KEY_PASS, password);
                editor.apply();

                Toast.makeText(LogInActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                startActivity(new Intent(LogInActivity.this, MainActivity.class));
                finish();
            }

            else {

                Toast.makeText(LogInActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
            }

        });

        textviewLogInContinue.setOnClickListener(v -> {

            Toast.makeText(LogInActivity.this, "Successful", Toast.LENGTH_LONG).show();
            startActivity(new Intent(LogInActivity.this, MainActivity.class));
            finish();

        });

    }

}