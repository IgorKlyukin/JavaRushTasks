package com.javarush.task.task34.task3410.model;

import java.awt.*;

public abstract class CollisionObject extends GameObject {
    public CollisionObject(int x, int y) {
        super(x, y);
    }

    @Override
    public void draw(Graphics graphics) {

    }

    public boolean isCollision(GameObject gameObject, Direction direction) {
        boolean flag = false;
        switch (direction) {
            case UP: {
                if (gameObject.getY() == getY() - Model.FIELD_CELL_SIZE && getX() == gameObject.getX())
                    flag = true;
                break;
            }
            case DOWN: {
                if (gameObject.getY() == getY() + Model.FIELD_CELL_SIZE && getX() == gameObject.getX())
                    flag = true;
                break;
            }
            case RIGHT: {
                if (gameObject.getX() == getX() + Model.FIELD_CELL_SIZE && getY() == gameObject.getY())
                    flag = true;
                break;
            }
            case LEFT: {
                if (gameObject.getX() == getX() - Model.FIELD_CELL_SIZE && getY() == gameObject.getY())
                    flag = true;
                break;
            }
        }
        return flag;
    }
}
