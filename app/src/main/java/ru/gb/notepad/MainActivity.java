package ru.gb.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NoticeFragment.Controller, NoticeListFragment.Controller {
    private Notepad notepad;
    private NoticeListFragment noticeListFragment;
    private NoticeFragment noticeFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        notepad = new Notepad();
        notepad.addNotice(new Notice("Магазин", "Купить морковку и картошку"));
        notepad.addNotice(new Notice("Будильник", "Не забыть поставить будильник"));
        notepad.addNotice(new Notice("Жена", "Не забыть позвонить жене"));
        notepad.addNotice(new Notice("Ремонт", "Отремонтировать дверную ручку в детской"));
        notepad.addNotice(new Notice("Уроки", "Дописать шестую домашнюю работу"));
        noticeListFragment = NoticeListFragment.newInstance(notepad.getNoticeList());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, noticeListFragment)
                .commit();
    }

    @Override
    public void saveNotice(Notice notice, int noticeIndex) {
        noticeListFragment = NoticeListFragment.newInstance(notepad.getNoticeList());
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, noticeListFragment)
                .commit();
    }

    @Override
    public void openNotice(Notice notice, int noticeIndex) {
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        noticeFragment = NoticeFragment.newInstance(noticeIndex, notice.getTitle(),
                notice.getDescription(), notice.getDateOfCreation(), notice.getDateOfEditing());
        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, noticeFragment)
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, noticeFragment)
                    .commit();
        }
    }
}