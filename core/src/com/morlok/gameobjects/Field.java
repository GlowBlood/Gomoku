package com.morlok.gameobjects;

import com.badlogic.gdx.Gdx;


public class Field {

    private int height;         //Высота поля
    private int width;          //Ширина поля
    private int[][] fieldState; //Двумерный массив для хранения состояний
                                //пересечений клеток поля
                                // 0 - узел пуст
                                // 1 - узел занят игроком 1
                                // 2 - узел занят игроком 2

/*******************************
 *      Конструктор
 ******************************/
    public Field(int h, int w)
    {
        height = h;
        width = w;
        fieldState = new int[height][width];    //Создаем двумерный массив заданных резмерностей
        for (int i=0; i<height; i++)
            for (int j=0; j<width; j++)
                fieldState[i][j] = 0;           //Заполняем его начальными значениями
    }


    public void setPebble(int x, int y, int pebbleType)
    {
        fieldState[x][y] = pebbleType;
    }

    public int getPebble(int x, int y)
    {
        return fieldState[x][y];
    }

    public int getHeight()
    {
        return height;
    }

    public int getWidth()
    {
        return width;
    }

    public boolean isWin(int x, int y) {
        //Крутой и веселый метод, который будет определять, есть победа, или нет
        //Как он это будет делать?
        //По-индийски.
        //Понимаем, что победа может оказаться только при участии только что поставленного камушка
        //Поэтому мы и будем проверять вертикали, горизонтали и диагонали относительно него.
        //Ищем победу по горизонтали
        int numbOfCoins = 1; //Просто переменная, чтобы искать кол-во наших точек в ряд
        //Gdx.app.log("Gomoku:", x + " " + y);
        int currState = fieldState[x][y];
        for (int i=1; i<5 && (i + x)<width; i++)
            if (fieldState[i + x][y] == currState)
                numbOfCoins++;
            else
                break;
        for (int i=-1; i>-5 && (i + x) >= 0; i--)
            if (fieldState[i + x][y] == currState)
                numbOfCoins++;
            else
                break;
        if (numbOfCoins >= 5)
            return true;

        //Теперь тоже самое, только по вертикали
        numbOfCoins = 1;
        for (int i=1; i<5 && (i + y)<height; i++)
            if (fieldState[x][i + y] == currState)
                numbOfCoins++;
            else
                break;
        for (int i=-1; i>-5 && (i + y) >= 0; i--)
            if (fieldState[x][i + y] == currState)
                numbOfCoins++;
            else
                break;
        if (numbOfCoins >= 5)
            return true;
        //Ну и по диагонали (правая диагональ)
        numbOfCoins = 1;
        for (int i=1; i<5 && (i + y)<height && (i + x)<width; i++)
            if (fieldState[i + x][i + y] == currState)
                numbOfCoins++;
            else
                break;
        for (int i=-1; i>-5 && (i + y) >= 0 && (i + x) >= 0; i--)
            if (fieldState[i + x][i + y] == currState)
                numbOfCoins++;
            else
                break;
        if (numbOfCoins >= 5)
            return true;
        //Ну и по диагонали (левая диагональ)
        numbOfCoins = 1;
        for (int i=1; i<5 && (i + y)<height && (x - i) >= 0; i++)
            if (fieldState[x - i][i + y] == currState)
                numbOfCoins++;
            else
                break;
        for (int i=1; i<5 && (y - i) >= 0 && (i + x) < width; i++)
            if (fieldState[x + i][y - i] == currState)
                numbOfCoins++;
            else
                break;
        if (numbOfCoins >= 5)
            return true;

        return false;       //Ну не нашли мы победителя - идем дальше
    }
}
