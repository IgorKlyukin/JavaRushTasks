package com.javarush.task.task23.task2413;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class KeyboardObserver extends Thread {
    private Queue<KeyEvent> keyEvents = new ArrayBlockingQueue<KeyEvent>(100);

    //private JFrame frame;
    static JFrame frame;

    @Override
    public void run() {
        frame = new JFrame("KeyPress Tester");
        frame.setTitle("Transparent JFrame Demo");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //frame.setUndecorated(true);
        frame.setUndecorated(false);//стандартная рамка окна
        //frame.setSize(400, 400);
        frame.setSize((Arkanoid.game.getWidth() * 10) + 17 + 20, (Arkanoid.game.getHeight() * 10) + 40 + 20);
        //frame.setExtendedState(JFrame.MAXIMIZED_BOTH);//открыть на полный экран
        frame.setLayout(new GridBagLayout());

        //frame.setOpacity(0.0f);//прозрачность
        //frame.setVisible(true);

        frame.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                //do nothing
            }

            @Override
            public void focusLost(FocusEvent e) {
                System.exit(0);
            }
        });


        frame.addKeyListener(new KeyListener() {

            public void keyTyped(KeyEvent e) {
                //do nothing
            }

            public void keyReleased(KeyEvent e) {
                //do nothing
            }

            public void keyPressed(KeyEvent e) {
                keyEvents.add(e);
            }
        });
    }


    public boolean hasKeyEvents() {
        return !keyEvents.isEmpty();
    }

    public KeyEvent getEventFromTop() {
        return keyEvents.poll();
    }
}

