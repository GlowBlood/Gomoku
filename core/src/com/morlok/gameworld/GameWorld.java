package com.morlok.gameworld;

import com.morlok.gameobjects.Field;

public class GameWorld {

    public enum GameState {
        READY, GREENTURN, BLUETURN, GREENWIN, BLUEWIN
    }

    private Field field;        //Игровое поле, которым будем пользоваться
    private GameState gameState;

    public GameWorld()
    {
        field = new Field(10,10);
        gameState = GameState.READY;
    }

    public Field getField()
    {
        return field;
    }

    public GameState getGameState()
    {
        return gameState;
    }

    public void setGameState(GameState newState)
    {
        gameState = newState;
    }
}
