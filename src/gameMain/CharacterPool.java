package gameMain;

import crew.*;

import java.util.*;

public class CharacterPool {
    private static final int POOL_SIZE = 10;
    public static ArrayList<String> uniqueNameHolder = new ArrayList<>();

    private List<CrewMember> pool = new ArrayList<>();

    public CharacterPool() {
        generatePool();
    }

    public  List<CrewMember> getCharacterPool() {
        return this.pool;
    }

    private void generatePool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(getRandomCrew());
        }
    }

    private CrewMember getRandomCrew() {
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

    public void refreshPool() {
        int[] indexes = new int[5];
        int counter = 0;
        while (counter < 5) {
            int r = GameMain.getRandomInteger(0, 9);
            if (Arrays.stream(indexes).noneMatch(e -> e == r)) {
                indexes[counter] = r;
                counter++;
            }
        }
        int l = this.pool.size();
        for (int i : indexes) {
            if (i >= l) pool.add(getRandomCrew());
            else pool.set(i, getRandomCrew());
        }
    }

    public void removeCharacter(int i) {
        if (i < 10 && i >= 0) {
            pool.remove(i);
        }
    }
}
