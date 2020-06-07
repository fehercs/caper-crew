package gameMain;

import crew.CrewMember;
import crew.Skill;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.concurrent.locks.Condition;

public class Heist {
    private LinkedList<Test> tests;
    private int reward;
    private ArrayList<Test> failedTests;
    private ArrayList<Container> succesfullTests;

    public Heist() {
        generateTests();
        this.reward = generateSkillBasedReward();
        this.failedTests = new ArrayList<>();
        this.succesfullTests = new ArrayList<>();
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
        int skillSum = tests.stream()
                .mapToInt(Test::getDifficulty)
                .sum();
        return (((skillSum * GameMain.getRandomInteger(80, 180)) + 999) / 1000 ) * 1000;
    }

    public LinkedList<Test> getTests() {
        return tests;
    }

    public int getReward() {
        return reward;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public ArrayList<Test> getFailedTests() {
        return failedTests;
    }

    public ArrayList<Container> getSuccesfullTests() {
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

    public void addFailedTests(ArrayList<Test> failedTests) {
        this.failedTests = failedTests;
    }

    @Override
    public String toString() {
        return "Heist{" +
                "tests=" + tests +
                ", reward=" + reward +
                '}';
    }
}
