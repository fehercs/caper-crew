package gameMain;

import crew.CrewMember;

import java.util.Scanner;

public class Game {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final Scanner SC = new Scanner(System.in);

    public static volatile GameState gameState;
    public static CharacterPool characterPool;
    public static Heist availableHeist;
    public static Player player;

    public static void initiateSelectionState() {
        gameState = GameState.SELECTION;
        player = new Player(200000);
        characterPool = new CharacterPool();
        availableHeist = new Heist();
        selectionState();
    }

    public static void selectionState() {
//        while (gameState.equals(GameState.SELECTION)) {
//
//        }
        printSelectionFrame();
    }

    public static void  printSelectionFrame() {

        System.out.println(
                "Heists left: "
                        + player.getHeistsLeft()
                        + "\t\t"
                        + "Money collected: "
                        + player.getMoney() + '/' + player.getMONEY_GOAL()
        );
        System.out.println();
        System.out.println(drawAvailableHeist());
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(drawCurrentCrew());
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(drawCharacterPool());
    }

    private static String drawAvailableHeist() {
        BUILDER.setLength(0);
        BUILDER.append("HEIST: ");
        for (Test t : availableHeist.getTests()) {
            BUILDER.append(t.getType());
            BUILDER.append(" | ");
        }
        BUILDER.append("Reward: ").append(availableHeist.getReward()).append("$");
        BUILDER.append("\n--type [SKIP] to skip this heist - this will reduce your remaining heists!--");
        return BUILDER.toString();
    }

    private static String drawCurrentCrew() {
        if (player.getCurrentCrew().size() == 0) {
            return "NO CREW SELECTED";
        } else {
            BUILDER.setLength(0);
            BUILDER.append("Current crew: ");
            for (CrewMember c : player.getCurrentCrew()) {
                BUILDER.append(c);
                BUILDER.append('\n');
            }
            return BUILDER.toString();
        }
    }

    private static String drawCharacterPool() {
        BUILDER.setLength(0);
        int i = 0;
        BUILDER.append("CHARACTER POOL:\n");
        BUILDER.append(String.format("%-5s%-10s%-20s%-10s%-10s%-20s%-10s%-20s%-10s%-10s%-10s%-20s%-10s%-10s\n", "","Name","Role","Cut %","DRIVING","VEHICLE_SELECTION","ACCURACY","FIREARM_HANDLING","REFLEX","STRATEGY","CHARISMA","COMPUTER_SKILLS","SPEED","STEALTH"));
        for (CrewMember c : characterPool.getCharacterPool()) {
            String index = "[" + i + "]";
            String name = c.getName();
            String role = c.getRole().toUpperCase();
            int[] s = new int[10];
            c.getSkills().forEach((skill, integer) -> s[skill.getValue()] = integer);
            BUILDER.append(String.format("%-5s%-10s%-20s%-10d%-10d%-20d%-10d%-20d%-10d%-10d%-10d%-20d%-10d%-10d\n", index,name,role,c.getCutPercent(),s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7],s[8],s[9]));
            i++;
        }
        BUILDER.append('\n')
                .append("--Enter [0-9] to add a crew member to your team!--");
        return BUILDER.toString();
    }
}
