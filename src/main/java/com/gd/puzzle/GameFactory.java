package com.gd.puzzle;

import java.util.List;

import com.gd.puzzle.domain.shared.factory.FlashGameFactory;
import com.gd.puzzle.domain.shared.factory.HarryPotterGameFactory;
import com.gd.puzzle.domain.shared.factory.LordOfTheRingsGameFactory;
import com.gd.puzzle.domain.shared.factory.MarvelGameFactory;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.enums.GameType;

public class GameFactory {
    public static Game createGame(String type, List<Player> players) {
        if (type.equalsIgnoreCase(GameType.FLASH.getGameName())) {
            return FlashGameFactory.createNewGame(players, type);
        } else if (type.equalsIgnoreCase(GameType.HARRY_POTTER.getGameName())) {
            return HarryPotterGameFactory.createNewGame(players, type);
        } else if (type.equalsIgnoreCase(GameType.THE_LORD_OF_THE_RINGS.getGameName())) {
            return LordOfTheRingsGameFactory.createNewGame(players, type);
        } else {
            return MarvelGameFactory.createNewGame(players, type);
        }
    }
}
