package crew;

import java.util.*;

import gameMain.GameMain;

public abstract class CrewMember {
    private String name;
    private String role;
    private int roleLimit;
    private Map<Skill, Integer> skills;
    private int skillsBonus;
    private Skill[] specialties;
    private String description;
    private int cutPercent;

    public CrewMember(String role, int roleLimit, int skillsBonus, Skill[] specialties, String description, int roleCut) {
        this.name = getRandomName();
        this.skills = generateSkills();

        this.role = role;
        this.roleLimit = roleLimit;
        this.skillsBonus = skillsBonus;
        this.specialties = specialties;
        this.description = description;
        this.cutPercent = (Collections.max(skills.values()) / 10) + roleCut;
    }

    @Override
    public String toString() {
        return "CrewMember{" +
                "name='" + name + '\'' +
                ", role='" + role + '\'' +
                ", roleLimit=" + roleLimit +
                ", skills=" + skills +
                ", skillsBonus=" + skillsBonus +
                ", specialties=" + Arrays.toString(specialties) +
                ", description='" + description + '\'' +
                ", cutPercent=" + cutPercent +
                '}';
    }

    private static String getRandomName() {
        var isFemale = new Random().nextBoolean();
        return isFemale ? "Ms. " + (char) GameMain.getRandomInteger(65, 90) : "Mr. " + (char) GameMain.getRandomInteger(65, 90);
    }

    private static Map<Skill, Integer> generateSkills() {
        var map = new HashMap<Skill, Integer>();
        for (Skill skill : Skill.values()) {
            map.put(skill, GameMain.getRandomInteger(1, 100));
        }
        return Map.copyOf(map);
    }
}
