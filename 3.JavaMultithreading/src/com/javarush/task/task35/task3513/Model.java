package com.javarush.task.task35.task3513;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Model {
    private static final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    protected int score = 0;
    protected int maxTile = 0;

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
        boolean flag = false;
        for (int i = 0; i < FIELD_WIDTH; i++) {
            flag |= compressTiles(gameTiles[i]) | mergeTiles(gameTiles[i]);
        }
        if (flag)
            addTile();
    }
}
