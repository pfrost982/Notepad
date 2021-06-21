package ru.gb.notepad;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Calendar;
import java.util.UUID;

public class Notice implements Parcelable {
    public final String id;
    public final String title;
    public final String description;
    public final long dateOfCreation;

    public Notice(String id, String title, String description, long dateOfCreation) {
        this.title = title;
        this.description = description;
        this.dateOfCreation = dateOfCreation;
    }

    protected Notice(Parcel in) {
        id = in.readString();
        title = in.readString();
        description = in.readString();
        dateOfCreation = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(id);
        dest.writeString(title);
        dest.writeString(description);
        dest.writeLong(dateOfCreation);
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

    public static String generateNewId() {
        return UUID.randomUUID().toString();
    }

    public static long getCurrentDate() {
        return Calendar.getInstance().getTimeInMillis();
    }
}
