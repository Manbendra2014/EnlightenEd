package com.android.quizproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    private final Context context;
    private final List<ScoreHistory> historyList;
    public HistoryAdapter(Context context, List<ScoreHistory> historyList) {
        this.context = context;
        this.historyList = historyList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.history_item, parent, false);
        return new ViewHolder(view);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ScoreHistory history = historyList.get(position);
        holder.dateText.setText(history.getDate());
        holder.scoreText.setText("Score: " + history.getScore() + "/" + history.getTotal());
    }
    @Override
    public int getItemCount() {
        return historyList.size();
    }
    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView dateText, scoreText;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            dateText = itemView.findViewById(R.id.dateText);
            scoreText = itemView.findViewById(R.id.scoreText);
        }
    }
}