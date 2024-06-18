package com.example.journalpro;

public class journalModel {
    String title;
    String description;

    public journalModel(String title, String description) {
        this.title = title;
        this.description = description;
    }
    public String getTitle() {
        return title;
    }
    public String getDescription() {
        return description;
    }
}
