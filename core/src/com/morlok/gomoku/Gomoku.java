package com.morlok.gomoku;

import com.badlogic.gdx.Game;

import com.morlok.screens.GameScreen;


public class Gomoku extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
	}
}
