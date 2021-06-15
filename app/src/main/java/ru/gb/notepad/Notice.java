package ru.gb.notepad;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;

public class Notice implements Parcelable {
    private String title;
    private String description;
    private String dateOfCreation;
    private String dateOfEditing;

    public Notice(String title, String description) {
        this.title = title;
        this.description = description;
        this.dateOfCreation = Calendar.getInstance().getTime().toString();
        this.dateOfEditing = Calendar.getInstance().getTime().toString();
    }

    protected Notice(Parcel in) {
        title = in.readString();
        description = in.readString();
        dateOfCreation = in.readString();
        dateOfEditing = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(description);
        dest.writeString(dateOfCreation);
        dest.writeString(dateOfEditing);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Notice> CREATOR = new Creator<Notice>() {
        @Override
        public Notice createFromParcel(Parcel in) {
            return new Notice(in);
        }

        @Override
        public Notice[] newArray(int size) {
            return new Notice[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getDateOfCreation() {
        return dateOfCreation;
    }

    public String getDateOfEditing() {
        return dateOfEditing;
    }
}
