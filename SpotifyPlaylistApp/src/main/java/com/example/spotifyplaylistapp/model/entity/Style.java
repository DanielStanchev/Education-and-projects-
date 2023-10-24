package com.example.spotifyplaylistapp.model.entity;

import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "styles")
public class Style extends BaseEntity{

    @Enumerated(EnumType.STRING)
    @Column(name = "style_name",nullable = false,unique = true)
    private StyleEnum styleName;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    public Style() {
    }

    public StyleEnum getStyleName() {
        return styleName;
    }

    public void setStyleName(StyleEnum styleName) {
        this.styleName = styleName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
