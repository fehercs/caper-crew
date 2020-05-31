package crew;

public class Distraction extends CrewMember {
    public Distraction() {
        super(
                "Distraction",
                2,
                0,
                new Skill[]{Skill.ACCURACY, Skill.CHARISMA},
                "description",
                4
        );
    }
}
