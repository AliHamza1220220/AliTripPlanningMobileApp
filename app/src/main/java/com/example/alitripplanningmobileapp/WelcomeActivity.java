// Name : Ali Feras Sudqi Hamza
// ID : 1220220
// Sec : Sec #1

package com.example.alitripplanningmobileapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class WelcomeActivity extends AppCompatActivity {

    public Button buttonWelcomeContinue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        buttonWelcomeContinue = findViewById(R.id.buttonWelcomeContinue);

        buttonWelcomeContinue.setOnClickListener(v -> {

            Intent intent = new Intent(WelcomeActivity.this, LogInActivity.class);
            startActivity(intent);
            finish();
        });

    }

}