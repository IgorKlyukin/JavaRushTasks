package com.javarush.task.task21.task2113;

import java.util.ArrayList;
import java.util.List;

public class Hippodrome {

    public static void main(String[] args) {
        List<Horse> list = new ArrayList<>();
        game = new Hippodrome(list);
        for (int i = 0; i < 3; i++) {
            list.add(new Horse("Horse" + (i + 1), 3, 0));
        }
        game.run();
        game.printWinner();
    }
    private List<Horse> horses = new ArrayList<>();
    public static Hippodrome game;

    public List<Horse> getHorses(){
        return horses;
    }

    public Hippodrome(List<Horse> horses){
        this.horses = horses;
    }

    public void move(){
        for (Horse horse : getHorses()) {
            horse.move();
        }
    }

    public void print(){
        for (Horse horse : getHorses()) {
            horse.print();
        }
        for (int i = 0; i < 10; i++) {
            System.out.println();
        }
    }

    public void run(){
        for (int i = 0; i < 100; i++) {
            move();
            print();
            try {
                Thread.sleep(200);
            }catch (InterruptedException e){}
        }
    }

    public Horse getWinner(){
        Horse horse = horses.get(0);
        for (Horse h :
                horses) {
            if (h.getDistance() > horse.getDistance()) {
                horse = h;
            }
        }
        return horse;
    }

    public void printWinner(){
        System.out.println("Winner is " + getWinner().getName() + "!");
    }

}
