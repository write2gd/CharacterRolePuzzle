package com.gd.puzzle.domain.shared.model;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;

public class TheFlash extends Game {
    private String gameName = "The Flash";

    public TheFlash(String gameSeriesName, List<Location> destinations, List<Player> players) {
        super(gameSeriesName, destinations, players);
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
