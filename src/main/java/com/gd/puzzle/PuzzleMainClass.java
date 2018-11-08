package com.gd.puzzle;

import java.io.File;

import com.gd.puzzle.domain.character.factory.CharacterFactory;
import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.character.service.CharacterService;
import com.gd.puzzle.domain.character.service.CharacterServiceBean;
import com.gd.puzzle.domain.shared.helper.GameHelper;
import com.gd.puzzle.domain.shared.service.GameService;
import com.gd.puzzle.domain.shared.service.GameServiceBean;
import com.gd.puzzle.exception.CharacterInitializationException;
import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.exception.GameException;
import com.gd.puzzle.exception.GameServiceException;
import com.gd.puzzle.util.ConsoleUtil;
import com.gd.puzzle.util.ResourceUtil;

public class PuzzleMainClass {
    private static GameService gameService = GameServiceBean.getGameService();
    private static CharacterService characterService = CharacterServiceBean.getCharacterService();
    private static String[] series = null;

    public static void main(String[] args) throws InterruptedException {
        init();
        String playerName = GameHelper.getPlayerName();
        if (playerName.isEmpty()) {
            return;
        }
        ConsoleUtil.printMessage("Welcome  " + playerName);
        while (true) {
            int nextAction = GameHelper.getPlayerAction();
            switch (nextAction) {
            case 1:
                if (series == null)
                    return;
                startNewGame(playerName, series);
                break;
            case 2:
                ConsoleUtil.printMessageWithBorder(ResourceUtil.getMessage("puzzle.resume_game"));
                try {
                    gameService.resumeGame(playerName);
                } catch (GameServiceException e) {
                    ConsoleUtil.printMessage(e.getMessage());
                }
                break;
            case 3:
                try {
                    gameService.exploreGame(playerName);
                } catch (GameServiceException e) {
                    ConsoleUtil.printMessage(e.getMessage());
                }
                break;
            case 4:
                try {
                    createCharacter(playerName);
                } catch (CharacterServiceException e) {
                    ConsoleUtil.printMessage(e.getMessage());
                }
                break;
            case 5:
                ConsoleUtil.showHelp();
                break;
            case 6:
                return;
            default:
                ConsoleUtil.showHelp();
            }
        }
    }

    private static void init() throws InterruptedException {
        if (series != null) {
            return;
        }
        ConsoleUtil.printMessageWithBorder("Initializing Game..wait");
        series = ResourceUtil.getResourceFolders("series");
        for (String selectedSeries : series) {
            try {
                createSeries(selectedSeries);
            } catch (GameException e) {
                ConsoleUtil.printMessageWithBorder("Game Initialization failed: " + e.getMessage());
            }
        }
        Thread.sleep(1000);
    }

    private static void createCharacter(String playerName) throws CharacterServiceException {
        ConsoleUtil.printMessage("Enter  Character Name");
        String charName = ConsoleUtil.readString();
        ConsoleUtil.printMessage("Enter  Character type(hero/vilian)");
        String type = ConsoleUtil.readString();
        ConsoleUtil.printMessage("Enter  Character Speciality(FLY/RUN/FIRE/MAGIC/STRENGTH)");
        String speciality = ConsoleUtil.readString();
        ConsoleUtil.printMessage("Enter  Character Punch power(1-100)");
        int punch = ConsoleUtil.readInteger();
        ConsoleUtil.printMessage("Enter  Character Hitting power(1-100)");
        int hit = ConsoleUtil.readInteger();
        ConsoleUtil.printMessage("Enter  Character Kick power(1-100)");
        int kick = ConsoleUtil.readInteger();
        ConsoleUtil.printMessage("Enter  Character Attacking power(1-100)");
        int attack = ConsoleUtil.readInteger();
        ConsoleUtil.printMessage("Enter the Series to character belongs(FLASH/HARRYPOTTER/LORDOFTHERINGS/SUPERHEROS)");
        String series = ConsoleUtil.readString();
        GameCharacter character = CharacterFactory.createCharacter(charName, type, speciality, punch, hit, kick, attack);
        characterService.addCharacter(character, series);
        ConsoleUtil.showCharacterSuccess();

    }

    private static void startNewGame(String playerName, String[] series) {
        ConsoleUtil.showAvailableSeries();
        int selectedIndex = GameHelper.getSelectedIndex();
        if (selectedIndex == 0) {
            return;
        }
        try {
            gameService.startNewGame(playerName, series[selectedIndex - 1].toUpperCase());
        } catch (GameServiceException e) {
            ConsoleUtil.printMessage(e.getMessage());
        }
    }

    private static void createSeries(String selectedSeries) throws GameException {
        File[] characters = ResourceUtil.getResourceFiles("series/" + selectedSeries);
        for (File file : characters) {
            try {
                characterService.addCharacter(CharacterFactory.createCharacter(file), selectedSeries.toUpperCase());
            } catch (CharacterServiceException | CharacterInitializationException e) {
                throw new GameException(e.getMessage(), e);
            }
        }
    }

}
