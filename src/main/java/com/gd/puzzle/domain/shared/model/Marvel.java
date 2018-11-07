package com.gd.puzzle.domain.shared.model;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;

public class Marvel extends Game {
    private String gameName = "The Marvel";

    public Marvel(List<Location> destinations, List<Player> players) {
        super(destinations, players);
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
