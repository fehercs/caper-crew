package gameMain;

import crew.CrewMember;

import java.util.HashSet;
import java.util.Set;

public class Player {
    private final int MONEY_GOAL;
    private int heistsLeft;
    private int money;
    private Set<CrewMember> currentCrew;
    private Heist currentHeist;

    public Player(int MONEY_GOAL) {
        this.MONEY_GOAL = MONEY_GOAL;
        this.heistsLeft = 20;
        this.money = 0;
        this.currentCrew = new HashSet<CrewMember>();
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

    public Set<CrewMember> getCurrentCrew() {
        return currentCrew;
    }

    public Heist getCurrentHeist() {
        return currentHeist;
    }

    public void reduceHeistsByOne() {
        this.heistsLeft--;
    }

    public void addCurrentCrew(Set<CrewMember> selectedCrew) {
        this.currentCrew = selectedCrew;
    }

    public void addHeist(Heist selectedHeist) {
        this.currentHeist = selectedHeist;
    }

    public void addCut(int cut) {
        this.money += cut;
    }
}
