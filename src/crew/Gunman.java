package crew;

public class Gunman extends CrewMember{
    public Gunman() {
        super(
                "Gunman",
                4,
                0,
                new Skill[]{Skill.FIREARM_HANDLING, Skill.ACCURACY, Skill.REFLEX},
                "kajchal",
                3
        );
    }
}
