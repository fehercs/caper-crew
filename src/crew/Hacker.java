package crew;

public class Hacker extends CrewMember {
    public Hacker() {
        super(
                "Hacker",
                1,
                0,
                new Skill[]{Skill.COMPUTER_SKILLS},
                "description",
                5
        );
    }
}
