package com.javarush.task.task25.task2502;

import java.util.*;

/* 
Машину на СТО не повезем!
*/
public class Solution {
    public static enum Wheel {
        FRONT_LEFT,
        FRONT_RIGHT,
        BACK_LEFT,
        BACK_RIGHT
    }

    public static class Car {
        protected List<Wheel> wheels;

        public Car() throws Exception{
            wheels = new ArrayList<>();
            try {
                for (String string : loadWheelNamesFromDB()) {
                    if (!wheels.contains(Wheel.valueOf(string))) {
                        wheels.add(Wheel.valueOf(string));
                    }
                    else {
                        throw new IllegalAccessException();
                    }
                }
                if (wheels.size() != 4) {
                    throw new IllegalAccessException();
                }
            }
            catch (IllegalArgumentException e){
                throw new Exception();
            }
        }

        protected String[] loadWheelNamesFromDB() {
            //this method returns mock data
            return new String[]{"FRONT_LEFT", "FRONT_RIGHT", "BACK_LEFT", "BACK_RIGHT"};
        }
    }

    public static void main(String[] args) {
    }
}
