package com.gd.puzzle.domain.shared.service;

import com.gd.puzzle.exception.CharacterServiceException;
import com.gd.puzzle.exception.GameServiceException;
import com.gd.puzzle.exception.LocationServiceException;

public interface GameService {
    void startNewGame(String playerName, String gameType) throws GameServiceException;

    void resumeGame(String playerName) throws GameServiceException;

    void exploreGame(String playerName) throws GameServiceException;
}
