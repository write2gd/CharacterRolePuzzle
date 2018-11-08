package com.gd.puzzle.domain.shared.factory;

import java.util.List;

import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.LordOfTheRingsGame;
import com.gd.puzzle.domain.shared.model.Player;

public class LordOfTheRingsGameFactory {
    public static Game createNewGame(List<Player> players, String type) {
        return new LordOfTheRingsGame(type, players);
    }
}
