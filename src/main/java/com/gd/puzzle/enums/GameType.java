package com.gd.puzzle.enums;

public enum GameType {
    FLASH(1, "FLASH"), HARRY_POTTER(2, "HARRY POTTER"), THE_LORD_OF_THE_RINGS(3, "THE LORD_OF_THE_RINGS"), SUPERHEROES(4, "SUPER HERO SERIES ");
    private int id;
    private String gameName;

    GameType(int id, String gameName) {
        this.id = id;
        this.gameName = gameName;
    }

    public int getId() {
        return id;
    }

    public String getGameName() {
        return gameName;
    }
}