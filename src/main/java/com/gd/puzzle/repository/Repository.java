package com.gd.puzzle.repository;

import java.util.List;
import java.util.Map;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.exception.GameException;

public interface Repository {

    void addHeroCharacter(GameCharacter character, String seriesName) throws CharacterServiceException;

    Map<String, GameCharacter> getAvailableHeroes(String selectedSeries) throws CharacterServiceException;

    Map<String, GameCharacter> getAvailableVillains(String selectedSeries) throws CharacterServiceException;

    void addVillainCharacter(GameCharacter character, String seriesName) throws CharacterServiceException;

    void saveGame(Game game, String playerName) throws GameException;

    void saveGameAttributes(List<Player> players, String gameSeriesName, boolean hasCompleted);

    Game getGame(String playerName) throws GameException;

    void deleteExistingGame(String playerName);

    void resetHealthOfSeriesCharacters(String gameType);
}
