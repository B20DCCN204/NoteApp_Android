package com.btl.noteapp.listeners;

import com.btl.noteapp.model.Note;

public interface NoteListener {
    void onNoteClicked(Note note, int position);
}
