package com.gd.puzzle.domain.shared.model;

import java.io.Serializable;

import com.gd.puzzle.domain.character.model.GameCharacter;
import com.gd.puzzle.domain.location.model.Location;

public class Player implements Serializable, Comparable<Player> {
    private String playerName;
    private GameCharacter gameCharacter;
    private boolean hasNextTurn;
    private Location location;

    public Player(String playerName, boolean hasNextTurn) {
        this.playerName = playerName;
        this.hasNextTurn = hasNextTurn;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public GameCharacter getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(GameCharacter gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    public boolean isHasNextTurn() {
        return hasNextTurn;
    }

    public void setHasNextTurn(boolean hasNextTurn) {
        this.hasNextTurn = hasNextTurn;
    }

    public Location travelToDestination(Location location) {
        this.location = location;
        return location;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("Player [gameCharacter=");
        builder.append(gameCharacter);
        builder.append(", hasNextTurn=");
        builder.append(hasNextTurn);
        builder.append(", playerName=");
        builder.append(playerName);
        builder.append("]");
        return builder.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        Player player = (Player) o;

        if (playerName != null ? !playerName.equals(player.playerName) : player.playerName != null)
            return false;
        if (gameCharacter != null ? !gameCharacter.equals(player.gameCharacter) : player.gameCharacter != null)
            return false;
        return location != null ? location.equals(player.location) : player.location == null;
    }

    @Override
    public int hashCode() {
        int result = playerName != null ? playerName.hashCode() : 0;
        result = 31 * result + (gameCharacter != null ? gameCharacter.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        return result;
    }

    @Override
    public int compareTo(Player o) {
        return o.getGameCharacter()
                .getHealthLevel() - this.getGameCharacter()
                                        .getHealthLevel();
    }
}
