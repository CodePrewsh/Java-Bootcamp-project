package com.precious.notes.note.models.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.util.Date;

@Entity
@Table(name = "notes")

public class Note {
    @Id
    @GeneratedValue
    private Integer id;
    @Column
    private String title;
    @Column
    private String content;
    @JsonIgnoreProperties("Note")

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;
    public Note() {}
    public Note(Integer id, String title, String content, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.createdAt = createdAt;
    }
    public Note(Integer id, String title, String content, User user, Date createdAt) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.user = user;
        this.createdAt = createdAt;

    }

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date(); // Automatically set the current date and time
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
