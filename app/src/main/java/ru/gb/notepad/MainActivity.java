package ru.gb.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity implements NoticeFragment.Controller, NoticeListFragment.Controller {
    private Notepad notepad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        int id = item.getItemId();
                        switch (id) {
                            case R.id.action_add:
                                Log.d("@@@", "onNavigationItemSelected: add");
                                return true;
                            case R.id.action_settings:
                                Log.d("@@@", "onNavigationItemSelected: settings");
                                return true;
                            case R.id.action_save:
                                Log.d("@@@", "onNavigationItemSelected: save");
                        }
                        return true;
                    }
                });

        notepad = new Notepad();

        notepad.addNotice(new Notice(Notice.generateNewId(), "Магазин", "Купить морковку и картошку", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Будильник", "Не забыть поставить будильник", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Жена", "Не забыть позвонить жене", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Ремонт", "Отремонтировать дверную ручку в детской", Notice.getCurrentDate()));
        notepad.addNotice(new Notice(Notice.generateNewId(), "Уроки", "Дописать шестую домашнюю работу", Notice.getCurrentDate()));
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, NoticeListFragment.newInstance(notepad.getNoticeList()))
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_example, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.action_favorite:
                Toast.makeText(this, "action_favorite", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_main:
                Toast.makeText(this, "action_main", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_search:
                Toast.makeText(this, "action_search", Toast.LENGTH_SHORT).show();
                return true;
            case R.id.action_settings:
                Toast.makeText(this, "action_settings", Toast.LENGTH_SHORT).show();
                return true;
        }
        return true;
    }

    @Override
    public void saveNotice(Notice notice) {
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, NoticeListFragment.newInstance(notepad.getNoticeList()))
                .commit();
    }

    @Override
    public void openNotice(Notice notice, int noticeIndex) {
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;
        if (isLandscape) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_container, NoticeFragment.newInstance(notice))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, NoticeFragment.newInstance(notice))
                    .commit();
        }
    }
}