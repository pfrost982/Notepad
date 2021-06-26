package ru.gb.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements NoticeFragment.Controller, NoticeListFragment.Controller {
    private Notepad notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.action_add:
                            Log.d("@@@", "onNavigationItemSelected: add");
                            openNotice(new Notice(Notice.generateNewId(), "Новая", "Новая", Notice.getCurrentDate()));
                            return true;
                        case R.id.action_settings:
                            Log.d("@@@", "onNavigationItemSelected: settings");
                            return true;
                        case R.id.action_save:
                            Log.d("@@@", "onNavigationItemSelected: save");
                    }
                    return true;
                });

        notepad = new Notepad();
        initNotepad();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, NoticeListFragment.newInstance(notepad.getNoticeList()))
                .commit();
    }

    private void initNotepad() {
        notepad.addNotice(new Notice(Notice.generateNewId(), "Магазин", "Купить морковку и картошку", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Будильник", "Не забыть поставить будильник", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Жена", "Не забыть позвонить жене", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Ремонт", "Отремонтировать дверную ручку в детской", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Уроки", "Дописать шестую домашнюю работу", Notice.getCurrentDate()));
    }

    @Override
    public void saveNotice(Notice notice) {
        notepad.addNotice(notice);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, NoticeListFragment.newInstance(notepad.getNoticeList()))
                .commit();
    }

    @Override
    public void openNotice(Notice notice) {
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        getSupportFragmentManager()
                .beginTransaction()
                .addToBackStack(null)
                .replace(isLandscape ? R.id.detail_container : R.id.container, NoticeFragment.newInstance(notice))
                .commit();
    }
}