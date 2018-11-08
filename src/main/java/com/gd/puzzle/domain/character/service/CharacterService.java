package com.gd.puzzle.domain.character.service;

import java.util.List;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.exception.CharacterServiceException;

public interface CharacterService {
    void addCharacter(GameCharacter character, String seriesName) throws CharacterServiceException;

    List<GameCharacter> getAvailableHeroes(String selectedSeries) throws CharacterServiceException;

    List<GameCharacter> getAvailableVillains(String selectedSeries) throws CharacterServiceException;


}
