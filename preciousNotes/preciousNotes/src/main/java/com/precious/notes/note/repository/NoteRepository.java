package com.precious.notes.note.repository;

import com.precious.notes.note.models.entities.Note;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NoteRepository extends JpaRepository<Note, Integer> {
    List<Note> findByUserId(Integer userId);
}
