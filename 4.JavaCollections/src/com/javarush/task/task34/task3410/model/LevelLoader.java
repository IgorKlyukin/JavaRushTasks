package com.javarush.task.task34.task3410.model;

import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;

public class LevelLoader {
    private final Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        int fcs = Model.FIELD_CELL_SIZE / 2;
        return  new GameObjects(new HashSet<>(Arrays.asList(new Wall(fcs, fcs),
                                                            new Wall(fcs * 3, fcs),
                                                            new Wall(fcs * 5, fcs))
                                                            ),
                                Collections.singleton(new Box(fcs * 3, fcs * 3)),
                                Collections.singleton(new Home(fcs * 5, fcs * 3)),
                                new Player(fcs, fcs * 3)
                                );

    }
}
