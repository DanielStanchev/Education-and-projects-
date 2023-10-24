package com.example.spotifyplaylistapp.model.dto;

import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.LocalDate;

public class SongAddDto {

    @Size(min = 3,max = 20)
    @NotNull
    private String performer;

    @Size(min = 3,max = 20)
    @NotNull
    private String title;

    @PastOrPresent
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate releaseDate;

    @Positive
    private Integer durationInSeconds;

    @NotNull
    private StyleEnum style;

    public SongAddDto() {
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

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getDurationInSeconds() {
        return durationInSeconds;
    }

    public void setDurationInSeconds(Integer durationInSeconds) {
        this.durationInSeconds = durationInSeconds;
    }

    public StyleEnum getStyle() {
        return style;
    }

    public void setStyle(StyleEnum style) {
        this.style = style;
    }
}
