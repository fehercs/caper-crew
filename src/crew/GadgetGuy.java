package crew;

public class GadgetGuy extends CrewMember {
    public GadgetGuy() {
        super(
                "GadgetGuy",
                2,
                0,
                new Skill[]{Skill.COMPUTER_SKILLS, Skill.SPEED, Skill.STEALTH},
                "description",
                6
        );
    }
}
