public class Player {
    private final int MONEY_GOAL;
    private int heistsLeft;
    private int money;

    public Player(int MONEY_GOAL) {
        this.MONEY_GOAL = MONEY_GOAL;
        this.heistsLeft = 20;
        this.money = 0;
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

    public void reduceHeistsByOne() {
        this.heistsLeft--;
    }

    public void addCut(int cut) {
        this.money += cut;
    }
}
