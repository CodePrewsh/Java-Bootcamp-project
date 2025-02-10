package com.precious.notes.note.models.responses;

import com.precious.notes.note.models.entities.Note;

public class NoteResponse {
    private Note note;
    private String response;

    public Note getNote(){
        return note;
    }

    public NoteResponse(Note note, String response) {
        this.note = note;
        this.response = response;
    }
    public NoteResponse(Note note){
        this.note = note;

    }

    public NoteResponse(String response){
        this.response = response;
    }

    public void setNote(Note note) {
        this.note = note;
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }
}

