package ru.gb.notepad;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import java.util.Calendar;

public class NoticeFragment extends Fragment {
    private static final String ARG_NOTICE = "ARG_NOTICE";

    private Notice notice;
    private EditText titleEt;
    private EditText descriptionEt;
    private DatePicker datePicker;
    private Button saveBt;
    private Calendar calendar;

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
    public void onAttach(@NonNull Context context) {
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
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        titleEt = view.findViewById(R.id.title_edit_text);
        descriptionEt = view.findViewById(R.id.description_edit_text);
        datePicker = view.findViewById(R.id.date_picker);
        saveBt = view.findViewById(R.id.save_button);
        saveBt.setOnClickListener(v -> {
            Controller controller = (Controller) getActivity();
            controller.saveNotice(new Notice(notice.getId(), titleEt.getText().toString(),
                    descriptionEt.getText().toString(), calendar.getTimeInMillis()));
        });

        titleEt.setText(notice.getTitle());
        descriptionEt.setText(notice.getDescription());
        calendar = Calendar.getInstance();
        calendar.setTimeInMillis(notice.getDateOfCreation());
        datePicker.setCalendarViewShown(false);
        datePicker.setSpinnersShown(true);
        datePicker.init(calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH), new DatePicker.OnDateChangedListener() {
                    @Override
                    public void onDateChanged(DatePicker view, int year,
                                              int monthOfYear, int dayOfMonth) {
                        calendar.set(year, monthOfYear, dayOfMonth);
                    }
                });
    }

    public interface Controller {
        void saveNotice(Notice notice);
    }
}