package com.example.loginactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

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

    }
}