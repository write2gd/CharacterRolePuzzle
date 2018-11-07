package com.gd.puzzle;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.domain.shared.factory.FlashGameFactory;
import com.gd.puzzle.domain.shared.factory.HarryPotterGameFactory;
import com.gd.puzzle.domain.shared.factory.LordOfTheRingsGameFactory;
import com.gd.puzzle.domain.shared.factory.MarvelGameFactory;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.enums.GameType;

public class GameFactory {
    public static Game createGame(GameType type, List<Location> locations, List<Player> players) {
        if (type.ordinal() == 1) {
            return FlashGameFactory.createNewGame(players, locations);
        } else if (type.ordinal() == 2) {
            return HarryPotterGameFactory.createNewGame(players, locations);
        } else if (type.ordinal() == 3) {
            return LordOfTheRingsGameFactory.createNewGame(players, locations);
        } else {
            return MarvelGameFactory.createNewGame(players, locations);
        }
    }
}
