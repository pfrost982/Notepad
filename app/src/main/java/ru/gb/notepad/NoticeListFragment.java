package ru.gb.notepad;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.util.ArrayList;

public class NoticeListFragment extends Fragment {
    private static final String ARG_LIST = "param_list";
    private ArrayList<Notice> noticeList;
    private LinearLayout linearLayout;

    public NoticeListFragment() {
    }

    public static NoticeListFragment newInstance(ArrayList<Notice> noticeList) {
        NoticeListFragment fragment = new NoticeListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST, noticeList);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new RuntimeException("Activity must implement NoticeListFragment.Controller");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            noticeList = getArguments().getParcelableArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        linearLayout = view.findViewById(R.id.linear_id);

        for (int i = 0; i < noticeList.size(); i++) {
            addNoticeToList(noticeList.get(i), i);
        }
    }

    private void addNoticeToList(Notice notice, int index) {
        Button button = new MaterialButton(getContext());
        button.setText(notice.title);
        Activity activity = requireActivity();
        button.setOnClickListener(v -> {
            PopupMenu popupMenu = new PopupMenu(activity, button);
            popupMenu.inflate(R.menu.popup);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                int id = menuItem.getItemId();
                switch (id) {
                    case R.id.item1_popup:
                        Toast.makeText(getContext(), "Почти удалил", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item2_popup:
                        Toast.makeText(getContext(), "Почти отредактировал", Toast.LENGTH_SHORT).show();
                        return true;
                    case R.id.item3_popup:
                        ((Controller) getActivity()).openNotice(notice, index);
                        return true;
                }
                return true;
            });
            popupMenu.show();
        });
        linearLayout.addView(button);
    }

    public interface Controller {
        void openNotice(Notice notice, int noticeIndex);
    }

}