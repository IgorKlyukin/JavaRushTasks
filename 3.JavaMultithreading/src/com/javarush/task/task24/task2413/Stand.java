package com.javarush.task.task24.task2413;

public class Stand extends BaseObject{

    private double speed;
    private double direction;//направление движения по оси x: 1 - вправо, -1 - влево,
                            // 0 - начальное значение, стоим на месте

    public Stand(double x, double y, double radius) {
        super(x, y, radius);
    }

    public Stand(double x, double y) {
        super(x, y, 3);
        speed = 1;
        direction = 0;
    }

    public double getSpeed() {
        return speed;
    }

    public double getDirection() {
        return direction;
    }

    @Override
    public void draw(Canvas canvas) {

    }

    @Override
    public void move() {
        x += speed * direction;
    }

    public void moveLeft(){
        direction = -1;
    }

    public void moveRight(){
        direction = 1;
    }
}
