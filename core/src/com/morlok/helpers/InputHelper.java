package com.morlok.helpers;

import com.badlogic.gdx.InputProcessor;
import com.morlok.gameobjects.Field;
import com.morlok.gameworld.GameWorld;


public class InputHelper implements InputProcessor {

    private GameWorld world;
    private Field field;

    public InputHelper(GameWorld world)
    {
        this.world = world;
        field = world.getField();
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {

        //Если идет обычная игра
        if (    world.getGameState() == GameWorld.GameState.GREENTURN||
                world.getGameState() == GameWorld.GameState.BLUETURN) {
            //Здесь мы будем обрабатывать тырк по экрану
            //Находим координаты, куда ставить камень
            int x, y;

            //Переходим в нашу рисующуюся систему координат игрового поля
            //Из-за того, что у нас рисуется все в системе координат "центр внизу"
            //А координаты нажатия он возвращает в системе координат "центр вверху"
            screenY = 330 - screenY;

            //Получаем грубую оценку
            x = screenX / 30;
            y = screenY / 30;
            //Уточняем по области
            if (screenX % 30 > 15)
                x++;
            if (screenY % 30 > 15)
                y++;

            //Кладем камень
            if (x < field.getWidth() && y < field.getHeight() && field.getPebble(x, y) == 0) {   //Разумеется, мы делаем проверку на выход
                //нашего тырка за пределы поля
                if (world.getGameState() == GameWorld.GameState.BLUETURN) {
                    field.setPebble(x, y, 1);
                    world.setGameState(GameWorld.GameState.GREENTURN);        //Меняем привилегию хода
                } else if (world.getGameState() == GameWorld.GameState.GREENTURN) {
                    field.setPebble(x, y, 2);
                    world.setGameState(GameWorld.GameState.BLUETURN);       //Меняем привилегию хода
                }

                //А теперь самое веселое - определение победителя
                //Если победа есть - выставляем соответствующий стейт
                if (field.isWin(x, y)) {
                    if (world.getGameState() == GameWorld.GameState.BLUETURN)
                        world.setGameState(GameWorld.GameState.GREENWIN);
                    if (world.getGameState() == GameWorld.GameState.GREENTURN)
                        world.setGameState(GameWorld.GameState.BLUEWIN);
                }
            }

        }
        else
        {
            if (world.getGameState() == GameWorld.GameState.READY) //Если игра еще не начиналась даже
                if (screenY <= 60)
                    world.setGameState(GameWorld.GameState.GREENTURN);
            if (    world.getGameState() == GameWorld.GameState.BLUEWIN ||
                    world.getGameState() == GameWorld.GameState.GREENWIN)
                if (screenY <= 60) {
                    //Выставляем все в начало
                    world.setGameState(GameWorld.GameState.READY);
                    //Чистим поле
                    for (int i=0; i<field.getHeight(); i++)
                        for (int j=0; j<field.getWidth(); j++)
                            field.setPebble(i,j,0);
                }
        }
        return true;
    }

/*********************************************************
 *      Эта требуха нам не нужна -
 *      она только для имплементации осталась
 *********************************************************/
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
