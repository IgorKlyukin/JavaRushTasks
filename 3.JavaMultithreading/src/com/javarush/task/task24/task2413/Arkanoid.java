package com.javarush.task.task24.task2413;

import java.awt.event.KeyEvent;
import java.util.ArrayList;

/**
 * Главный класс игры
 */
public class Arkanoid {
    // ширина и высота
    private int width;
    private int height;

    // список кирпичей
    private ArrayList<Brick> bricks = new ArrayList<Brick>();
    // шарик
    private Ball ball;
    // подставка
    private Stand stand;
    static Canvas canvas;

    // игра закончена?
    private boolean isGameOver = false;

    public Arkanoid(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public ArrayList<Brick> getBricks() {
        return bricks;
    }

    public Ball getBall() {
        return ball;
    }

    public void setBall(Ball ball) {
        this.ball = ball;
    }

    public Stand getStand() {
        return stand;
    }

    public void setStand(Stand stand) {
        this.stand = stand;
    }

    /**
     * Рисуем на холсте границы и все объекты.
     */
    void draw(Canvas canvas) {
        drawBorders(canvas);

        // draw bricks
        for (Brick brick : bricks) {
            brick.draw(canvas);
        }

        // draw ball
        ball.draw(canvas);

        // draw stand
        stand.draw(canvas);

    }

    /**
     * Рисуем на холсте границы
     */
    private void drawBorders(Canvas canvas) {
        // draw game
        for (int i = 1; i < width + 1; i++) {
            for (int j = 1; j < height + 1; j++) {
                canvas.setPoint(i, j, '.');
            }
        }

        for (int i = 0; i < width + 2; i++) {
            canvas.setPoint(i, 0, '-');
            canvas.setPoint(i, height + 1, '-');
        }

        for (int i = 0; i < height + 2; i++) {
            canvas.setPoint(0, i, '|');
            canvas.setPoint(width + 1, i, '|');
        }
    }

    /**
     * Основной цикл программы.
     * Тут происходят все важные действия
     */
    void run() throws Exception {
        // Создаем холст для отрисовки.
        canvas = new Canvas(width, height);

        // Создаем объект "наблюдатель за клавиатурой" и стартуем его.
        KeyboardObserver keyboardObserver = new KeyboardObserver();
        keyboardObserver.start();

        // Исполняем цикл, пока игра не окончена
        while (!isGameOver) {
            // "наблюдатель" содержит события о нажатии клавиш?
            if (keyboardObserver.hasKeyEvents()) {
                KeyEvent event = keyboardObserver.getEventFromTop();

                // Если "стрелка влево" - сдвинуть фигурку влево
                if (event.getKeyCode() == KeyEvent.VK_LEFT)
                    stand.moveLeft();
                    // Если "стрелка вправо" - сдвинуть фигурку вправо
                else if (event.getKeyCode() == KeyEvent.VK_RIGHT)
                    stand.moveRight();
                    // Если "пробел" - запускаем шарик
                else if (event.getKeyCode() == KeyEvent.VK_SPACE)
                    ball.start();
            }

            // двигаем все объекты
            move();

            // проверяем столкновения
            checkBricksBump();
            checkStandBump();

            // проверяем, что шарик мог улететь через дно.
            checkEndGame();

            // отрисовываем все объекты
            canvas.clear();
            draw(canvas);
            //canvas.print();
            if (KeyboardObserver.frame != null) {
                KeyboardObserver.frame.setContentPane(new Layer());
                KeyboardObserver.frame.setVisible(true);
            }

            // пауза
            Thread.sleep(5);
        }

        // Выводим сообщение "Game Over"
        System.out.println("Game Over!");
    }

    /**
     * Двигаем шарик и подставку.
     */
    public void move() {
        ball.move();
        stand.move();
    }

    /**
     * Проверяем столкновение с кирпичами.
     * Если столкновение было - шарик отлетает в случайном направлении 0..360 градусов
     */
    void checkBricksBump() {
        for (Brick brick : new ArrayList<Brick>(bricks)) {
            switch (ball.isIntersec(brick)) {
                case 1: {
                    ball.setDy(-ball.getDy());
                    bricks.remove(brick);
                    break;}
                case 2: {
                    ball.setDx(-ball.getDx());
                    bricks.remove(brick);
                    break;}
                case 3: {
                    ball.setDx(-ball.getDx());
                    bricks.remove(brick);
                    break;}
                case 4: {
                    ball.setDy(-ball.getDy());
                    bricks.remove(brick);
                    break;}
            }
        }
    }

    /**
     * Проверяем столкновение с подставкой.
     * Если столкновение было - шарик отлетает в случайном направлении  вверх 70..110 градусов.
     */
    void checkStandBump() {
        if (ball.isIntersec(stand) != 0) {
            double angle = 90 + 40 * (Math.random() - 0.5);
            ball.setDirection(angle);
        }
    }

    /**
     * Проверяем - не улетел ли шарик через дно.
     * Если да - игра окончена (isGameOver = true)
     */
    void checkEndGame() {
        if (ball.getY() + 1 > height + 1 && ball.getDy() > 0)
            isGameOver = true;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public static Arkanoid game;

    public static void main(String[] args) throws Exception {
        int w = 40;
        int h = 60;
        game = new Arkanoid(w, h);

        Ball ball = new Ball(w/2, h-1, .1, 95);
        game.setBall(ball);

        Stand stand = new Stand(w/2, h);
        game.setStand(stand);

        for (int i = 3; i < w; i+=4) {
            for (int j = 3; j < 50; j+=4) {
                game.getBricks().add(new Brick(i, j));
            }
        }

        game.run();
    }
}



















