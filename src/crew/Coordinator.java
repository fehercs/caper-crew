package crew;

public class Coordinator extends CrewMember {
    public Coordinator() {
        super(
                "Coordinator",
                1,
                0,
                new Skill[]{Skill.STRATEGY, Skill.REFLEX},
                "description",
                5);
    }
}
