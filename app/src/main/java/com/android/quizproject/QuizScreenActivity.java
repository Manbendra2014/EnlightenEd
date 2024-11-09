package com.android.quizproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import java.util.List;

public class QuizScreenActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private int score = 0;
    private int questionIndex = 0;
    private List<Question> questions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        db = FirebaseFirestore.getInstance();
        String topic = getIntent().getStringExtra("topic");
        loadQuestions(topic);
    }

    private void loadQuestions(String topic) {
        db.collection("questions")
                .whereEqualTo("topic", topic)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    questions = queryDocumentSnapshots.toObjects(Question.class);
                    displayQuestion();
                });
    }

    private void displayQuestion() {
        if (questionIndex < questions.size()) {
            Question question = questions.get(questionIndex);
            TextView questionText = findViewById(R.id.questionText);
            questionText.setText(question.getText());
        } else {
            Intent intent = new Intent(QuizScreenActivity.this, ScoreSummaryActivity.class);
            intent.putExtra("score", score);
            startActivity(intent);
        }
    }
}
