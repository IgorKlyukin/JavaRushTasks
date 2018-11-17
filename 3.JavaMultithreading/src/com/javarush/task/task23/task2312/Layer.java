package com.javarush.task.task23.task2312;

import javax.swing.*;
import java.awt.*;

public class Layer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); //Задаем цвет элементов зеленый
        //g.fillRect(Room.game.getWidth() * 10, 0, 10, (Room.game.getWidth() * 10) + 10);  //Рисуем прямоугольник показывающий край поля справа
        //g.fillRect(0, Room.game.getHeight() * 10, (Room.game.getHeight() * 10) + 10, 10); //Рисуем прямоугольник показывающий край поля снизу

        g.fillRect(Room.game.getMouse().getX() * 10 + 1, Room.game.getMouse().getY() * 10 + 1, 9, 9); //Рисуем прямоугольник показывающий мышь

        java.util.List<SnakeSection> getsection = Room.game.getSnake().getSections(); //Получаем секции змейки
        g.setColor(Color.BLUE); //Задаем цвет элементов зеленый
        g.fillRect(getsection.get(0).getX() * 10, getsection.get(0).getY() * 10, 10, 10); //Рисуем по очереди секции змейки

        g.setColor(Color.GREEN); //Задаем цвет элементов зеленый
        for (int i = 1; i < getsection.size(); i++) {
            g.fillRect(getsection.get(i).getX() * 10 + 1, getsection.get(i).getY() * 10 + 1, 9, 9); //Рисуем по очереди секции змейки
        }
    }
}
