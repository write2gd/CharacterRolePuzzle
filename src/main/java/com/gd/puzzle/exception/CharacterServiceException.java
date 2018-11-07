package com.gd.puzzle.exception;

public class CharacterServiceException extends GameException {

    public CharacterServiceException(String message) {
        super(message);
    }

    public CharacterServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
