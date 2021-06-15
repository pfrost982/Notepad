package ru.gb.notepad;

import java.util.ArrayList;

public class Notepad {
    private final ArrayList<Notice> noticeList;

    public Notepad() {
        noticeList = new ArrayList<Notice>();
    }

    public void addNotice(Notice notice) {
        noticeList.add(notice);
    }

    public ArrayList<Notice> getNoticeList() {
        return noticeList;
    }
}
