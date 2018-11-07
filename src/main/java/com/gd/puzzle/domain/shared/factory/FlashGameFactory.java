package com.gd.puzzle.domain.shared.factory;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.domain.shared.model.TheFlash;

public class FlashGameFactory {

    public static Game createNewGame(List<Player> players, List<Location> locations) {
        return new TheFlash(locations, players);
    }
}
