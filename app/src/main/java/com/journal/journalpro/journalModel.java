package com.journal.journalpro;

public class journalModel {
    private String Title;
    private String disc;

    // Default constructor required for calls to DataSnapshot.getValue(journalModel.class)
    public journalModel() {
    }

    public journalModel(String Title, String disc) {
        this.Title = Title;
        this.disc = disc;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String Title) {
        this.Title = Title;
    }

    public String getDisc() {
        return disc;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
}
