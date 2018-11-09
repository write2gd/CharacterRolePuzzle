package com.gd.puzzle.domain.character.service;

import java.util.List;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.exception.CharacterServiceException;

public interface CharacterService {
    /**
     * This service is to add characters to the game repository
     *
     * @param character
     * @param seriesName
     * @throws CharacterServiceException
     */
    void addCharacter(GameCharacter character, String seriesName) throws CharacterServiceException;

    /**
     * This service will give available heroes for any role play series
     *
     * @param selectedSeries
     * @return
     * @throws CharacterServiceException
     */
    List<GameCharacter> getAvailableHeroes(String selectedSeries) throws CharacterServiceException;

    /**
     * This service will give available villains for any role play series
     *
     * @param selectedSeries
     * @return
     * @throws CharacterServiceException
     */
    List<GameCharacter> getAvailableVillains(String selectedSeries) throws CharacterServiceException;

}
