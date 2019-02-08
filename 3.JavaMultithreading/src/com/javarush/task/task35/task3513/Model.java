package com.javarush.task.task35.task3513;

import java.util.*;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score = 0;
    protected int maxTile = 0;
    private Stack<Tile[][]> previousStates = new Stack();
    private Stack<Integer> previousScores = new Stack();
    private boolean isSaveNeeded = true;

    public Model() {
        resetGameTiles();
    }

    private void addTile() {
        List<Tile> list = getEmptyTiles();
        if (list.size() > 0)
            list.get((int)(list.size() * Math.random())).value = Math.random() < 0.9 ? 2 : 4 ;
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> list = new ArrayList<>();
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0)
                    list.add(gameTiles[i][j]);
            }
        }
        return list;
    }

    protected void resetGameTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++) {
            for (int j = 0; j < FIELD_WIDTH; j++) {
                gameTiles[i][j] = new Tile();
            }
        }

        addTile();
        addTile();

        score = 0;
        maxTile = 0;
    }

    private boolean compressTiles(Tile[] tiles){
        int count = 0;
        boolean flag = false;
        for (int i = 0; i < FIELD_WIDTH; i++)
            if (tiles[i].value != 0)
                tiles[count++].value = tiles[i].value;
        while (count < FIELD_WIDTH) {
            if (tiles[count].value != 0)
                flag = true;
            tiles[count++].value = 0;
        }
        return flag;
    }

    private boolean mergeTiles(Tile[] tiles){
        for (int i = 0; i < FIELD_WIDTH - 1; i++) {
            if (tiles[i].value == tiles[i + 1].value) {
                tiles[i + 1].value *= 2;
                score += tiles[i + 1].value;
                if (tiles[i + 1].value > maxTile)
                    maxTile = tiles[i + 1].value;
                tiles[i].value = 0;
                i++;
            }
        }
        return compressTiles(tiles);
    }

    public void left() {
        if (isSaveNeeded)
            saveState(gameTiles);
        boolean flag = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            flag |= compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]);
        }
        isSaveNeeded = true;
        if (flag)
            addTile();
    }

    public void right() {
        saveState(gameTiles);
        for (int i = 0; i < FIELD_WIDTH; i++) {
            turn();
            if (i == 1)
                left();
        }
    }

    public void up() {
        saveState(gameTiles);
        for (int i = 0; i < FIELD_WIDTH; i++) {
            turn();
            if (i == 0)
                left();
        }
    }

    public void down() {
        saveState(gameTiles);
        for (int i = 0; i < FIELD_WIDTH; i++) {
            turn();
            if (i == 2)
                left();
        }
    }

    private void turn() {
        for (int i = 0; i < FIELD_WIDTH / 2; i++)
            for (int j = i; j < FIELD_WIDTH-i-1; j++)
            {
                int temp = gameTiles[i][j].value;
                gameTiles[i][j].value = gameTiles[j][FIELD_WIDTH-1-i].value;
                gameTiles[j][FIELD_WIDTH-1-i].value = gameTiles[FIELD_WIDTH-1-i][FIELD_WIDTH-1-j].value;
                gameTiles[FIELD_WIDTH-1-i][FIELD_WIDTH-1-j].value = gameTiles[FIELD_WIDTH-1-j][i].value;
                gameTiles[FIELD_WIDTH-1-j][i].value = temp;
            }
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++) {
                if (gameTiles[i][j].value == 0)
                    return true;
                if (j < FIELD_WIDTH - 1 && gameTiles[i][j].value == gameTiles[i][j + 1].value)
                    return true;
                if (i < FIELD_WIDTH - 1 && gameTiles[i][j].value == gameTiles[i + 1][j].value)
                    return true;
            }
        return false;
    }

    private void saveState(Tile[][] tiles) {
        Tile[][] tile = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int i = 0; i < FIELD_WIDTH; i++)
            for (int j = 0; j < FIELD_WIDTH; j++)
                tile[i][j] = new Tile(tiles[i][j].value);

        previousStates.push(tile);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (!previousStates.empty() && !previousScores.empty()) {
            gameTiles = previousStates.pop();
            score = previousScores.pop();
        }
    }

    public void randomMove() {
        switch (((int) (Math.random() * 100)) % 4) {
            case 0: {
                left();
                break;
            }
            case 1: {
                right();
                break;
            }
            case 2: {
                up();
                break;
            }
            case 3: {
                down();
                break;
            }
            default:
                break;
        }
    }
}
