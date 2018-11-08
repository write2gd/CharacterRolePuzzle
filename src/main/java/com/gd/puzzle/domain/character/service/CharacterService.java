package com.gd.puzzle.domain.character.service;

import java.util.List;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.exception.CharacterServiceException;

public interface CharacterService {
    void addCharacter(GameCharacter character, String seriesName) throws CharacterServiceException;

    List<GameCharacter> getAvailableHeros(String selectedSeries) throws CharacterServiceException;

    List<GameCharacter> getAvailableVilians(String selectedSeries) throws CharacterServiceException;


}
