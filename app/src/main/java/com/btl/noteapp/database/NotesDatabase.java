package com.btl.noteapp.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.btl.noteapp.dao.NoteDao;
import com.btl.noteapp.model.Note;

@Database(entities = Note.class, version = 6, exportSchema = false)
public abstract class NotesDatabase extends RoomDatabase {

    private static NotesDatabase notesDatabase;

    public static synchronized NotesDatabase getDatabase(Context context){
        if(notesDatabase == null){
            notesDatabase = Room.databaseBuilder(
                    context,
                    NotesDatabase.class,
                    "notes_db"
            ).build();
        }
        return notesDatabase;
    }

    public abstract NoteDao noteDao();

}
