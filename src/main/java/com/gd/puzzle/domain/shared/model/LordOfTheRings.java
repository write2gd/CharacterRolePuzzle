package com.gd.puzzle.domain.shared.model;

import java.util.List;

import com.gd.puzzle.domain.location.model.Location;

public class LordOfTheRings extends Game {
    private String gameName = "The Lord Of the Rings";

    public LordOfTheRings(List<Location> destinations, List<Player> players) {
        super(destinations, players);
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }
}
