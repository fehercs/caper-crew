package gameMain;

import crew.Skill;

public class Test {
    private Skill type;
    private int difficulty;

    public Test (Skill type) {
        this.type = type;
        this.difficulty = GameMain.getRandomInteger(25, 125);
    }

    public Skill getType() {
        return type;
    }

    public int getDifficulty() {
        return difficulty;
    }

    @Override
    public String toString() {
        return "Test{" +
                "type=" + type +
                ", difficulty=" + difficulty +
                '}';
    }
}
