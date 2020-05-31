package gameMain;

import java.util.Random;
import crew.*;

public class GameMain {
    private static final Random r = new Random();

    public static void main(String[] args) {
        Game.initiateSelectionState();
    }

    public static int getRandomInteger(int min, int max) {
        return r.nextInt(max + 1 - min) + min;
    }
}
