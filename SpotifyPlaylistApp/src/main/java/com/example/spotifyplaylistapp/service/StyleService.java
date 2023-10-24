package com.example.spotifyplaylistapp.service;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;

public interface StyleService {
    void initStyles();

    Style findStyleByStyleName(StyleEnum style);
}
