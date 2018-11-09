package com.gd.puzzle.enums;

public enum GameType {
    FLASH(1, "FLASH"), HARRY_POTTER(2, "HARRYPOTTER"), THE_LORD_OF_THE_RINGS(3, "LORDOFTHERINGS"), SUPERHEROES(4, "SUPERHEROES");
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