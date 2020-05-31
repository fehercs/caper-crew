package crew;

public class ConMan extends CrewMember {
    public ConMan() {
        super(
                "ConMan",
                2,
                0,
                new Skill[]{Skill.CHARISMA},
                "description",
                5
        );
    }
}
