package gameMain;

import crew.Skill;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;

public class Heist {
    private LinkedList<Test> tests;
    private int reward;
    private ArrayList<Test> failedTests;
    private HashSet<Container> succesfullTests;

    public Heist() {
        generateTests();
        this.reward = generateSkillBasedReward();
        this.failedTests = new ArrayList<>();
        this.succesfullTests = new HashSet<>();
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

    private int generateSkillBasedReward() {
        int skillSum = (int)tests.stream()
                .mapToInt(Test::getDifficulty)
                .average()
                .orElse(75);
        return (((skillSum * GameMain.getRandomInteger(800, 1200)) + 999) / 1000 ) * 1000;
    }

    public LinkedList<Test> getTests() {
        return tests;
    }

    public int getReward() {
        return reward;
    }

    public HashSet<Container> getSuccesfullTests() {
        return succesfullTests;
    }

    public void addFailedTest(Test test) {
        failedTests.add(test);
   }

    public void addSuccesfullTest(Container succesfullTest) {
        this.succesfullTests.add(succesfullTest);
    }

    public boolean hasHeistFailed() {
        return succesfullTests.size() == 0;
    }

    @Override
    public String toString() {
        return "Heist{" +
                "tests=" + tests +
                ", reward=" + reward +
                '}';
    }
}
