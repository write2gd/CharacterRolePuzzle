package com.gd.puzzle.util;

import static com.gd.puzzle.Constants.VERTICAL_LINE;
import static com.gd.puzzle.Constants.VERTICAL_LINE_RIGHT;

import java.util.List;
import java.util.Scanner;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.enums.Action;
import com.gd.puzzle.enums.GameType;
import com.gd.puzzle.enums.MenuItems;
import com.gd.puzzle.exception.GameServiceException;

public class ConsoleUtil {
    private static Scanner scanner = new Scanner(System.in);

    private static Scanner getScanner() {
        if (scanner == null) {
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static void printMessageWithBorder(String msg) {
        printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
        printMessage(VERTICAL_LINE + String.format("%29s", msg) + String.format("%5s", " ") + VERTICAL_LINE_RIGHT);
        printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
    }

    public static int readInteger() {
        try {
            return Integer.parseInt(getScanner().nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    public static void printCharacterList(List<GameCharacter> gameHeroes) {
        printMessage(ResourceUtil.getMessage("puzzle.select_character"));
        displayCharacters(gameHeroes);
    }

    private static void displayCharacters(List<GameCharacter> gameHeroes) {
        printMessage(
                   VERTICAL_LINE + String.format("%3s", "ID") + String.format("%3s", " ") + VERTICAL_LINE_RIGHT + String.format("%3s", "Name") + String.format(
                              "%3s", " ") + VERTICAL_LINE_RIGHT + String.format("%3s", "Experience") + String.format("%3s", " ") + VERTICAL_LINE_RIGHT
                              + String.format("%3s", "Speciality") + String.format("%3s", " ") + VERTICAL_LINE_RIGHT);
        int i = 1;
        for (GameCharacter c : gameHeroes) {
            printMessage(VERTICAL_LINE + String.format("%3s", i) + String.format("%3s", " ") + VERTICAL_LINE_RIGHT + String.format("%3s", c.getCharacterName())
                                    + String.format("%3s", " ") + VERTICAL_LINE_RIGHT + String.format("%3s", c.getExperience()) + String.format("%9s", " ")
                                    + VERTICAL_LINE_RIGHT + String.format("%3s", c.getSpeciality()) + String.format("%6s", " ") + VERTICAL_LINE_RIGHT);
            i++;
            sleep(500);
        }
        printMessage(ResourceUtil.getMessage("puzzle.plain_line"));
    }

    public static char readCharacter() {
        try {
            return getScanner().nextLine()
                               .toUpperCase()
                               .charAt(0);
        } catch (Exception e) {
            return 'X';
        }
    }

    public static String readString() {
        try {
            return getScanner().nextLine()
                               .toUpperCase();
        } catch (Exception e) {
            return "DEMO";
        }
    }

    public static void printMessage(String msg) {
        System.out.println(msg);
    }

    public static void announceWinner(Player winner) {
        printMessage(ResourceUtil.getMessage("puzzle.game_over"));
        printMessage(VERTICAL_LINE + String.format("%25s", "WINNER") + VERTICAL_LINE_RIGHT + String.format("%25s", winner.getPlayerName() + "("
                   + winner.getGameCharacter()
                           .getCharacterName() + ")") + VERTICAL_LINE_RIGHT);

        printMessage(ResourceUtil.getMessage("puzzle.plain_line"));
        printMessage("Press Any key to continue....");
        readCharacter();

    }

    public static void showPlayerStatistics(List<Player> players) {
        printMessage(ResourceUtil.getMessage("puzzle.player_stats"));
        printMessage(VERTICAL_LINE + String.format("%20s", "PLAYER") + VERTICAL_LINE_RIGHT + String.format("%20s", "HEALTH") + VERTICAL_LINE_RIGHT);
        for (Player p : players) {
            printMessage(VERTICAL_LINE + String.format("%20s", p.getGameCharacter()
                                                                .getCharacterName() + "(" + p.getPlayerName() + ")") + VERTICAL_LINE_RIGHT + String.format(
                       "%20s", p.getGameCharacter()
                                .getHealthLevel()) + VERTICAL_LINE_RIGHT);

        }
        printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
        sleep(1000);
    }

    public static void printGameControls() throws GameServiceException {
        printMessage(ResourceUtil.getMessage("puzzle.start_game_controls"));
        printMessage(VERTICAL_LINE + String.format("%25s", "Save & Exit Game") + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter S") + VERTICAL_LINE_RIGHT);
        printMessage(VERTICAL_LINE + String.format("%25s", "Exit Game") + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter X") + VERTICAL_LINE_RIGHT);
        printMessage(ResourceUtil.getMessage("puzzle.player_move_line"));
        printMessage(VERTICAL_LINE + String.format("%25s", Action.PUNCH.name()) + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter P") + VERTICAL_LINE_RIGHT);
        printMessage(
                   VERTICAL_LINE + String.format("%25s", Action.ATTACK.name()) + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter A") + VERTICAL_LINE_RIGHT);
        printMessage(VERTICAL_LINE + String.format("%25s", Action.HIT.name()) + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter H") + VERTICAL_LINE_RIGHT);

        printMessage(VERTICAL_LINE + String.format("%25s", Action.KICK.name()) + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter K") + VERTICAL_LINE_RIGHT);
        printMessage(
                   VERTICAL_LINE + String.format("%25s", Action.DEFENSE.name()) + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter D") + VERTICAL_LINE_RIGHT);
        printMessage(VERTICAL_LINE + String.format("%25s", Action.COUNTER_ATTACK.name()) + VERTICAL_LINE_RIGHT + String.format("%25s", "Enter C")
                                + VERTICAL_LINE_RIGHT);

        printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
        sleep(1000);
    }

    public static void showMenuItems() {
        printMessage(ResourceUtil.getMessage("puzzle.user_action"));
        printMessage(ResourceUtil.getMessage("puzzle.menu"));
        printMessage(VERTICAL_LINE + String.format("%25s", "Option Number") + VERTICAL_LINE_RIGHT + String.format("%36s", "Name") + VERTICAL_LINE_RIGHT);
        ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.plain_line"));
        for (MenuItems menu : MenuItems.values()) {
            printMessage(VERTICAL_LINE + String.format("%25s", menu.getAction()) + VERTICAL_LINE_RIGHT + String.format("%36s", menu.getActionMsg())
                                    + VERTICAL_LINE_RIGHT);
            sleep(500);
        }
        printMessage(ResourceUtil.getMessage("puzzle.plain_line"));
    }

    private static void sleep(int i) {
        try {
            Thread.sleep(i);
        } catch (InterruptedException e) {
        }
    }

    public static void showSaveSuccess() {
        printMessage("Game saved successfully.");
    }

    public static void showAvailableSeries() {

        printMessage(ResourceUtil.getMessage("puzzle.series"));
        printMessage(VERTICAL_LINE + String.format("%25s", "Series Id") + VERTICAL_LINE_RIGHT + String.format("%36s", "Series Name") + VERTICAL_LINE_RIGHT);
        ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.plain_line"));
        for (GameType gameType : GameType.values()) {
            printMessage(VERTICAL_LINE + String.format("%25s", gameType.getId()) + VERTICAL_LINE_RIGHT + String.format("%36s", gameType.getGameName())
                                    + VERTICAL_LINE_RIGHT);
            sleep(1000);
        }
        printMessage(ResourceUtil.getMessage("puzzle.plain_line"));
    }

    public static void showExitConfirmation() {
        printMessage("Do you want to play again? (Y/N)");

    }

    public static void showInvalidOptionMessage() {

        printMessage(ResourceUtil.getMessage("puzzle.invalid_option"));
    }

    public static void showRegardsMessage() {

        printMessageWithBorder("Nice Playing with you..");
    }

    public static void showSaveFailureMessage() {

        printMessageWithBorder("There is no active game to save");
    }

    public static void showResumedSuccessfully() {
        printMessage("Game resumed successfully.");

    }

    public static void showGameStartMessage() {

        printMessageWithBorder(ResourceUtil.getMessage("puzzle.start_game"));
    }

    public static void showCharacterSuccess() {

        printMessage("Character Created Successfully.");
    }

    public static void showExploreMessage(String playerName) {
        printMessageWithBorder("Welcome " + playerName);
        printMessage("You can explore different Heroes and Villains ");
    }

    public static void showHeroesMessage(List<GameCharacter> heroes, GameType g) {
        printMessage(ResourceUtil.getMessage("puzzle.heroes"));
        showCharacterSeries(heroes, g);

    }

    public static void showVillainsMessage(List<GameCharacter> heroes, GameType g) {
        printMessage(ResourceUtil.getMessage("puzzle.villains"));
        showCharacterSeries(heroes, g);

    }

    private static void showCharacterSeries(List<GameCharacter> heroes, GameType g) {
        if (g == GameType.FLASH) {
            printMessage("FLASH SERIES");
            displayCharacters(heroes);

        } else if (g == GameType.HARRY_POTTER) {
            printMessage("HARRY POTTER");
            displayCharacters(heroes);

        } else if (g == GameType.THE_LORD_OF_THE_RINGS) {
            printMessage("THE LORD OF THE RINGS");
            displayCharacters(heroes);

        } else {
            printMessage("SUPERHERO SERIES");
            displayCharacters(heroes);

        }
    }

    public static void showHelp() {
        printMessage(ResourceUtil.getMessage("puzzle.help"));
        printMessage("Enter the preferred option and follow the actions");
        printMessage(ResourceUtil.getMessage("puzzle.plain_line"));

    }
}
