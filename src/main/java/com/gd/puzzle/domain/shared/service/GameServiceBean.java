package com.gd.puzzle.domain.shared.service;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import com.gd.puzzle.GameFactory;
import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.character.service.CharacterService;
import com.gd.puzzle.domain.character.service.CharacterServiceBean;
import com.gd.puzzle.domain.shared.helper.GameHelper;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.enums.GameType;
import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.exception.GameException;
import com.gd.puzzle.exception.GameServiceException;
import com.gd.puzzle.repository.GameRepository;
import com.gd.puzzle.repository.Repository;
import com.gd.puzzle.util.ConsoleUtil;

public class GameServiceBean implements GameService {
    private static GameServiceBean gameService;
    private static Repository repository = GameRepository.getGameRepository();
    private static CharacterService characterService = CharacterServiceBean.getCharacterService();

    public static GameServiceBean getGameService() {
        if (gameService == null) {
            synchronized (GameServiceBean.class) {
                gameService = new GameServiceBean();
            }
        }
        return gameService;
    }

    @Override
    public void startNewGame(String playerName, String gameType) throws GameServiceException {
        resetHealthOfSeriesCharacters(gameType);
        Player player = new Player(playerName, true);
        try {
            GameCharacter character = chooseCharacter(gameType);
            if (character == null) {
                return;
            }
            player.setGameCharacter(character);
        } catch (CharacterServiceException e) {
            throw new GameServiceException(e.getMessage(), e);
        }
        Player opponent = new Player("Computer", false);
        List<GameCharacter> villians = null;
        try {
            villians = characterService.getAvailableVilians(gameType);
        } catch (CharacterServiceException e) {
            throw new GameServiceException(e.getMessage(), e);
        }
        opponent.setGameCharacter(villians.get(new Random().nextInt(villians.size())));
        List<Player> players = Arrays.asList(player, opponent);
        Game game = GameFactory.createGame(gameType, players);
        ConsoleUtil.showGameStartMessage();
        ConsoleUtil.printGameControls();
        Player winner = game.play();
        nextAction(playerName, game, winner);
    }

    private void resetHealthOfSeriesCharacters(String gameType) {
        repository.resetHealthOfSeriesCharacters(gameType);
    }

    private void nextAction(String playerName, Game game, Player winner) {
        if (winner == null && game.isActive()) {
            saveGame(game, playerName);
        } else if (winner == null && !game.isActive()) {
            updateAndDeleteGame(playerName, game, false);
        } else {
            ConsoleUtil.announceWinner(winner);
            updateAndDeleteGame(playerName, game, true);
        }
    }

    private void updateAndDeleteGame(String playerName, Game game, boolean hasCompleted) {
        repository.deleteExistingGame(playerName);
        repository.saveGameAttributes(game.getPlayers(), game.getGameSeriesName(), hasCompleted);

    }

    private GameCharacter chooseCharacter(String selectedSeries) throws CharacterServiceException {
        List<GameCharacter> gameHeros = characterService.getAvailableHeros(selectedSeries);
        ConsoleUtil.printCharacterList(gameHeros);
        return GameHelper.getGameCharacter(gameHeros);
    }

    private void saveGame(Game game, String playerName) {
        try {
            repository.saveGame(game, playerName);
        } catch (GameException e) {
            ConsoleUtil.showSaveFailureMessage();
        }
        ConsoleUtil.showSaveSuccess();
    }

    @Override
    public void resumeGame(String playerName) throws GameServiceException {
        Game resumedGame = null;
        try {
            resumedGame = repository.getGame(playerName);
        } catch (GameException e) {
            throw new GameServiceException(e.getMessage(), e);
        }
        if (resumedGame != null) {
            ConsoleUtil.showResumedSuccessfully();
            Player winner = resumedGame.play();
            nextAction(playerName, resumedGame, winner);
        }
    }

    @Override
    public void exploreGame(String playerName) throws GameServiceException {
        ConsoleUtil.showExploreMessage(playerName);
        for (GameType g : GameType.values()) {
            List<GameCharacter> heros = null;
            try {
                heros = characterService.getAvailableHeros(g.getGameName());
            } catch (CharacterServiceException e) {
                throw new GameServiceException(e.getMessage(), e);
            }
            ConsoleUtil.showHerosMessage(heros, g);
            List<GameCharacter> vilians = null;
            try {
                vilians = characterService.getAvailableVilians(g.getGameName());
            } catch (CharacterServiceException e) {
                throw new GameServiceException(e.getMessage(), e);
            }
            ConsoleUtil.showVillainsMessage(vilians, g);
        }

    }

}
