package com.android.quizproject;

import java.util.List;

public class Question {
    private String text;
    private List<String> options;
    private String correctAnswer;
    private String topic;
    public Question() {}
    public Question(String text, List<String> options, String correctAnswer, String topic) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.topic = topic;
    }
    public String getText() {
        return text;
    }
    public List<String> getOptions() {
        return options;
    }
    public String getCorrectAnswer() {
        return correctAnswer;
    }
    public String getTopic() {
        return topic;
    }
    public void setText(String text) {
        this.text = text;
    }
    public void setOptions(List<String> options) {
        this.options = options;
    }
    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
}