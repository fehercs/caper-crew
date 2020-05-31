package crew;

public enum Skill {
    DRIVING(0),
    VEHICLE_SELECTION(1),
    ACCURACY(2),
    FIREARM_HANDLING(3),
    REFLEX(4),
    STRATEGY(5),
    CHARISMA(6),
    COMPUTER_SKILLS(7),
    SPEED(8),
    STEALTH(9);
    private final int value;

    private Skill(int value) {
        this.value = value;
    }
}
