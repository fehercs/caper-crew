import java.util.Map;

public abstract class CrewMember {
    private String name;
    private String role;
    private Map<Skill, Integer> skills;
    private int skillsBonus;
    private Skill[] specialties;
    private String description;
    private int cutPercent;
    private int limit;


}
