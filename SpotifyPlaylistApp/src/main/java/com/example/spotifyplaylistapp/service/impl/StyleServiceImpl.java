package com.example.spotifyplaylistapp.service.impl;

import com.example.spotifyplaylistapp.model.entity.Style;
import com.example.spotifyplaylistapp.model.entity.enums.StyleEnum;
import com.example.spotifyplaylistapp.repository.StyleRepository;
import com.example.spotifyplaylistapp.service.StyleService;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class StyleServiceImpl implements StyleService {

    private final StyleRepository styleRepository;

    public StyleServiceImpl(StyleRepository styleRepository) {this.styleRepository = styleRepository;}

    @Override
    public void initStyles() {

        if (styleRepository.count()!=0) {
            return;
        }

        Arrays.stream(StyleEnum.values())
            .forEach(styleEnum -> {
                Style style = new Style();
                style.setStyleName(styleEnum);

                styleRepository.save(style);
            });

    }

    @Override
    public Style findStyleByStyleName(StyleEnum style) {

        return styleRepository.findStyleByStyleName(style).orElse(null);
    }
}
