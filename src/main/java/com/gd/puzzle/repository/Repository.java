package com.gd.puzzle.repository;

import java.util.List;
import java.util.Set;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.exception.GameException;
import com.gd.puzzle.exception.LocationServiceException;

public interface Repository {
    List<Location> getLocation() throws LocationServiceException;

    void addHeroCharacter(GameCharacter character, String seriesName) throws CharacterServiceException;

    Set<GameCharacter> getAvailableHeros(String selectedSeries) throws CharacterServiceException;

    Set<GameCharacter> getAvailableVilians(String selectedSeries) throws CharacterServiceException;

    void addVilianCharacter(GameCharacter character, String seriesName) throws CharacterServiceException;

    void addNewLocation(Location location) throws LocationServiceException;

    void saveGame(Game game, String playerName) throws GameException;

    void saveGameAttributes();

    Game getGame(String playerName) throws GameException;

    void deleteExistingGame(String playerName);
}
