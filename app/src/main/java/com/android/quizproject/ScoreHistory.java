package com.android.quizproject;

public class ScoreHistory {
    private String date;
    private int score;
    private int total;

    public ScoreHistory() {}  // Default constructor for Firestore

    public ScoreHistory(String date, int score, int total) {
        this.date = date;
        this.score = score;
        this.total = total;
    }

    public String getDate() {
        return date;
    }

    public int getScore() {
        return score;
    }

    public int getTotal() {
        return total;
    }
}
