package com.precious.notes.note.controller;

import com.precious.notes.note.models.entities.Note;
import com.precious.notes.note.models.responses.NoteResponse;
import com.precious.notes.note.service.NoteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Note", description = "Note Taking APIs")
@RestController
@RequestMapping("/notes")
public class NoteController {

    private final NoteService noteService;

    public NoteController(NoteService noteService) {
        this.noteService = noteService;
    }

    @Operation(
            summary = "Add Note",
            description = "Add Note by Populating all fields",
            tags = {"Notes", "post"}
    )

    @ApiResponses({
            @ApiResponse(responseCode = "200", content = {@Content(schema = @Schema(implementation = Note.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "404", content = {@Content(schema = @Schema())}),
            @ApiResponse(responseCode = "500", content = {@Content(schema = @Schema())})
    })
    @SecurityRequirement(name = "Bearer Authentication")
    @PostMapping("/create")
    public ResponseEntity<NoteResponse> createNote(
            @RequestHeader(name = "Authorization") String token,
            @RequestBody Note note
    ) {
        return new ResponseEntity<>(noteService.createNote(token, note), HttpStatus.CREATED);
    }


    @Operation(
            summary = "Get list of notes",
            description = "Get a list of all user notes by checking token and find all notes in database",
            tags = { "Notes", "get" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Note.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @SecurityRequirement(name="Bearer Authentication")
    @GetMapping("/all")
    public ResponseEntity<List<Note>> getAllNotes(
            @RequestHeader(name="Authorization") String token
    ) {
        return ResponseEntity.ok(noteService.getNotes(token));
    }





    @Operation(
            summary = "Delete note",
            description = "Delete note by using specified note id",
            tags = { "Notes", "delete" })
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Note.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema()) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema()) }) })
    @SecurityRequirement(name="Bearer Authentication")
    @DeleteMapping("/{id}")
    public ResponseEntity<NoteResponse> deleteNote(
            @PathVariable Integer id
    ){
        return ResponseEntity.ok(noteService.deleteNote(id));
    }


}
