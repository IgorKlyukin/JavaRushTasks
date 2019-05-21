package com.javarush.task.task34.task3410.model;

import java.io.*;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private final Path levels;
    //C:\Users\пк\JavaRushTasks\4.JavaCollections\src\com\javarush\task\task34\task3410\res\levels.txt

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        int fcs = Model.FIELD_CELL_SIZE / 2;
        Set<Wall> wallSet = new HashSet<>();
        Set<Box> boxSet = new HashSet<>();
        Set<Home> homeSet = new HashSet<>();
        Player player = null;

        try {
            BufferedReader reader = new BufferedReader(new FileReader(levels.toFile()));
            while (reader.ready()){
                String s = reader.readLine();
                if (s.equals("Maze: " + ((level - 1) % 60 + 1))){
                    while (!(s = reader.readLine()).equals(""));
                    int j = 1;
                    while (!(s = reader.readLine()).equals("")){
                        for (int i = 0, n = s.length(); i < n; i++) {
                            switch (s.charAt(i)){
                                case 'X': {
                                    wallSet.add(new Wall((i * 2 + 1) * fcs, j * fcs));
                                    break;
                                }
                                case '*': {
                                    boxSet.add(new Box((i * 2 + 1) * fcs, j * fcs));
                                    break;
                                }
                                case '.': {
                                    homeSet.add(new Home((i * 2 + 1) * fcs, j * fcs));
                                    break;
                                }
                                case '&': {
                                    boxSet.add(new Box((i * 2 + 1) * fcs, j * fcs));
                                    homeSet.add(new Home((i * 2 + 1) * fcs, j * fcs));
                                    break;
                                }
                                case '@': {
                                    player = new Player((i * 2 + 1) * fcs, j * fcs);
                                    break;
                                }
                                default: break;
                            }
                        }
                        j+=2;
                    }
                }
            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new GameObjects(wallSet, boxSet, homeSet, player);
//        return  new GameObjects(new HashSet<>(Arrays.asList(new Wall(fcs, fcs),
//                                                            new Wall(fcs * 3, fcs),
//                                                            new Wall(fcs * 5, fcs))
//                                                            ),
//                                Collections.singleton(new Box(fcs * 3, fcs * 3)),
//                                Collections.singleton(new Home(fcs * 5, fcs * 3)),
//                                new Player(fcs, fcs * 3)
//                                );

    }
}
