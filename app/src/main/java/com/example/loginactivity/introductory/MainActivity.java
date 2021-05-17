package com.example.loginactivity.introductory;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.example.loginactivity.Authentication.Login;
import com.example.loginactivity.R;

public class MainActivity extends AppCompatActivity {
    ImageView logo;
    LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logo = findViewById(R.id.logo);
        lottieAnimationView = findViewById(R.id.animation);

        logo.animate().translationY(1600).setDuration(1000).setStartDelay(7000);
        lottieAnimationView.animate().translationY(1600).setDuration(1500).setStartDelay(7000);

        startActivity(new Intent(getApplicationContext(), Login.class));
        finish();

    }
}