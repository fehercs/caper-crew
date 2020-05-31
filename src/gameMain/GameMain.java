package gameMain;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

import crew.*;
import gui.*;

public class GameMain {
    private static final Random r = new Random();

    public static void main(String[] args) {
//        JFrame frame = new CaperCrewGameGUI("Cape Crew");
//        frame.setVisible(true);
        System.out.println(new Heist().toString());

    }

    public static int getRandomInteger(int min, int max) {
        return r.nextInt(max + 1 - min) + min;
    }
}
