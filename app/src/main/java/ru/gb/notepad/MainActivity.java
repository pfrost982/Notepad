package ru.gb.notepad;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements NoticeFragment.Controller, NoticeListFragment.Controller {
    private Notepad notepad;

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
    public void saveNotice(Notice notice, int noticeIndex) {
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
                    .replace(R.id.detail_container, NoticeFragment.newInstance(noticeIndex, notice.getTitle(),
                            notice.getDescription(), notice.getDateOfCreation(), notice.getDateOfEditing()))
                    .commit();
        } else {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.container, NoticeFragment.newInstance(noticeIndex, notice.getTitle(),
                            notice.getDescription(), notice.getDateOfCreation(), notice.getDateOfEditing()))
                    .commit();
        }
    }
}