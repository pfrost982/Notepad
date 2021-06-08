package ru.gb.notepad;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

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
        return inflater.inflate(R.layout.fragment_notice_list, null);
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
        Button button = new Button(getContext());
        button.setText(notice.getTitle());
        button.setOnClickListener(v -> {
            ((Controller) getActivity()).openNotice(notice, index);
        });
        linearLayout.addView(button);
    }

    public interface Controller {
        void openNotice(Notice notice, int noticeIndex);
    }

}