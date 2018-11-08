package com.gd.puzzle.domain.shared.factory;

import java.util.List;

import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.HarryPotterGame;
import com.gd.puzzle.domain.shared.model.Player;

public class HarryPotterGameFactory {
    public static Game createNewGame(List<Player> players, String type) {
        return new HarryPotterGame(type, players);
    }
}
