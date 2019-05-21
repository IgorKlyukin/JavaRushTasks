package com.javarush.task.task34.task3410.model;

import com.javarush.task.task34.task3410.controller.EventListener;

import java.io.File;
import java.nio.file.Paths;

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

    }
}
