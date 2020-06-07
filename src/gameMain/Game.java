package gameMain;

import crew.CrewMember;
import crew.Skill;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class Game {
    private static final StringBuilder BUILDER = new StringBuilder();
    private static final Scanner SC = new Scanner(System.in);

    public static volatile GameState gameState;
    public static CharacterPool characterPool;
    public static Heist availableHeist;
    public static Player player;
    public static Test nextTest;
    public static boolean prevTestFailed;

    public static void initiateSelectionState() {
        gameState = GameState.SELECTION;
        player = new Player(200000);
        characterPool = new CharacterPool();
        availableHeist = new Heist();
        selectionState();
    }

    public static void heistState() {
        availableHeist.getTests().forEach(test -> {
            nextTest = test;
            clearConsole();
            printHeistState();
            promptEnterKey();
            if (getCrewForTest().isPresent()) {
                attemptTest(getCrewForTest().get());
            } else {
               availableHeist.addFailedTest(test);
            }
        });
        gameState = GameState.SUMMARY;
    }

    private static void attemptTest(ArrayList<CrewMember> crewForTest) {
        if (getSkillPointsForTest(crewForTest) < nextTest.getDifficulty()) {
            availableHeist.addFailedTest(nextTest);
        } else {
            availableHeist.addSuccesfullTest(new Container(nextTest, crewForTest));
        }
    }

    private static int getSkillPointsForTest(List<CrewMember> crewForTest) {
       return crewForTest
                .stream()
                .mapToInt(crewMember -> crewMember.getSkills().get(nextTest.getType()))
                .sum()
                ;
    }

    public static void selectionState() {
        while (gameState.equals(GameState.SELECTION)) {
            clearConsole();
            printSelectionFrame();
            selectionStateInput();
        }
    }

    public static void summaryState() {
        if (availableHeist.hasHeistFailed()) {

        } else {

        }
    }

    private static void selectionStateInput() {
        String line = SC.nextLine();
        switch (line.toUpperCase()) {
            case "SKIP":
                skipHeist();
                break;
            case "REPLACE":
                characterPool.refreshPool();
                if (player.getHeistsLeft() < 20) {
                    player.reduceMoney(5);
                }
                break;
            case "DONE":
                if (confirm()) gameState = GameState.HEIST;
                break;
            case "0":
                addCharacter(0);
                break;
            case "1":
                addCharacter(1);
                break;
            case "2":
                addCharacter(2);
                break;
            case "3":
                addCharacter(3);
                break;
            case "4":
                addCharacter(4);
                break;
            case "5":
                addCharacter(5);
                break;
            case "6":
                addCharacter(6);
                break;
            case "7":
                addCharacter(7);
                break;
            case "8":
                addCharacter(8);
                break;
            case "9":
                addCharacter(9);
                break;
        }
    }

    private static void addCharacter(int i) {
        player.addCrewMember(characterPool.getCharacterPool().get(i));
        player.setCurrentCut(player.getCurrentCut() - characterPool.getCharacterPool().get(i).getCutPercent());
        characterPool.removeCharacter(i);
    }

    public static void  printSelectionFrame() {

        System.out.println(drawHeader());
        System.out.println();
        System.out.println(drawAvailableHeist());
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(drawCurrentCrew());
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(drawCharacterPool());
        System.out.println(drawSelectionFooter());
    }

    private static void printHeistState() {
        System.out.println(drawHeader());
        System.out.println();
        System.out.println(drawAvailableHeist());
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(drawNextTest());
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

    private static String drawHeader() {
        BUILDER.setLength(0);
        BUILDER
                .append("Heists left: ")
                .append(player.getHeistsLeft())
                .append("\t\t\t")
                .append("Money collected: ")
                .append(player.getMoney().isPresent() ? player.getMoney() : "Nada")
                .append("$/")
                .append(player.getMONEY_GOAL())
                .append("$\t\t\t")
                .append("Your Cut: ")
                .append(player.getCurrentCut())
                .append("%")
        ;
        return BUILDER.toString();
    }

    private static String drawAvailableHeist() {
        BUILDER.setLength(0);
        BUILDER.append("HEIST: ");
        for (Test t : availableHeist.getTests()) {
            BUILDER.append(t.getType());
            BUILDER.append(" | ");
        }
        BUILDER
                .append("Reward: ")
                .append(availableHeist.getReward())
                .append("$ - ")
                .append("( ")
                .append((availableHeist.getReward()/100) * player.getCurrentCut())
                .append("$ )")
        ;
        return BUILDER.toString();
    }

    private static String drawCurrentCrew() {
        if (player.getCurrentCrew().size() == 0) {
            return "NO CREW SELECTED";
        } else {
            char a = 65;
            BUILDER.setLength(0);
            BUILDER.append("CURRENT CREW:\n");
            BUILDER.append(String.format("%-5s%-10s%-20s%-10s%-10s%-20s%-10s%-20s%-10s%-10s%-10s%-20s%-10s%-10s\n", "","Name","Role","Cut %","DRIVING","VEHICLE_SELECTION","ACCURACY","FIREARM_HANDLING","REFLEX","STRATEGY","CHARISMA","COMPUTER_SKILLS","SPEED","STEALTH"));
            for (CrewMember c : player.getCurrentCrew()) {
                String index = "[" + a + "]";
                String name = c.getName();
                String role = c.getRole().toUpperCase();
                String[] s = new String[10];
                c.getSkills().forEach((skill, integer) -> {
                    for (Skill spec : c.getSpecialties()) {
                        if (skill.getValue() == spec.getValue()) {
                            s[skill.getValue()] = integer.toString() + " *";
                            break;
                        }
                        else s[skill.getValue()] = integer.toString();
                    }
                });
                BUILDER.append(String.format("%-5s%-10s%-20s%-10s%-10s%-20s%-10s%-20s%-10s%-10s%-10s%-20s%-10s%-10s\n", index,name,role,c.getCutPercent(),s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7],s[8],s[9]));
                a++;
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
            String[] s = new String[10];
            c.getSkills().forEach((skill, integer) -> {
                for (Skill spec : c.getSpecialties()) {
                    if (skill.getValue() == spec.getValue()) {
                        s[skill.getValue()] = integer.toString() + " *";
                        break;
                    }
                    else s[skill.getValue()] = integer.toString();
                }
            });
            BUILDER.append(String.format("%-5s%-10s%-20s%-10d%-10s%-20s%-10s%-20s%-10s%-10s%-10s%-20s%-10s%-10s\n", index,name,role,c.getCutPercent(),s[0],s[1],s[2],s[3],s[4],s[5],s[6],s[7],s[8],s[9]));
            i++;
        }
        return BUILDER.toString();
    }

    private static String drawSelectionFooter() {
        BUILDER.setLength(0);
        BUILDER
                .append("\n-- Type [SKIP] to skip this heist - this will reduce your remaining heists!\n")
                .append("-- Enter [0-9] to add a crew member to your team!\n")
                .append("-- Enter [REPLACE] to replace 5 characters in the pool for 5% of your money!\n")
                .append(player.getHeistsLeft() == 20 ? "Yes it's free in the first round... Good Luck!" : "")
                .append("\n-- Type [DONE] to start the Heist!\n")
                .append("Enter command here:")
        ;
        return BUILDER.toString();
    }

    private static void skipHeist() {
        availableHeist = new Heist();
        player.reduceHeistsByOne();
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static boolean confirm() {
        clearConsole();
        System.out.println("Are you sure?\n-- Type [Y] if yes!");
        return SC.nextLine().equalsIgnoreCase("Y");
    }

    private static void promptEnterKey(){
        System.out.println("Press \"ENTER\" to continue...");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String drawNextTest() {
        BUILDER.setLength(0);
        BUILDER
                .append("UPCOMING TEST:\t")
                .append(nextTest.getType().toString().toUpperCase())
                .append("\tDifficulty: ")
                .append(nextTest.getDifficulty())
        ;
        return BUILDER.toString();
    }

    private static Optional<ArrayList<CrewMember>> getCrewForTest() {
        var crewForTest = new ArrayList<CrewMember>();
        player.getCurrentCrew().forEach(crewMember -> {
            for (Skill s : crewMember.getSpecialties()) {
                if (s.getValue() == nextTest.getType().getValue()) crewForTest.add(crewMember);
            }
        });
        if (crewForTest.size() > 0) {
            return Optional.of(crewForTest);
        }
        return Optional.empty();
    }


}
