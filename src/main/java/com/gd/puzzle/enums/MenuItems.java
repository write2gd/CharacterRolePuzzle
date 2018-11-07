package com.gd.puzzle.enums;

public enum MenuItems {
    START_GAME(1, "Start New Game"), RESUME_GAME(2, "Resume from last point"), TRAVEL(3, "Explore the Game"), CREATE_CHARACTER(4,
                                                                                                                               "Create your favourite Character"), HELP(
               5, "Need Help"), EXIT(6, "Exit the Game");

    private int action;
    private String actionMsg;

    MenuItems(int action, String actionMsg) {
        this.action = action;
        this.actionMsg = actionMsg;
    }

    public int getAction() {
        return action;
    }

    public void setAction(int action) {
        this.action = action;
    }

    public String getActionMsg() {
        return actionMsg;
    }

    public void setActionMsg(String actionMsg) {
        this.actionMsg = actionMsg;
    }
}
