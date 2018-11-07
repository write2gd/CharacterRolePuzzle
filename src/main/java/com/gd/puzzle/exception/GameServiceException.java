package com.gd.puzzle.exception;

public class GameServiceException extends GameException {
    public GameServiceException(String message) {
        super(message);
    }

    public GameServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
