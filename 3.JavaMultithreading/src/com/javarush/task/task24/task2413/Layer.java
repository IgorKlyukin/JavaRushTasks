package com.javarush.task.task24.task2413;

import javax.swing.*;
import java.awt.*;

public class Layer extends JPanel {
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.RED); //Задаем цвет элементов зеленый
        //g.fillRect(Room.game.getWidth() * 10, 0, 10, (Room.game.getWidth() * 10) + 10);  //Рисуем прямоугольник показывающий край поля справа
        //g.fillRect(0, Room.game.getHeight() * 10, (Room.game.getHeight() * 10) + 10, 10); //Рисуем прямоугольник показывающий край поля снизу

        //g.fillRect(Arkanoid.game.getMouse().getX() * 10 + 1, Arkanoid.game.getMouse().getY() * 10 + 1, 9, 9); //Рисуем прямоугольник показывающий мышь

        //java.util.List<Brick> getsection = Arkanoid.game.getSnake().getSections(); //Получаем секции змейки
        //g.setColor(Color.BLUE); //Задаем цвет элементов зеленый
        //g.fillRect(getsection.get(0).getX() * 10, getsection.get(0).getY() * 10, 10, 10); //Рисуем по очереди секции змейки

        //g.setColor(Color.GREEN); //Задаем цвет элементов зеленый
       // for (int i = 1; i < getsection.size(); i++) {
        //    g.fillRect(getsection.get(i).getX() * 10 + 1, getsection.get(i).getY() * 10 + 1, 9, 9); //Рисуем по очереди секции змейки
        //}

        for (int i = 0, n = Arkanoid.canvas.getHeight() + 2; i < n; i++) {
            for (int j = 0, m = Arkanoid.canvas.getWidth() + 2; j < m; j++) {
                char c = Arkanoid.canvas.getMatrix()[i][j];
                switch (c){
                    case ('M'):{
                        g.setColor(Color.RED);
                        g.fillRect(j * 10, i * 10, 9, 9);
                        break;
                    }
                    case ('O'):{
                        g.setColor(Color.ORANGE);
                        g.fillRect(j * 10, i * 10, 10, 10);
                        break;
                    }
                    case ('H'):{
                        g.setColor(Color.BLUE);
                        g.fillRect(j * 10, i * 10, 10, 10);
                        break;
                    }
                    case ('.'):{
                        g.setColor(Color.ORANGE);
                        g.fillRect(j * 10, i * 10, 10, 10);
                        break;
                    }
                    case ('-'):{
                        g.setColor(Color.GRAY);
                        g.fillRect(j * 10, i * 10, 10, 10);
                        break;
                    }
                    case ('|'):{
                        g.setColor(Color.YELLOW);
                        g.fillRect(j * 10, i * 10, 10, 10);
                        break;
                    }
                    default:{
                        break;
                    }
                }
            }
        }
        g.setColor(Color.GREEN);
        g.fillOval((int)(Arkanoid.game.getBall().getX() * 10), (int)(Arkanoid.game.getBall().getY() * 10), 10, 10);
     }
}

