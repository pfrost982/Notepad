package ru.gb.notepad;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

public class NoteViewHolder extends RecyclerView.ViewHolder {
    private final TextView titleTextView;
    private final TextView dateTextView;
    private final CardView cardView;
    private Notice notice;

    public NoteViewHolder(@NonNull ViewGroup parent, @Nullable ru.gb.notepad.NotesAdapter.OnItemClickListener clickListener) {
        super(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note, parent, false));
        cardView = (CardView) itemView;
        titleTextView = itemView.findViewById(R.id.title_text_view);
        dateTextView = itemView.findViewById(R.id.date_text_view);
        cardView.setOnClickListener(v -> {
            if (clickListener != null) {
                clickListener.onItemClick(notice, cardView);
            }
        });
    }

    public void bind(Notice notice) {
        this.notice = notice;
        titleTextView.setText(notice.title);
        dateTextView.setText(Long.toString(notice.dateOfCreation));
    }
}