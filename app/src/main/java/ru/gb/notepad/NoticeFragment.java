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
    private static final String ARG_NOTICE = "ARG_NOTICE";

    private Notice notice;
    private EditText idEt;
    private EditText titleEt;
    private EditText descriptionEt;
    private EditText dateOfCreationEt;
    private Button saveBt;

    public NoticeFragment() {
    }

    public static NoticeFragment newInstance(Notice notice) {
        NoticeFragment fragment = new NoticeFragment();
        Bundle args = new Bundle();
        args.putParcelable(ARG_NOTICE, notice);
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
            this.notice = getArguments().getParcelable(ARG_NOTICE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_notice, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        idEt = view.findViewById(R.id.id_edit_text);
        titleEt = view.findViewById(R.id.title_edit_text);
        descriptionEt = view.findViewById(R.id.description_edit_text);
        dateOfCreationEt = view.findViewById(R.id.date_of_creation_edit_text);
        saveBt = view.findViewById(R.id.save_button);
        saveBt.setOnClickListener(v -> {
            Controller controller = (Controller) getActivity();
            controller.saveNotice(new Notice(notice.id, titleEt.getText().toString(),
                    descriptionEt.getText().toString(), notice.dateOfCreation));
        });

        idEt.setText(notice.id);
        titleEt.setText(notice.title);
        descriptionEt.setText(notice.description);
        dateOfCreationEt.setText(Long.toString(notice.dateOfCreation));
    }

    public interface Controller {
        void saveNotice(Notice notice);
    }
}