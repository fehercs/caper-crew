package gameMain;

import crew.*;

public class CharacterPool {
    private static CrewMember[] pool = new CrewMember[10];

    public static CrewMember[] getCharacterPool() {
        return pool;
    }

    public static void generatePool() {
        for (int i = 0; i < pool.length; i++) {
            int r = GameMain.getRandomInteger(0, 100);
            if (r < 6) {
                //Partner in Crime
                pool[i] = new PartnerInCrime();
            } else if (r < 12) {
                //Hacker
                pool[i] = new Hacker();
            } else if (r < 18) {
                //Coordinator
                pool[i] = new Coordinator();
            } else if (r < 30) {
                //Distraction
                pool[i] = new Distraction();
            } else if (r < 42) {
                //Driver
                pool[i] = new Driver();
            } else if (r < 53) {
                //ConMan
                pool[i] = new ConMan();
            } else if (r < 65) {
                //Burglar
                pool[i] = new Burglar();
            } else if (r < 77) {
                //GadgetGuy
                pool[i] = new GadgetGuy();
            } else {
                //GunMan
                pool[i] = new Gunman();
            }
        }
    }
}
