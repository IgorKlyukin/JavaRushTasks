package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.io.File;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;

public class Model {
    public static final int FIELD_CELL_SIZE = 20;
    private EventListener eventListener;
    private GameObjects gameObjects;
    private int currentLevel = 1;
//    private LevelLoader levelLoader = new LevelLoader(Paths.get(new File(this.getClass().getPackage().getName().replace(".", "\\")).getParent().replace("\\", ".") + ".res.levels.txt"));
    private LevelLoader levelLoader = new LevelLoader(
            new File(this.getClass().getPackage().getName()
                + ".res.levels.txt").toPath());
    public void setEventListener(EventListener eventListener) {
        this.eventListener = eventListener;
    }

    public GameObjects getGameObjects() {
        return gameObjects;
    }

    public void restartLevel(int level) {
        gameObjects = levelLoader.getLevel(level);
    }

    public void restart() {
        restartLevel(currentLevel);
    }

    public void startNextLevel() {
        currentLevel++;
        restart();
    }

    public void move(Direction direction){
        Player player = gameObjects.getPlayer();
        if (checkWallCollision(player, direction) || checkBoxCollisionAndMoveIfAvaliable(direction))
            return;

        moving(player, direction);

        checkCompletion();
    }

    public boolean checkWallCollision(CollisionObject gameObject, Direction direction){
        for (Wall wall :
                gameObjects.getWalls())
            if (gameObject.isCollision(wall, direction))
                return true;
        return false;
    }

    public boolean checkBoxCollisionAndMoveIfAvaliable(Direction direction) {
        Player player = gameObjects.getPlayer();
        for (Box box :
                gameObjects.getBoxes())
            if (player.isCollision(box, direction)) {
                if (checkWallCollision(box, direction))
                    return true;

                Set<Box> set = new HashSet<>(gameObjects.getBoxes());
                set.remove(box);

                for (Box boxs :
                        set) {
                    if (box.isCollision(boxs, direction))
                        return true;
                }

                moving(box, direction);
                break;
            }
        return false;
    }

    private void moving(Movable movable, Direction direction) {
        int fcs = Model.FIELD_CELL_SIZE;
        switch (direction) {
            case UP: {
                movable.move(0,-fcs);
                break;
            }
            case DOWN: {
                movable.move(0,fcs);
                break;
            }
            case RIGHT: {
                movable.move(fcs,0);
                break;
            }
            case LEFT: {
                movable.move(-fcs,0);
                break;
            }
        }
    }

    public void checkCompletion() {
        boolean flag = false;
        Set<Home> homes = gameObjects.getHomes();
        for (Box box :
             gameObjects.getBoxes()) {
            flag = false;
            for (Home home:
                 homes) {
                if (box.getX() == home.getX() && box.getY() == home.getY()) {
                    flag = true;
                    break;
                }
            }
            if (!flag)
                break;
        }

        if (flag)
            eventListener.levelCompleted(currentLevel);
    }
}
