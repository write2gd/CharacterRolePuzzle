package com.gd.puzzle.domain.shared.factory;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Marvel;
import com.gd.puzzle.domain.shared.model.Player;

public class MarvelGameFactory {

    public static Game createNewGame(List<Player> players, List<Location> locations, String type) {
        return new Marvel(type, locations, players);
    }
}
