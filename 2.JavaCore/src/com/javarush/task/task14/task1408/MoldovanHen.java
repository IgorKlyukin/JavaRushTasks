package com.javarush.task.task14.task1408;

public class MoldovanHen extends Hen {
    public int getCountOfEggsPerMonth() {
        return 2;
    }

    public String getDescription() {
        return super.getDescription() + " Моя страна - Moldova. Я несу " + this.getCountOfEggsPerMonth() + " яиц в месяц.";
    }
}