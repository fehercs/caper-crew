import javax.swing.*;
import java.util.Random;

public class GameMain {
    public static void main(String[] args) {
        JFrame frame = new CaperCrewGameGUI("Cape Crew");
        frame.setVisible(true);
    }

    public static int getRandomInteger(int min, int max) {
        return new Random().nextInt(max + 1 - min) + min;
    }
}
