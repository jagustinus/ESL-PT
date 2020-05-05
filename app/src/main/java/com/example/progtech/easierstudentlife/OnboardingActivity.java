package com.example.progtech.easierstudentlife;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class OnboardingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboarding);

        Intent i = new Intent(OnboardingActivity.this, HomeActivity.class);
        startActivity(i);
        finish();
    }
}
