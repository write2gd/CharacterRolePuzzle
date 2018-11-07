package com.gd.puzzle.enums;

public enum Action {
    ATTACK(1, "ATTACK"), DEFENSE(2, "DEFENSE"), COUNTER_ATTACK(3, "COUNTER ATTACK"), KICK(4, "KICK"), PUNCH(5, "PUNCH"), HIT(6, "HIT");
    private int id;
    private String name;

    Action(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
