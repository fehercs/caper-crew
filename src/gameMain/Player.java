package gameMain;

import crew.CrewMember;

import java.util.ArrayList;
import java.util.List;

public class Player {
    private final int MONEY_GOAL;
    private int heistsLeft;
    private int money;
    private List<CrewMember> currentCrew;
    private int currentCut;

    public Player(int MONEY_GOAL) {
        this.MONEY_GOAL = MONEY_GOAL;
        this.heistsLeft = 20;
        this.money = 0;
        this.currentCrew = new ArrayList<>();
        this.currentCut = 100;
    }

    public int getMONEY_GOAL() {
        return MONEY_GOAL;
    }

    public int getHeistsLeft() {
        return heistsLeft;
    }

    public int getMoney() {
        return money;
    }

    public List<CrewMember> getCurrentCrew() {
        return currentCrew;
    }

    public int getCurrentCut() {
        return currentCut;
    }

    public void setCurrentCut(int currentCut) {
        this.currentCut = currentCut;
    }

    public void reduceHeistsByOne() {
        this.heistsLeft--;
    }

    public void addCrewMember(CrewMember crew) {
        this.currentCrew.add(crew);
    }

    public void addCurrentCrew(List<CrewMember> selectedCrew) {
        this.currentCrew = selectedCrew;
    }

    public void addCut(int cut) {
        this.money += cut;
    }

    public void reduceMoney(int percent) {
        this.money -= (this.money / 100) * percent;
    }
}
