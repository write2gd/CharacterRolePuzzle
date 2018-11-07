package com.gd.puzzle.domain.shared.helper;

import static com.gd.puzzle.Constants.NEW_LINE;
import static com.gd.puzzle.util.ConsoleUtil.showMenuItems;

import java.util.Arrays;
import java.util.stream.Collectors;

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
            try {
                selectedAction = Integer.parseInt(ConsoleUtil.getScanner()
                                                             .nextLine());
            } catch (Exception e) {
                selectedAction = 0;
            }
            if (Arrays.stream(MenuItems.values())
                      .map(menu -> menu.getAction())
                      .collect(Collectors.toList())
                      .contains(selectedAction)) {
                return selectedAction;

            } else {
                trialCount++;
            }
            ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.invalid_option"));
            if (trialCount == 3) {
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
                showMenuItems();
                ConsoleUtil.printMessageWithBorder(promptMsg);
                trialCount = 0;
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
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.invalid_option"));
            } else {
                break;
            }
            if (trialCount == 3) {
                ConsoleUtil.printMessage(ResourceUtil.getMessage("puzzle.boundary_line"));
                break;
            }
        }
        return selectedIndex;
    }

}
