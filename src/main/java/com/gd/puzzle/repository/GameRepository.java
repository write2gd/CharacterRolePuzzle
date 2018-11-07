package com.gd.puzzle.repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.enums.CharacterType;
import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.exception.GameException;
import com.gd.puzzle.exception.LocationServiceException;
import com.gd.puzzle.util.ConsoleUtil;

public class GameRepository implements Repository {
    private static GameRepository gameRepository = null;
    private static Set<Location> locations = new HashSet<>();
    public static Map<String, Map<String, GameCharacter>> heros = new HashMap<>();
    public static Map<String, Map<String, GameCharacter>> vilians = new HashMap<>();

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
    public List<Location> getLocation() throws LocationServiceException {
        return Collections.unmodifiableList(new ArrayList<>(locations));
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
    public Map<String, GameCharacter> getAvailableVilians(String selectedSeries) throws CharacterServiceException {
        return vilians.get(selectedSeries);
    }

    @Override
    public void addVilianCharacter(GameCharacter character, String seriesName) throws CharacterServiceException {
        Map<String, GameCharacter> characters = vilians.get(seriesName);
        if (characters == null) {
            characters = new HashMap<>();
            characters.putIfAbsent(character.getCharacterName(), character);
            vilians.put(seriesName, characters);
        } else {
            characters.putIfAbsent(character.getCharacterName(), character);
        }
    }

    @Override
    public void addNewLocation(Location location) throws LocationServiceException {
        locations.add(location);

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
        } catch (Exception e) {
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
            file = new FileOutputStream("game_vilians");
            out = new ObjectOutputStream(file);
            out.writeObject(vilians);
            out.close();
            file.close();
            file = new FileOutputStream("game_locations");
            out = new ObjectOutputStream(file);
            out.writeObject(locations);
            out.close();
            file.close();
        } catch (Exception e) {

        }

    }

    private void updateStore(List<Player> players, String gameSeriesName) {
        Map<String, GameCharacter> heroCharacters = heros.get(gameSeriesName);
        Map<String, GameCharacter> vilianCharacters = vilians.get(gameSeriesName);
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
        } catch (Exception e) {
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
        }
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
            file = new FileInputStream("game_vilians");
            in = new ObjectInputStream(file);
            Map<String, Map<String, GameCharacter>> vilianMap = (HashMap) in.readObject();
            vilians.putAll(vilianMap);
            in.close();
            file.close();
            file = new FileInputStream("game_locations");
            in = new ObjectInputStream(file);
            Set<Location> locationSet = (HashSet) in.readObject();
            locations.addAll(locationSet);
            in.close();
            file.close();
        } catch (Exception e) {

        }
    }
}
