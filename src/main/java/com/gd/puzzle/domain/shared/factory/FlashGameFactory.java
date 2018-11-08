package com.gd.puzzle.domain.shared.factory;

import java.util.List;

import com.gd.puzzle.domain.shared.model.Game;
import com.gd.puzzle.domain.shared.model.Player;
import com.gd.puzzle.domain.shared.model.TheFlashGame;

public class FlashGameFactory {

    public static Game createNewGame(List<Player> players, String type) {
        return new TheFlashGame(type, players);
    }
}
