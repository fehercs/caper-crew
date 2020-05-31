package gameMain;

import crew.*;

import java.util.Arrays;

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

    private static CrewMember getRandomCrew() {
        int r = GameMain.getRandomInteger(0, 100);
        if (r < 6) {
            //Partner in Crime
            return new PartnerInCrime();
        } else if (r < 12) {
            //Hacker
            return new Hacker();
        } else if (r < 18) {
            //Coordinator
            return new Coordinator();
        } else if (r < 30) {
            //Distraction
            return new Distraction();
        } else if (r < 42) {
            //Driver
            return new Driver();
        } else if (r < 53) {
            //ConMan
            return new ConMan();
        } else if (r < 65) {
            //Burglar
            return new Burglar();
        } else if (r < 77) {
            //GadgetGuy
            return new GadgetGuy();
        } else {
            //GunMan
            return new Gunman();
        }
    }

    public static void refreshPool() {
        int[] indexes = new int[5];
        int counter = 0;
        while (counter < 5) {
            int r = GameMain.getRandomInteger(0, 9);
            if (!Arrays.stream(indexes).anyMatch(e -> e == r)) {
                indexes[counter] = r;
                counter++;
            }
        }
        System.out.println(Arrays.toString(indexes));
        for (int i : indexes) {
            pool[i] = getRandomCrew();
        }
    }

    public static void switchCharacter(int i, CrewMember crew) {
        if (i < 10 && i >= 0) {
            pool[i] = crew;
        }
    }
}
