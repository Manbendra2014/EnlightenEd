package com.android.quizproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScoreSummaryActivity extends AppCompatActivity {
    private TextView scoreSummaryText;
    private Button playAgainButton, viewHistoryButton;
    private FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_summary);
        scoreSummaryText = findViewById(R.id.scoreSummaryText);
        playAgainButton = findViewById(R.id.playAgainButton);
        viewHistoryButton = findViewById(R.id.viewHistoryButton);
        db = FirebaseFirestore.getInstance();
        int score = getIntent().getIntExtra("score", 0);
        int total = getIntent().getIntExtra("total", 0);
        scoreSummaryText.setText("You scored " + score + " out of " + total);
        saveScoreToHistory(score, total);
        playAgainButton.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreSummaryActivity.this, HomeScreenActivity.class);
            startActivity(intent);
            finish();
        });
        viewHistoryButton.setOnClickListener(v -> {
            Intent intent = new Intent(ScoreSummaryActivity.this, HistoryScreenActivity.class);
            startActivity(intent);
        });
    }
    private void saveScoreToHistory(int score, int total) {
        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());
        ScoreHistory scoreHistory = new ScoreHistory(date, score, total);
        db.collection("scoreHistory")
                .add(scoreHistory)
                .addOnSuccessListener(documentReference -> {})
                .addOnFailureListener(e -> {});
    }
}