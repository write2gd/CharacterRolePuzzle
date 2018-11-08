package com.gd.puzzle.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.enums.CharacterType;
import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.exception.GameException;
import com.gd.puzzle.util.ConsoleUtil;

public class GameRepository implements Repository {
    private static GameRepository gameRepository = null;
    private static final Map<String, Map<String, GameCharacter>> heros = new HashMap<>();
    private static final Map<String, Map<String, GameCharacter>> villains = new HashMap<>();

    static {
        initRepo();
    }

    private static void initRepo() {
        readGameAttributes();

    }

    public static GameRepository getGameRepository() {
        if (gameRepository == null) {
            synchronized (GameRepository.class) {
                gameRepository = new GameRepository();
            }
        }
        return gameRepository;
    }

    @Override
    public void addHeroCharacter(GameCharacter character, String seriesName) throws CharacterServiceException {
        Map<String, GameCharacter> characters = heros.get(seriesName);
        if (characters == null) {
            characters = new HashMap<>();
            characters.putIfAbsent(character.getCharacterName(), character);
            heros.put(seriesName, characters);
        } else {
            characters.putIfAbsent(character.getCharacterName(), character);
        }
    }

    @Override
    public Map<String, GameCharacter> getAvailableHeros(String selectedSeries) throws CharacterServiceException {
        return heros.get(selectedSeries);
    }

    @Override
    public Map<String, GameCharacter> getAvailableVillains(String selectedSeries) throws CharacterServiceException {
        return villains.get(selectedSeries);
    }

    @Override
    public void addVillainCharacter(GameCharacter character, String seriesName) throws CharacterServiceException {
        Map<String, GameCharacter> characters = villains.get(seriesName);
        if (characters == null) {
            characters = new HashMap<>();
            characters.putIfAbsent(character.getCharacterName(), character);
            villains.put(seriesName, characters);
        } else {
            characters.putIfAbsent(character.getCharacterName(), character);
        }
    }

    @Override
    public void saveGame(Game game, String playerName) throws GameException {
        FileOutputStream file;
        ObjectOutputStream out;
        try {
            file = new FileOutputStream(playerName);
            out = new ObjectOutputStream(file);
            out.writeObject(game);
            out.close();
            file.close();
        } catch (IOException e) {
            ConsoleUtil.printMessageWithBorder("Game not saved due to technical issue");
        }
    }

    @Override
    public void saveGameAttributes(List<Player> players, String gameSeriesName, boolean hasCompleted) {
        FileOutputStream file;
        ObjectOutputStream out;
        if (hasCompleted) {
            updateStore(players, gameSeriesName);
        }
        try {
            file = new FileOutputStream("game_heros");
            out = new ObjectOutputStream(file);
            out.writeObject(heros);
            out.close();
            file.close();
            file = new FileOutputStream("game_villains");
            out = new ObjectOutputStream(file);
            out.writeObject(villains);
            out.close();
            file.close();
        } catch (IOException e) {

        }

    }

    private void updateStore(List<Player> players, String gameSeriesName) {
        Map<String, GameCharacter> heroCharacters = heros.get(gameSeriesName);
        Map<String, GameCharacter> vilianCharacters = villains.get(gameSeriesName);
        for (Player p : players) {
            if (p.getGameCharacter()
                 .getType() == CharacterType.HERO) {
                heroCharacters.put(p.getGameCharacter()
                                    .getCharacterName(), p.getGameCharacter());
            } else if (p.getGameCharacter()
                        .getType() == CharacterType.VILIAN) {
                vilianCharacters.put(p.getGameCharacter()
                                      .getCharacterName(), p.getGameCharacter());
            }
        }
    }

    @Override
    public Game getGame(String playerName) throws GameException {
        FileInputStream file;
        ObjectInputStream in;
        try {
            file = new FileInputStream(playerName);
            in = new ObjectInputStream(file);
            Game game = (Game) in.readObject();
            in.close();
            file.close();
            return game;
        } catch (IOException | ClassNotFoundException e) {
            throw new GameException("Game can not be resume...No Active Game");
        }
    }

    @Override
    public void deleteExistingGame(String playerName) {
        try {
            File file = new File(playerName);
            if (file.exists()) {
                file.delete();
            }
        } catch (Exception e) {
            //Nothing to do
        }
    }

    @Override
    public void resetHealthOfSeriesCharacters(String gameSeriesName) {
        Map<String, GameCharacter> heroCharacters = heros.get(gameSeriesName);
        Map<String, GameCharacter> villainCharacters = villains.get(gameSeriesName);
        heroCharacters.values()
                      .forEach(ch -> ch.setHealthLevel(100));
        villainCharacters.values()
                         .forEach(ch -> ch.setHealthLevel(100));
    }

    private static void readGameAttributes() {
        FileInputStream file;
        ObjectInputStream in;
        try {
            file = new FileInputStream("game_heros");
            in = new ObjectInputStream(file);
            Map<String, Map<String, GameCharacter>> herosMap = (HashMap) in.readObject();
            heros.putAll(herosMap);
            in.close();
            file.close();
            file = new FileInputStream("game_villains");
            in = new ObjectInputStream(file);
            Map<String, Map<String, GameCharacter>> vilianMap = (HashMap) in.readObject();
            villains.putAll(vilianMap);
            in.close();
            file.close();
        } catch (IOException | ClassNotFoundException e) {

        }
    }
}
