package com.gd.puzzle.exception;

public class GameCreationException extends GameException {

    public GameCreationException(String message) {
        super(message);
    }

    public GameCreationException(String message, Throwable cause) {
        super(message, cause);
    }
}
