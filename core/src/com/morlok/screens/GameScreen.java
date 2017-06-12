package com.morlok.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.morlok.gameworld.GameRenderer;
import com.morlok.gameworld.GameWorld;
import com.morlok.helpers.InputHelper;


public class GameScreen implements Screen {

    private GameWorld world;
    private GameRenderer renderer;

    public GameScreen()
    {
        int screenWidth =  Gdx.graphics.getWidth();
        int screenHeight = Gdx.graphics.getHeight();

        world = new GameWorld();
        renderer = new GameRenderer(screenHeight, screenWidth, world);

        Gdx.input.setInputProcessor(new InputHelper(world));
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        renderer.render();

    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
