package com.morlok.gameworld;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.morlok.gameobjects.Field;


public class GameRenderer {

    private int screenHeight;
    private int screenWidth;

    private SpriteBatch batch;
    private Texture cell;
    private Texture bluePebble;
    private Texture greenPebble;

    public static BitmapFont font;

    private GameWorld world;

    private Field field;

    public GameRenderer(int h, int w, GameWorld world)
    {
        screenHeight = h;
        screenWidth = w;

        this.world = world;
        field = this.world.getField();

        batch = new SpriteBatch();
        cell = new Texture("data/textures/cell.png");
        bluePebble = new Texture("data/textures/bluePebble.png");
        greenPebble = new Texture("data/textures/greenPebble.png");

        font = new BitmapFont(Gdx.files.internal("data/font/text.fnt"));
        font.getData().setScale(.5f, .5f);
    }

    public void render()
    {


        //Заполняем фон белым цветом
        Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
        //Затем в цикле начинаем отрисовку поля
		for (int i=0; i<field.getHeight()-1; i++)
		    for (int j=0; j<field.getWidth()-1; j++)
                batch.draw(cell, i*30, j*30);
		//Теперь пробегаемся по полю и выставляем, где нужно, камушки
        for (int i=0; i<field.getHeight(); i++) {
            for (int j = 0; j < field.getWidth(); j++) {
                if (field.getPebble(i, j) == 1)
                    batch.draw(bluePebble, i * 30 - 7, j * 30 - 7);
                else if (field.getPebble(i, j) == 2)
                    batch.draw(greenPebble, i * 30 - 7, j * 30 - 7);
            }
        }
        //В зависимости от того, чей ход - выводим соответствующую информацию
        //А может игра вообще окончена?
        //А может игра еще и не началась?
        //Как знать, как знать.
        switch (world.getGameState())
        {
            case READY:
                font.draw(batch, "Tap for start", 5, 315);
                break;
            case GREENTURN:
                font.draw(batch, "Green turn", 40, 315);
                break;
            case BLUETURN:
                font.draw(batch, "Blue turn", 50, 315);
                break;
            case GREENWIN:
                font.draw(batch, "Green win!", 40, 315);
                break;
            case BLUEWIN:
                font.draw(batch, "Blue win!", 50, 315);
                break;
        }


        batch.end();

    }



}
