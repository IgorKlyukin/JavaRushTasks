package com.javarush.task.task14.task1408;

public class BelarusianHen extends Hen {
    public int getCountOfEggsPerMonth() {
        return 1;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - Belarus. Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}
