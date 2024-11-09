package com.android.quizproject;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class HomeScreenActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        findViewById(R.id.historyButton).setOnClickListener(view -> {
            startActivity(new Intent(HomeScreenActivity.this, HistoryScreenActivity.class));
        });
        CardView natureCard = findViewById(R.id.cardNature);
        CardView scienceCard = findViewById(R.id.cardScience);
        CardView csCard = findViewById(R.id.cardCS);
        natureCard.setOnClickListener(view -> startQuiz("Nature"));
        scienceCard.setOnClickListener(view -> startQuiz("Science"));
        csCard.setOnClickListener(view -> startQuiz("ComputerScience"));
    }
    private void startQuiz(String topic) {
        Intent intent = new Intent(HomeScreenActivity.this, QuizScreenActivity.class);
        intent.putExtra("topic", topic);
        startActivity(intent);
    }
}