package com.pkappstudio.tictactoe.model;

/**
 * Created by kishon on 09,March,2022
 */
public enum Game {
    PLAYER_NONE(-1),
    PLAYER_WINK(0),
    PLAYER_SMILE(1),
    PLAYER_WINK_UNICODE(0x1F609),
    PLAYER_SMILE_UNICODE(0x1F601);

    private final int value;

    Game(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
