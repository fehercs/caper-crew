package gameMain;

import javax.swing.*;
import java.util.Arrays;
import java.util.Random;

import crew.*;
import gui.*;

public class GameMain {
    public static void main(String[] args) {
//        JFrame frame = new CaperCrewGameGUI("Cape Crew");
//        frame.setVisible(true);
        CharacterPool.generatePool();
        System.out.println(Arrays.toString(CharacterPool.getCharacterPool()));
    }

    public static int getRandomInteger(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}
