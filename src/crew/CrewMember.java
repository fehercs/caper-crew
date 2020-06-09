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

        this.role = role;
        this.roleLimit = roleLimit;
        this.skillsBonus = skillsBonus;
        this.specialties = specialties;
        this.description = description;
        this.skills = generateSkills(specialties);
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

    private static TreeMap<Skill, Integer> generateSkills() {
        var map = new TreeMap<Skill, Integer>();
        for (Skill skill : Skill.values()) {
            map.put(skill, GameMain.getRandomInteger(1, 100));
        }
        return map;
    }

    private static TreeMap<Skill, Integer> generateSkills(Skill[] spec) {
        var set = new TreeSet<Integer>();
        var map = new TreeMap<Skill, Integer>();
        for (int i = 0; i < 12; i++) {
            set.add(GameMain.getRandomInteger(1, 100));
        }
        var dit = set.descendingIterator();
        var ait = set.iterator();
        int top = dit.next();
        int bottom = ait.next();
        int counter = 0;
        while (counter < Skill.values().length) {
            var skill = Skill.getARandomSkill();
            if (!map.containsKey(skill)) {
                if (Arrays.asList(spec).contains(skill)) {
                    map.put(skill, top);
                    top = dit.next();
                } else {
                    map.put(skill, bottom);
                    bottom = ait.next();
                }
                counter++;
            }
        }
        return map;
    }

    public String getName() {
        return name;
    }

    public String getRole() {
        return role;
    }

    public int getRoleLimit() {
        return roleLimit;
    }

    public Map<Skill, Integer> getSkills() {
        return skills;
    }

    public int getSkillsBonus() {
        return skillsBonus;
    }

    public Skill[] getSpecialties() {
        return specialties;
    }

    public String getDescription() {
        return description;
    }

    public int getCutPercent() {
        return cutPercent;
    }

    public void increaseSkill(Skill skill, int value) {
        this.skills.put(skill, this.skills.get(skill) + value);
    }
}
