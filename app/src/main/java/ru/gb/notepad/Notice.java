package ru.gb.notepad;

import java.util.Calendar;

public class Notice {
    private StringBuilder title;
    private StringBuilder description;
    private Calendar dateOfCreation;
    private Calendar dateOfEditing;

    public Notice(String title, String description) {
        this.title = new StringBuilder(title);
        this.description = new StringBuilder(description);
        this.dateOfCreation = Calendar.getInstance();
        this.dateOfEditing = Calendar.getInstance();
    }

    public StringBuilder getTitle() {
        return title;
    }

    public StringBuilder getDescription() {
        return description;
    }

    public Calendar getDateOfCreation() {
        return dateOfCreation;
    }

    public Calendar getDateOfEditing() {
        return dateOfEditing;
    }

    public int getCreationYear() {
        return dateOfCreation.get(Calendar.YEAR);
    }

    public int getCreationMonth() {
        return dateOfCreation.get(Calendar.MONTH);
    }

    public int getCreationDay() {
        return dateOfCreation.get(Calendar.DAY_OF_MONTH);
    }

    public int getEditingYear() {
        return dateOfEditing.get(Calendar.YEAR);
    }

    public int getEditingMonth() {
        return dateOfEditing.get(Calendar.MONTH);
    }

    public int getEditingDay() {
        return dateOfEditing.get(Calendar.DAY_OF_MONTH);
    }
}
