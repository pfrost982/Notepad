package ru.gb.notepad;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class Notepad {
    private ArrayList<Notice> noticeList = null;
    private static Notepad notepad = null;

    private Notepad() {
    }

    public static Notepad getNotepadInstance() {
        if (notepad == null) {
            notepad = new Notepad();
            notepad.noticeList = new ArrayList<>();
            initNotepad();
        }
        return notepad;
    }

    private static void initNotepad() {
        notepad.addNotice(new Notice(Notice.generateNewId(), "Магазин", "Купить морковку и картошку", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Будильник", "Не забыть поставить будильник", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Жена", "Не забыть позвонить жене", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Ремонт", "Отремонтировать дверную ручку в детской", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Уроки", "Дописать шестую домашнюю работу", Notice.getCurrentDate()));
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

    public void deleteNotice(Notice notice) {
        Notice sameNote = findNoteWithId(notice.id);
        if (sameNote != null) {
            noticeList.remove(sameNote);
        }
    }
}
