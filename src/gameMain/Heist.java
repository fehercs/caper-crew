package gameMain;

import crew.Skill;

import java.util.LinkedList;

public class Heist {
    private LinkedList<Test> tests;
    private int reward;

    public Heist() {
        generateTests();
        this.reward = GameMain.getRandomInteger(20, 120) * 1000;
    }

    private void generateTests() {
        this.tests = new LinkedList<>();
        int nTests = GameMain.getRandomInteger(4, 8);
        for (int i = 0; i < nTests; i++) {
            tests.add(new Test(Skill.getRandomSkill()));
        }
        if (GameMain.getRandomInteger(1, 4) == 4) {
            tests.addFirst(new Test(Skill.VEHICLE_SELECTION));
        }
        if (GameMain.getRandomInteger(1, 4) == 1) {
            tests.addFirst(new Test(Skill.DRIVING));
        }
    }

    @Override
    public String toString() {
        return "Heist{" +
                "tests=" + tests +
                ", reward=" + reward +
                '}';
    }
}
