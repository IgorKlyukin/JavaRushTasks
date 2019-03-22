package com.javarush.task.task25.task2515;

public abstract class BaseObject {
    private double x;
    private double y;
    private double radius;
    private boolean isAlive;

    public BaseObject(double x, double y, double radius) {
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.isAlive = true;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public boolean isAlive() {
        return isAlive;
    }

    public void move() {

    }

    public void draw() {

    }

    public void die() {
        isAlive = false;
    }

    public boolean isIntersect(BaseObject o) {
        return Math.sqrt(Math.pow(x - o.x, 2) + Math.pow(y - o.y, 2)) < Double.max(radius, o.radius);
    }
}
