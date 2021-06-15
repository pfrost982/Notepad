package ru.gb.notepad;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

public class NoticeFragment extends Fragment {
    private static final String ARG_NOTICE_INDEX = "noticeIndex";
    private static final String ARG_TITLE = "title";
    private static final String ARG_DESCRIPTION = "description";
    private static final String ARG_DATE_OF_CREATION = "dateOfCreation";
    private static final String ARG_DATE_OF_EDITING = "dateOfEditing";

    private int noticeIndex;
    private String title;
    private String description;
    private String dateOfCreation;
    private String dateOfEditing;

    private EditText titleEt;
    private EditText descriptionEt;
    private EditText dateOfCreationEt;
    private EditText dateOfEditingEt;
    private Button saveBt;

    public NoticeFragment() {
    }

    public static NoticeFragment newInstance(int noticeIndex, String title, String description,
                                             String dateOfCreation, String dateOfEditing) {
        NoticeFragment fragment = new NoticeFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_NOTICE_INDEX, noticeIndex);
        args.putString(ARG_TITLE, title);
        args.putString(ARG_DESCRIPTION, description);
        args.putString(ARG_DATE_OF_CREATION, dateOfCreation);
        args.putString(ARG_DATE_OF_EDITING, dateOfEditing);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (!(context instanceof Controller)) {
            throw new RuntimeException("Activity must implement NoticeFragment.Controller");
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            noticeIndex = getArguments().getInt(ARG_NOTICE_INDEX);
            title = getArguments().getString(ARG_TITLE);
            description = getArguments().getString(ARG_DESCRIPTION);
            dateOfCreation = getArguments().getString(ARG_DATE_OF_CREATION);
            dateOfEditing = getArguments().getString(ARG_DATE_OF_EDITING);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice, null);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEt = view.findViewById(R.id.title_edit_text);
        descriptionEt = view.findViewById(R.id.description_edit_text);
        dateOfCreationEt = view.findViewById(R.id.date_of_creation_edit_text);
        dateOfEditingEt = view.findViewById(R.id.date_of_editing_edit_text);
        saveBt = view.findViewById(R.id.save_button);
        saveBt.setOnClickListener(v -> {
            Controller controller = (Controller) getActivity();
            controller.saveNotice(new Notice(titleEt.getText().toString(),
                    descriptionEt.getText().toString()), noticeIndex);
        });


        titleEt.setText(title);
        descriptionEt.setText(description);
        dateOfCreationEt.setText(dateOfCreation);
        dateOfEditingEt.setText(dateOfEditing);
    }

    public interface Controller {
        void saveNotice(Notice notice, int noticeIndex);
    }
}