package com.btl.noteapp.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.btl.noteapp.model.Note;

import java.util.List;

@Dao
public interface NoteDao {

    @Query("SELECT * FROM notes WHERE userId = :userId ORDER BY id DESC")
    List<Note> getAllNotes(String userId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertNote(Note note);

    @Delete
    void deleteNote(Note note);

}
