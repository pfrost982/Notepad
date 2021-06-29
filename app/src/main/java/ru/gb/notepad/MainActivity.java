package ru.gb.notepad;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(
                item -> {
                    int id = item.getItemId();
                    switch (id) {
                        case R.id.action_add:
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
        notepad.setSubscriber(new Runnable() {
            @Override
            public void run() {
                refreshNoticeListFragment();
            }
        });
        refreshNoticeListFragment();
    }

    private void refreshNoticeListFragment() {
        getSupportFragmentManager().popBackStack();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, NoticeListFragment.newInstance(notepad.getNoticeList()))
                .commit();
    }

    @Override
    public void saveNotice(Notice notice) {
        notepad.addNotice(notice);
        refreshNoticeListFragment();
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

    @Override
    public void deleteNotice(Notice notice) {
        new AlertDialog.Builder(this)
                .setTitle(R.string.alert)
                .setMessage(getString(R.string.do_you_want_delete_notice) + notice.getTitle())
                .setCancelable(false)
                .setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        notepad.deleteNotice(notice);
                        refreshNoticeListFragment();
                    }
                })
                .setNegativeButton(R.string.no, null)
                .show();
    }
}