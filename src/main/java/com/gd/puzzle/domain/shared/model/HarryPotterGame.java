package com.gd.puzzle.domain.shared.model;

import java.util.List;
import java.util.Objects;

public class HarryPotterGame extends Game {
    private String gameName = "The Harry Potter";

    public HarryPotterGame(String gameSeriesName, List<Player> players) {
        super(gameSeriesName, players);
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        if (!super.equals(o))
            return false;
        HarryPotterGame that = (HarryPotterGame) o;
        return Objects.equals(gameName, that.gameName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), gameName);
    }
}
