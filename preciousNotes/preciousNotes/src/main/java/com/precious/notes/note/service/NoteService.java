package com.precious.notes.note.service;

import com.precious.notes.note.models.entities.Note;
import com.precious.notes.note.models.entities.User;
import com.precious.notes.note.models.responses.NoteResponse;
import com.precious.notes.note.repository.NoteRepository;
import com.precious.notes.note.repository.UserRepository;
import com.precious.notes.note.security.service.JwtService;
import com.precious.notes.note.tools.utils.SpliteToken;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NoteService {
    private final UserRepository userRepository;
    private final NoteRepository noteRepository;
    private final JwtService jwtService;
    private final SpliteToken spliteToken;

    public NoteService(UserRepository userRepository, NoteRepository noteRepository, JwtService jwtService, SpliteToken spliteToken) {
        this.userRepository = userRepository;
        this.noteRepository = noteRepository;
        this.jwtService = jwtService;
        this.spliteToken = spliteToken;
    }

    public NoteResponse createNote(String token, Note request) {
        if (token == null) {
            return new NoteResponse("You are not logged in");
        }
        if (request.getTitle() == null || request.getTitle().isEmpty()) {

            return new NoteResponse("Please enter Tittle!");
        }

        if (request.getContent() == null || request.getContent().isEmpty()) {

            return new NoteResponse("Please enter Content!");
        }

        Note note = new Note();
        String filteredToken = spliteToken.split(token);
        String username = jwtService.extractUsername(filteredToken);

        User user = userRepository.findByUsername(username).orElseThrow();
        try {
            note.setTitle(request.getTitle());
            note.setContent(request.getContent());
            note.setUser(user);
            noteRepository.save(note);

        } catch (Exception e) {
            return new NoteResponse("Something went wrong");
        }

        return new NoteResponse(note, "Note created successfully");
    }

    public List<Note> getNotes(String token) {
        String filteredToken = spliteToken.split(token);
        String username = jwtService.extractUsername(filteredToken);
        User user = userRepository.findByUsername(username).orElseThrow();
        Integer userId = user.getId();
        List<Note> notes = noteRepository.findByUserId(userId);
        return notes;
    }


    public NoteResponse deleteNote(Integer noteId) {
        if (noteId == null) {
            return new NoteResponse("Please enter Note ID");
        }
        Note note = noteRepository.findById(noteId).orElseThrow();
        if (noteId == null) {
            return new NoteResponse("Note not found! Please try again!");
        }
        try {
            noteRepository.deleteById(noteId);
        } catch (Exception e) {
            return new NoteResponse("Something went wrong!");
        }

        return new NoteResponse(note, "note deleted successfully!");
    }
}
