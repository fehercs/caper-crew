package gameMain;

import java.util.Random;

public class GameMain {
    private static final Random r = new Random();

    public static void main(String[] args) {
        Menu.renderMenu();
        Game.initiateSelectionState();
        while (true) {
            Game.selectionState();
            Game.heistState();
            Game.summaryState();
        }
    }



    public static int getRandomInteger(int min, int max) {
        return r.nextInt(max + 1 - min) + min;
    }
}
