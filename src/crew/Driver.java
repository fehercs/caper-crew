package crew;

public class Driver extends CrewMember {

    public Driver() {
        super(
                "Driver",
                2,
                0,
                new Skill[]{Skill.DRIVING, Skill.VEHICLE_SELECTION},
                "efioajc",
                4
        );
    }
}
