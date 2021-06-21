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

        notepad = Notepad.getNotepadInstance();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, NoticeListFragment.newInstance(notepad.getNoticeList()))
                .commit();
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