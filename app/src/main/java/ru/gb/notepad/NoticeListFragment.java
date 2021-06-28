package ru.gb.notepad;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.PopupMenu;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

public class NoticeListFragment extends Fragment {
    private static final String ARG_LIST = "param_list";
    private ArrayList<Notice> noticeList;

    private RecyclerView recyclerView;
    private NotesAdapter adapter;

    public NoticeListFragment() {
    }

    public static NoticeListFragment newInstance(ArrayList<Notice> noticeList) {
        NoticeListFragment fragment = new NoticeListFragment();
        Bundle args = new Bundle();
        args.putParcelableArrayList(ARG_LIST, noticeList);
        fragment.setArguments(args);
        fragment.noticeList = noticeList;
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            noticeList = getArguments().getParcelableArrayList(ARG_LIST);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notice_list, container, false);
        recyclerView = view.findViewById(R.id.recycler_view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        adapter = new NotesAdapter();
        adapter.setOnItemClickListener((notice, cardView) -> {
            PopupMenu popupMenu = new PopupMenu(requireActivity(), cardView);
            popupMenu.inflate(R.menu.popup);
            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()) {
                    case R.id.item_open:
                        getController().openNotice(notice);
                        return true;
                    case R.id.item_delete:
                        getController().deleteNotice(notice);
                        return true;
                }
                return true;
            });
            popupMenu.show();
        });
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        renderList(noticeList);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new IllegalStateException("Activity must implement Contract");
        }
    }

    private void renderList(List<Notice> noticeList) {
        adapter.setData(noticeList);
    }

    private Controller getController() {
        return (Controller) getActivity();
    }

    interface Controller {
        void openNotice(Notice notice);

        void deleteNotice(Notice notice);
    }
}