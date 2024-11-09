package com.android.quizproject;

import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import java.util.ArrayList;
import java.util.List;

public class HistoryScreenActivity extends AppCompatActivity {
    private FirebaseFirestore db;
    private List<ScoreHistory> historyList;
    private HistoryAdapter historyAdapter;
    private ListView historyListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history_screen);

        db = FirebaseFirestore.getInstance();
        historyListView = findViewById(R.id.historyListView);

        historyList = new ArrayList<>();
        historyAdapter = new HistoryAdapter(this, historyList);
        historyListView.setAdapter(historyAdapter);

        loadHistory();
    }

    private void loadHistory() {
        db.collection("scoreHistory")
                .get()
                .addOnSuccessListener(queryDocumentSnapshots -> {
                    historyList.clear();
                    for (QueryDocumentSnapshot document : queryDocumentSnapshots) {
                        ScoreHistory scoreHistory = document.toObject(ScoreHistory.class);
                        historyList.add(scoreHistory);
                    }
                    historyAdapter.notifyDataSetChanged();
                })
                .addOnFailureListener(e -> Toast.makeText(this, "Failed to load history.", Toast.LENGTH_SHORT).show());
    }
}
