package ru.gb.notepad;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Notepad {
    private final ArrayList<Notice> noticeList;

    public Notepad() {
        noticeList = new ArrayList<Notice>();
    }

    @Nullable
    private Notice findNoteWithId(String id) {
        for (Notice note : noticeList) {
            if (note.id.equals(id)) {
                return note;
            }
        }
        return null;
    }

    public ArrayList<Notice> getNoticeList() {
        return noticeList;
    }

    public void addNotice(Notice newNote) {
        Notice sameNote = findNoteWithId(newNote.id);
        if (sameNote != null) {
            noticeList.remove(sameNote);
        }
        noticeList.add(newNote);
    }


}
