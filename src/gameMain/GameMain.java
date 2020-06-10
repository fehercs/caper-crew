package gameMain;

import java.util.Random;

public class GameMain {
    private static final Random r = new Random();

    public static void main(String[] args) {
        Menu.renderMenu();
        Game.initiateSelectionState();
        while (!(Game.gameState == GameState.OVER)) {
            Game.selectionState();
            if (!(Game.gameState == GameState.OVER)) {
                Game.heistState();
                Game.summaryState();
                checkGameOver();
            }
        }
        Menu.renderGameOver();
    }

    public static void checkGameOver() {
        if (Game.player.getMoney().isPresent()) {
            int money = Game.player.getMoney().get();
            if (money >= Game.player.getMONEY_GOAL()) {
                Game.gameState = GameState.OVER;
            }
        }
        if (Game.player.getHeistsLeft() <= 0) {
            Game.gameState = GameState.OVER;
        }
    }

    public static int getRandomInteger(int min, int max) {
        return r.nextInt(max + 1 - min) + min;
    }
}
