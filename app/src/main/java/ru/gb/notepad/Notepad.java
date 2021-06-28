package ru.gb.notepad;

import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Notepad {
    private ArrayList<Notice> noticeList = null;
    private static Notepad notepad = null;
    private static final String NOTICES = "NOTICES";
    private Runnable subscriber;
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();

    private Notepad() {
    }

    public static Notepad getNotepadInstance() {
        if (notepad == null) {
            notepad = new Notepad();
            notepad.noticeList = new ArrayList<>();
        }
        notepad.initNoticeList();
        notepad.db.collection(NOTICES).addSnapshotListener(new EventListener<QuerySnapshot>() {
            @Override
            public void onEvent(@Nullable QuerySnapshot queryDocumentSnapshots, @Nullable FirebaseFirestoreException e) {
                notepad.updatingList(queryDocumentSnapshots);
            }
        });
        return notepad;
    }

    private void initNoticeList() {
        db.collection(NOTICES).get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                updatingList(queryDocumentSnapshots);
            }
        });
    }

    private void updatingList(QuerySnapshot queryDocumentSnapshots) {
        if (queryDocumentSnapshots == null) return;
        noticeList.clear();
        for (QueryDocumentSnapshot queryDocumentSnapshot : queryDocumentSnapshots) {
            noticeList.add(queryDocumentSnapshot.toObject(Notice.class));
        }
        notifySubscriber();
    }

    public void setSubscriber(Runnable subscriber) {
        this.subscriber = subscriber;
    }

    private void notifySubscriber() {
        subscriber.run();
    }

    @Nullable
    private Notice findNoteWithId(String id) {
        for (Notice note : noticeList) {
            if (note.getId().equals(id)) {
                return note;
            }
        }
        return null;
    }

    public ArrayList<Notice> getNoticeList() {
        Collections.sort(noticeList, new Comparator<Notice>() {
            @Override
            public int compare(Notice o1, Notice o2) {
                return (int) (o1.getDateOfCreation() - o2.getDateOfCreation());
            }
        });
        return noticeList;
    }

    public void addNotice(Notice notice) {
        Notice sameNote = findNoteWithId(notice.getId());
        if (sameNote != null) {
            noticeList.remove(sameNote);
        }
        noticeList.add(notice);

        db.collection(NOTICES)
                .document(notice.getId())
                .set(notice);
    }

    public void deleteNotice(Notice notice) {
        Notice sameNote = findNoteWithId(notice.getId());
        if (sameNote != null) {
            noticeList.remove(sameNote);
        }

        db.collection(NOTICES).document(notice.getId()).delete();
    }
}
