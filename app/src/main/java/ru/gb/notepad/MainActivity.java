package ru.gb.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NoticeFragment.Controller, NoticeListFragment.Controller {
    private Notepad notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notepad = new Notepad();
        notepad.addNotice(new Notice("111", "111"));
        notepad.addNotice(new Notice("222", "222"));
        notepad.addNotice(new Notice("333", "333"));
        notepad.addNotice(new Notice("444", "444"));
        notepad.addNotice(new Notice("555", "555"));

        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.container, NoticeListFragment.newInstance(notepad.getNoticeList()))
                .commit();
    }

    @Override
    public void saveNotice(Notice notice, int noticeIndex) {

    }

    @Override
    public void openNotice(Notice notice, int noticeIndex) {

    }
}