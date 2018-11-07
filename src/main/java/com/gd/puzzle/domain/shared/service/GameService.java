package com.gd.puzzle.domain.shared.service;

import com.gd.puzzle.exception.GameServiceException;

public interface GameService {
    /**
     * Start a new Game with player
     *
     * @param playerName
     * @param gameType
     * @throws GameServiceException
     */
    void startNewGame(String playerName, String gameType) throws GameServiceException;

    /**
     * Resume the saved game
     *
     * @param playerName
     * @throws GameServiceException
     */
    void resumeGame(String playerName) throws GameServiceException;

    /**
     * Explore the game
     *
     * @param playerName
     * @throws GameServiceException
     */

    void exploreGame(String playerName) throws GameServiceException;
}
