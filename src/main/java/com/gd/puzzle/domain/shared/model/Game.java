package com.gd.puzzle.domain.shared.model;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import com.gd.puzzle.domain.location.model.Location;
import com.gd.puzzle.util.ConsoleUtil;

public class Game implements Serializable {
    private static final long serialVersionUID = 1l;
    private String gameSeriesName;
    private List<Location> destinations;
    private List<Player> players;
    private boolean isActive = true;

    public Game(String gameSeriesName, List<Location> destinations, List<Player> players) {
        this.gameSeriesName = gameSeriesName;
        this.destinations = destinations;
        this.players = players;
    }

    public List<Player> getPlayers() {
        return players;
    }

    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    public List<Location> getDestinations() {
        return destinations;
    }

    public void setDestinations(List<Location> destinations) {
        this.destinations = destinations;
    }

    public boolean isActive() {
        return isActive;
    }

    public void setActive(boolean active) {
        isActive = active;
    }

    public String getGameSeriesName() {
        return gameSeriesName;
    }

    public void setGameSeriesName(String gameSeriesName) {
        this.gameSeriesName = gameSeriesName;
    }

    public void resetGame() {
        this.isActive = false;
        this.getPlayers()
            .forEach(p -> p.getGameCharacter()
                           .setHealthLevel(100));
    }

    public Player play() {
        if (this.getPlayers()
                .size() == 2) {
            return playWithComputer();
        } else {
            return playAmongPlayers();
        }
    }

    private Player playAmongPlayers() {
        return null;
    }

    private Player playWithComputer() {
        char action = 'A';
        Player you = this.getPlayers()
                         .get(0);
        Player computer = this.getPlayers()
                              .get(1);
        do {
            ConsoleUtil.showPlayerStatistics(this.getPlayers());
            Player current = you;
            Player opponent = computer;
            if (computer.isHasNextTurn()) {
                current = computer;
                opponent = you;
            } else {
                current = you;
                opponent = computer;
            }
            action = getPlayerAction(current, computer.isHasNextTurn());
            switch (action) {
            case 'S':
                return null;
            case 'X': {
                resetGame();
                return null;
            }
            default:
                makeAction(current, opponent, action);
            }
            computer.setHasNextTurn(!computer.isHasNextTurn());
        } while (you.isAlive() && computer.isAlive());
        addExperience();
        resetGame();
        return getWinner();
    }

    private void makeAction(Player current, Player opponent, char action) {
        switch (action) {
        case 'P': {
            current.getGameCharacter()
                   .punch(opponent.getGameCharacter());
            break;
        }
        case 'A': {
            current.getGameCharacter()
                   .attack(opponent.getGameCharacter());
            break;
        }
        case 'K': {
            current.getGameCharacter()
                   .kick(opponent.getGameCharacter());
            break;
        }
        case 'D': {
            current.getGameCharacter()
                   .selfDefense(opponent.getGameCharacter());
            break;
        }
        case 'C': {
            current.getGameCharacter()
                   .counterAttack(opponent.getGameCharacter());
            break;
        }
        case 'H': {
            current.getGameCharacter()
                   .hit(opponent.getGameCharacter());
            break;
        }
        default:
        }
    }

    public void addExperience() {
        this.getPlayers()
            .forEach(p -> p.getGameCharacter()
                           .setExperience(p.getGameCharacter()
                                           .getExperience() + 1));
    }

    private Player getWinner() {
        Collections.sort(this.players);
        return this.players.get(0);

    }

    private char getPlayerAction(Player player, boolean isComputerTurn) {
        ConsoleUtil.printMessage("Your Turn : " + player.getGameCharacter()
                                                        .getCharacterName());
        char action = 'P';
        if (isComputerTurn) {

            char[] actions = new char[] { 'P', 'A', 'K', 'H', 'D', 'C' };
            action = actions[new Random().nextInt(actions.length)];
            ConsoleUtil.printMessage(String.valueOf(action));
            return action;
        } else {
            try {
                return ConsoleUtil.getScanner()
                                  .nextLine()
                                  .toUpperCase()
                                  .charAt(0);
            } catch (Exception e) {
                return action;
            }
        }

    }

    public Player continuePlay() {
        return play();

    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Game game = (Game) o;
        return Objects.equals(destinations, game.destinations) && Objects.equals(players, game.players);
    }

    @Override
    public int hashCode() {
        return Objects.hash(destinations, players);
    }
}
