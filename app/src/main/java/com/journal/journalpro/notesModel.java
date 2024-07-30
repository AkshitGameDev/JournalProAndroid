package com.journal.journalpro;

public class notesModel {
    String Title;
    String disc;

    public notesModel(){

    }

    public notesModel(String title, String disc) {
        Title = title;
        this.disc = disc;
    }

    public String getTitle() {
        return Title;
    }

    public String getDisc() {
        return disc;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public void setDisc(String disc) {
        this.disc = disc;
    }
}
