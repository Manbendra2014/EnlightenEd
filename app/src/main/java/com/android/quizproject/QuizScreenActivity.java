package com.android.quizproject;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class QuizScreenActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private List<Question> questions = new ArrayList<>();
    private int score = 0;
    private int questionIndex = 0;
    private CountDownTimer timer;
    private TextView questionText, scoreText, timerText;
    private Button option1, option2, option3, option4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_screen);

        db = FirebaseFirestore.getInstance();

        questionText = findViewById(R.id.questionText);
        scoreText = findViewById(R.id.scoreText);
        timerText = findViewById(R.id.timerText);
        option1 = findViewById(R.id.option1);
        option2 = findViewById(R.id.option2);
        option3 = findViewById(R.id.option3);
        option4 = findViewById(R.id.option4);

        String topic = getIntent().getStringExtra("topic");
        loadQuestions(topic);
    }

    private void loadQuestions(String topic) {
        db.collection("questions")
                .whereEqualTo("topic", topic)
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        Question question = document.toObject(Question.class);
                        questions.add(question);
                    }
                    Collections.shuffle(questions);  // Shuffle questions to get random order
                    if (questions.size() > 5) {
                        questions = questions.subList(0, 5); // Limit to 5 random questions
                    }
                    displayQuestion();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to load questions.", Toast.LENGTH_SHORT).show());
    }

    private void displayQuestion() {
        if (questionIndex < questions.size()) {
            Question question = questions.get(questionIndex);
            questionText.setText(question.getText());
            option1.setText(question.getOptions()[0]);
            option2.setText(question.getOptions()[1]);
            option3.setText(question.getOptions()[2]);
            option4.setText(question.getOptions()[3]);

            setOptionClickListeners(question);

            startTimer();
        } else {
            endQuiz();
        }
    }

    private void startTimer() {
        if (timer != null) timer.cancel();
        timer = new CountDownTimer(15000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("Time: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                questionIndex++;
                displayQuestion();
            }
        }.start();
    }

    private void setOptionClickListeners(Question question) {
        View.OnClickListener listener = view -> {
            timer.cancel();
            Button selectedOption = (Button) view;
            if (selectedOption.getText().toString().equals(question.getCorrectAnswer())) {
                score++;
                scoreText.setText("Score: " + score);
            }
            questionIndex++;
            displayQuestion();
        };

        option1.setOnClickListener(listener);
        option2.setOnClickListener(listener);
        option3.setOnClickListener(listener);
        option4.setOnClickListener(listener);
    }

    private void endQuiz() {
        Intent intent = new Intent(QuizScreenActivity.this, ScoreSummaryActivity.class);
        intent.putExtra("score", score);
        intent.putExtra("total", questions.size());
        startActivity(intent);
        finish();
    }
}
