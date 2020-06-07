package gameMain;

import crew.CrewMember;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Player {
    private final int MONEY_GOAL;
    private int heistsLeft;
    private int money;
    private int gainedReward;
    private List<CrewMember> currentCrew;
    private ArrayList<CrewMember> deadCrew;
    private int currentCut;


    public Player(int MONEY_GOAL) {
        this.MONEY_GOAL = MONEY_GOAL;
        this.heistsLeft = 20;
        this.money = 0;
        this.currentCrew = new ArrayList<>();
        this.deadCrew = new ArrayList<>();
        this.currentCut = 100;
    }

    public int getMONEY_GOAL() {
        return MONEY_GOAL;
    }

    public int getHeistsLeft() {
        return heistsLeft;
    }

    public Optional<Integer> getMoney() {
        if (this.money > 0) return Optional.of(this.money);
        return Optional.empty();
    }

    public void setMoney(int money) {
        this.money = money;
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

    public ArrayList<CrewMember> getDeadCrew() {
        return deadCrew;
    }

    public void addDeadCrew(CrewMember dead) {
        this.deadCrew.add(dead);
    }

    public int getGainedReward() {
        return gainedReward;
    }

    public void setGainedReward(int gainedReward) {
        this.gainedReward = gainedReward;
    }

    public void reduceGainedReward(int red) {
        if (this.gainedReward - red <= 0){
            this.gainedReward = 0;
        } else {
            this.gainedReward -= red;
        }
    }

    public void reduceHeistsByOne() {
        this.heistsLeft--;
    }

    public void addCrewMember(CrewMember crew) {
        this.currentCut -= crew.getCutPercent();
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

    public void removeCrew(CrewMember crew) {
        this.currentCrew.remove(crew);
    }

    public void removeCrew(int index) {
        this.currentCut += this.currentCrew.get(index).getCutPercent();
        this.currentCrew.remove(index);
    }
}
