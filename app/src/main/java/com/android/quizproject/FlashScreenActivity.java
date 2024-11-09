package com.android.quizproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.ImageView;

public class FlashScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_screen);
        ImageView logo = findViewById(R.id.amritaLogo);
        logo.setImageResource(R.drawable.amrita_logo);
        new Handler().postDelayed(() -> {
            startActivity(new Intent(FlashScreenActivity.this, HomeScreenActivity.class));
            finish();
        }, 2000);
    }
}