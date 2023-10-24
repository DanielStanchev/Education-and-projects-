package com.example.spotifyplaylistapp.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Table(name = "songs")
public class Song extends BaseEntity {

    @Column(name = "performer",nullable = false)
    private String performer;

    @Column(name = "title",nullable = false)
    private String title;

    @Column(name = "duration",nullable = false)
    private Integer duration;

   @Column(name = "release_date")
    private LocalDate releaseDate;

    @ManyToOne
    @NotNull
    private Style style;

    @ManyToMany(mappedBy = "playlist")
    private Set<User> users;

    public Song() {
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public String getPerformer() {
        return performer;
    }

    public void setPerformer(String performer) {
        this.performer = performer;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Style getStyle() {
        return style;
    }

    public void setStyle(Style style) {
        this.style = style;
    }
}
