package com.gd.puzzle.domain.shared.model;

import java.io.Serializable;
import java.util.Objects;

import com.gd.puzzle.domain.character.model.GameCharacter;

public class Player implements Serializable, Comparable<Player> {
    private static final long serialVersionUID = 1l;
    private String playerName;
    private GameCharacter gameCharacter;
    private boolean hasNextTurn;
    private boolean isAlive = true;

    public Player(String playerName, boolean hasNextTurn) {
        this.playerName = playerName;
        this.hasNextTurn = hasNextTurn;
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

    public boolean isAlive() {
        return this.gameCharacter.getHealthLevel() > 0;
    }

    public void setAlive(boolean alive) {
        isAlive = alive;
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
        return Objects.equals(playerName, player.playerName) && Objects.equals(gameCharacter, player.gameCharacter);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerName, gameCharacter);
    }

    @Override
    public int compareTo(Player o) {
        return o.getGameCharacter()
                .getHealthLevel() - this.getGameCharacter()
                                        .getHealthLevel();
    }
}
