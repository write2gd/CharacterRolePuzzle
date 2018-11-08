package com.gd.puzzle.domain.shared.helper;

import static com.gd.puzzle.Constants.NEW_LINE;
import static com.gd.puzzle.util.ConsoleUtil.showMenuItems;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.enums.GameType;
import com.gd.puzzle.enums.MenuItems;
import com.gd.puzzle.util.ConsoleUtil;
import com.gd.puzzle.util.ResourceUtil;

public class GameHelper {

    public static int getPlayerAction() {
        showMenuItems();
        String promptMsg = NEW_LINE + ResourceUtil.getMessage("puzzle.user_action_prompt");
        int trialCount = 0;
        int selectedAction = 0;
        ConsoleUtil.printMessage(promptMsg);
        while (true) {
            selectedAction = ConsoleUtil.readInteger();
            if (Arrays.stream(MenuItems.values())
                      .map(menu -> menu.getAction())
                      .collect(Collectors.toList())
                      .contains(selectedAction)) {
                return selectedAction;

            } else {
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.invalid_option"));
                ConsoleUtil.printMessage(promptMsg);
                trialCount++;
            }
            if (trialCount == 3) {
                ConsoleUtil.printMessageWithBorder(ResourceUtil.getMessage("puzzle.exceeded_trials"));
                return 7;
            }
        }
    }

    public static int getSelectedIndex() {
        int trialCount = 0;
        int selectedIndex = 0;
        while (true) {
            selectedIndex = ConsoleUtil.readInteger();
            if (!Arrays.stream(GameType.values())
                       .map(type -> type.getId())
                       .collect(Collectors.toList())
                       .contains(selectedIndex)) {
                trialCount++;
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.invalid_series"));
            } else {
                break;
            }
            if (trialCount == 3) {
                ConsoleUtil.printMessageWithBorder(ResourceUtil.getMessage("puzzle.exceeded_trials"));
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
                selectedIndex = 0;
                break;
            }
        }
        return selectedIndex;
    }

    public static String getPlayerName() {
        ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.player_name"));
        int trialCount = 0;
        String playerName;
        while (true) {
            playerName = ConsoleUtil.readString();
            if (playerName != null && !playerName.isEmpty()) {
                break;
            } else {
                trialCount++;
                ConsoleUtil.printMessage("You have not entered your name. Please try again..");
            }
            if (trialCount == 3) {
                ConsoleUtil.printMessageWithBorder(ResourceUtil.getMessage("puzzle.exceeded_trials"));
                playerName = "";
                break;
            }
        }
        return playerName;
    }
    public static GameCharacter getGameCharacter(List<GameCharacter> gameHeros) {
        GameCharacter character = null;
        int trialCount = 0;
        while (true) {
            int selectedCharacter = ConsoleUtil.readInteger();
            if (selectedCharacter <= 0 || selectedCharacter > gameHeros.size()) {
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.invalid_series"));
                trialCount++;
            } else {
                character = Optional.ofNullable(gameHeros.get(selectedCharacter - 1))
                                    .orElse(null);
                break;
            }
            if(trialCount>=3){
                ConsoleUtil.printMessageWithBorder(ResourceUtil.getMessage("puzzle.exceeded_trials"));
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
                break;
            }
        }
        return character;
    }
}
