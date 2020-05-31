package crew;

public class Burglar extends CrewMember {
    public Burglar() {
        super(
                "Burglar",
                2,
                0,
                new Skill[]{Skill.SPEED, Skill.STEALTH},
                "description",
                5
        );
    }
}
