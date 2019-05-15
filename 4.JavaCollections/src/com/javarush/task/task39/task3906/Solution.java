package com.javarush.task.task39.task3906;

/* 
Интерфейсы нас спасут!
*/
public class Solution {
    public static void main(String[] args) {
        SecuritySystem securitySystem = new SecuritySystem();
        ElectricPowerSwitch electricPowerSwitch = new ElectricPowerSwitch(securitySystem);

        electricPowerSwitch.press();
        electricPowerSwitch.press();

        LightBulb lightBulb = new LightBulb();
        ElectricPowerSwitch electricPowerSwitch1 = new ElectricPowerSwitch(lightBulb);

        electricPowerSwitch1.press();
        electricPowerSwitch1.press();
    }
}
