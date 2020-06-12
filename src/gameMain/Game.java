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

    private static StringBuilder tempHolder;

    public static volatile GameState gameState;
    public static CharacterPool characterPool;
    public static Heist availableHeist;
    public static Player player;
    public static Test nextTest;
    private static int nRandomFail;

    public static void initiateSelectionState() {
        gameState = GameState.SELECTION;
        player = new Player(200000);
        characterPool = new CharacterPool();
    }

    public static void heistState() {
        player.setGainedReward(availableHeist.getReward());
        tempHolder = new StringBuilder();
        clearConsole();
        printHeistState();
        availableHeist.getTests().forEach(test -> {
            nRandomFail = 0;
            nextTest = test;
            String skillPointsForTest = "None";
            if (getCrewForTest().isPresent()) {
                skillPointsForTest = Integer.toString(attemptTest(getCrewForTest().get()));
            } else {
                testFailedResult(test);
                availableHeist.addFailedTest(test);
            }
            printHeistTestState(skillPointsForTest);
        });
        promptEnterKey();
        gameState = GameState.SUMMARY;
    }

    private static int attemptTest(ArrayList<CrewMember> crewForTest) {
        int skillPoints = getSkillPointsForTest(crewForTest, nextTest);
        if ( skillPoints < nextTest.getDifficulty()) {
            availableHeist.addFailedTest(nextTest);
            testFailedResult(nextTest);
        } else {
            availableHeist.addSuccesfullTest(new Container(nextTest, crewForTest));
        }
        return skillPoints;
    }

    private static boolean attemptRandomTest(Skill type) {
        nRandomFail++;
        Test test = new Test(type);
        if (getCrewForRandomTest(test).size() == 0 || nRandomFail > 5) {
            randomTestFailedResult();
            return true;
        }
        if (getSkillPointsForTest(getCrewForRandomTest(test), test) < test.getDifficulty()) {
            testFailedResult(test);
            return false;
        } else {
            return true;
        }
    }

    private static void randomTestFailedResult() {
        tempHolder.append("Random test failed too many times:\n\tAll money has been lost!\n");
        player.reduceGainedReward(availableHeist.getReward());
    }

    private static void testFailedResult(Test test) {
        switch (test.getType()) {
            case DRIVING:
                int nDriver = (int) player
                        .getCurrentCrew()
                        .stream()
                        .filter(crewMember -> crewMember.getRole().equals("Driver"))
                        .count();
                if (nDriver < 2) {
                    player.reduceGainedReward(availableHeist.getReward());
                    tempHolder.append("DRIVING test failed and you had only one driver -> All money has been lost.\n");
                } else {
                    player.reduceGainedReward(availableHeist.getReward() / 2);
                    tempHolder.append("DRIVING test failed but you had more than one drivers  -> Half of the money has been lost.\n");
                }
                break;
            case VEHICLE_SELECTION:
                if (attemptRandomTest(Skill.FIREARM_HANDLING)) {
                    tempHolder.append("VEHICLE SELECTION test failed and you got into a firefight -> You successfully escaped.\n");
                } else {
                    player.reduceGainedReward(availableHeist.getReward());
                    tempHolder.append("VEHICLE SELECTION test failed. You couldn't escape and got into a firefight -> All money has been lost.\n");
                }
                break;
            case ACCURACY:
                tempHolder.append("ACCURACY test failed: Friendly fire.");
                randomCrewDead(1, 100);
                tempHolder.append("\tRewards reduced by 20%.\n");
                player.reduceGainedReward((availableHeist.getReward() / 100) * 20);
                break;
            case FIREARM_HANDLING:
                player.reduceGainedReward((availableHeist.getReward() / 100) * 10);
                tempHolder.append("FIREARM HANDLING test failed: You got into a firefight with not enough firepower.\n");
                randomCrewDead(2, 50);
                tempHolder.append("\tRewards reduced by 10%.\n");
                break;
            case REFLEX:
                tempHolder.append("REFLEX test failed: random speed test.\n");
                if (!attemptRandomTest(Skill.SPEED)) {
                    randomCrewDead(1, 100);
                    player.reduceGainedReward((availableHeist.getReward() / 100) * 70);
                    tempHolder.append("\tRewards reduced by 70%.\n");
                }
                break;
            case STRATEGY:
                tempHolder.append("STRATEGY test failed: another random strategy test -> Passed.\n");
                attemptRandomTest(Skill.STRATEGY);
                player.reduceGainedReward((availableHeist.getReward() / 100) * 25);
                tempHolder.append("\tRewards reduced by 25%.\n");
                break;
            case CHARISMA:
                tempHolder.append("CHARISMA test failed: another random strategy test.\n");
                attemptRandomTest(Skill.STRATEGY);
                player.reduceGainedReward((availableHeist.getReward() / 100) * 15);
                tempHolder.append("\tRewards reduced by 15%.\n");
                break;
            case COMPUTER_SKILLS:
                tempHolder.append("COMPUTER SKILLS test failed:\n\tAll money has been lost\n");
                player.reduceGainedReward(availableHeist.getReward());
                break;
            case SPEED:
                tempHolder.append("SPEED test failed:\n\tRewards reduced by 30%\n");
                player.reduceGainedReward((availableHeist.getReward() / 100) * 30);
                break;
            case STEALTH:
                tempHolder.append("STEALTH test failed:\n\tRewards reduced by 40%\n");
                player.reduceGainedReward((availableHeist.getReward() / 100) * 40);
                break;
        }
    }

    private static void randomCrewDead(int nDead, int percent) {
        for (int i = 0; i < nDead; i++) {
            if (GameMain.getRandomInteger(0, 100) <= percent) {
                if (player.getCurrentCrew().size() > 0) {
                    CrewMember dead = player.getCurrentCrew().get(GameMain.getRandomInteger(0, player.getCurrentCrew().size() - 1));
                    player.addDeadCrew(dead);
                    player.removeCrew(dead);
                    tempHolder
                            .append('\t')
                            .append("A crew member died: ")
                            .append(dead.getName())
                            .append('\n')
                    ;
                }
            }
        }
    }

    private static int getSkillPointsForTest(List<CrewMember> crewForTest, Test test) {
       return crewForTest
                .stream()
                .mapToInt(crewMember -> crewMember.getSkills().get(test.getType()))
                .sum()
                ;
    }

    public static void selectionState() {
        availableHeist = new Heist();
        gameState = GameState.SELECTION;
        while (gameState.equals(GameState.SELECTION)) {
            clearConsole();
            printSelectionFrame();
            selectionStateInput();
            GameMain.checkGameOver();
        }
    }

    public static void summaryState() {
        player.reduceHeistsByOne();
        int reward = (player.getGainedReward() / 100) * player.getCurrentCut();
        player.setMoney(player.getMoney().isPresent() ? player.getMoney().get() + reward : reward);
        clearConsole();
        renderSummary();
        characterLevelUp();
        promptEnterKey();
    }

    private static void renderSummary() {
        BUILDER.setLength(0);
        drawHeader();
        BUILDER.append("-------------------------------------------------------------------------------------------------\n");
        BUILDER.append("COMPLETED ");
        drawAvailableHeist(false);
        BUILDER
                .append("Collected Reward: ")
                .append((player.getGainedReward() / 100) * player.getCurrentCut())
                .append('\n')

                .append("-------------------------------------------------------------------------------------------------\n")
        ;
        if (!availableHeist.hasHeistFailed()) {
            BUILDER.append("SUCCESFULL TESTS:\n");
            availableHeist.getSuccesfullTests().forEach( container -> {
            BUILDER
                    .append(container.getTest().getType().toString())
                    .append(" | ")
            ;
            });
            BUILDER.setLength(BUILDER.length() - 3);
            BUILDER
                    .append('\n')
                    .append("-------------------------------------------------------------------------------------------------\n");
        }
        BUILDER.append(tempHolder.toString())
                .append("\nEvery crew member got a skill point for all their skills.\n")
                .append("Characters participating in a successful test also gained 5 skill points for that skill.\n")
        ;
        System.out.println(BUILDER.toString());
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
            case "READY":
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
            case "A":
                removeCharacter(0);
                break;
            case "B":
                removeCharacter(1);
                break;
            case "C":
                removeCharacter(2);
                break;
            case "D":
                removeCharacter(3);
                break;
            case "E":
                removeCharacter(4);
                break;
            case "F":
                removeCharacter(5);
                break;
            case "G":
                removeCharacter(6);
                break;
            case "H":
                removeCharacter(7);
                break;
            case "I":
                removeCharacter(8);
                break;
            case "J":
                removeCharacter(9);
                break;
        }
    }

    private static void addCharacter(int index) {
        if (characterPool.getCharacterPool().size() > index) {
            var character =characterPool.getCharacterPool().get(index);
            if (player.getCurrentCut() - character.getCutPercent() > 0) {
                if (character.getRoleLimit() > nCharacter(character)) {
                    player.addCrewMember(character);
                    characterPool.removeCharacter(index);
                } else {
                    clearConsole();
                    BUILDER.setLength(0);
                    BUILDER
                            .append("CANNOT ADD CREW MEMBER -- character role ")
                            .append(character.getRole().toUpperCase())
                            .append(" has a limit of ")
                            .append(character.getRoleLimit())
                            .append("!\n")
                            ;
                    System.out.println(BUILDER.toString());
                    promptEnterKey();
                }
            } else {
                clearConsole();
                System.out.println("CANNOT ADD CREW MEMBER -- your cut cannot be at or bellow 0%!");
                promptEnterKey();
            }
        }
    }

    private static int nCharacter(CrewMember character) {
        return (int) player.getCurrentCrew().stream()
                .filter(crewMember -> character.getRole().equals(crewMember.getRole()))
                .count();
    }

    private static void removeCharacter(int index) {
        if (player.getCurrentCrew().size() > index) {
            player.removeCrew(index);
        }
    }

    public static void  printSelectionFrame() {

        System.out.println(drawHeader());
        System.out.println();
        System.out.println(drawAvailableHeist(true));
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(drawCurrentCrew());
        System.out.println("-------------------------------------------------------------------------------------------------");
        System.out.println(drawCharacterPool());
        System.out.println(drawSelectionFooter());
    }

    private static void printHeistState() {
        System.out.println(drawHeader());
        System.out.println();
        System.out.println(drawAvailableHeist(true));
        System.out.println("Tests in detail:");
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

    private static void printHeistTestState(String skillPoints) {
        System.out.println(drawNextTest(skillPoints));
        System.out.println("-------------------------------------------------------------------------------------------------");
    }

    private static String drawHeader() {
        BUILDER.setLength(0);
        BUILDER
                .append("Heists left: ")
                .append(player.getHeistsLeft())
                .append("\t\t\t")
                .append("Money collected: ")
                .append(player.getMoney().isPresent() ? player.getMoney().get() : "Nada")
                .append("$/")
                .append(player.getMONEY_GOAL())
                .append("$\t\t\t")
                .append("Your Cut: ")
                .append(player.getCurrentCut())
                .append("%\n")
        ;
        return BUILDER.toString();
    }

    private static String drawAvailableHeist(boolean zeroB) {
        if (zeroB) {
            BUILDER.setLength(0);
        }
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
                .append("$ )\n")
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
                .append("-- Enter [A-J] to remove a crew member from your team!\n")
                .append("-- Enter [REPLACE] to replace 5 characters in the pool for 5% of your money!\n")
                .append(player.getMoney().isEmpty() ? "Yes it's free if you have no money... Good Luck!" : "")
                .append("\n-- Type [READY] to start the Heist!\n")
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

    private static String drawNextTest(String skillPoints) {
        BUILDER.setLength(0);
        BUILDER
                .append("\t")
                .append(String.format("%-20s\t%-12s%-5d\t%-15s%-5s",
                        nextTest.getType().toString().toUpperCase(),
                        "Difficulty: ",
                        nextTest.getDifficulty(),
                        "Crew skill points: ",
                        skillPoints
                ))
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

    private static ArrayList<CrewMember> getCrewForRandomTest(Test test) {
        var crewForTest = new ArrayList<CrewMember>();
        player.getCurrentCrew().forEach(crewMember -> {
            for (Skill s : crewMember.getSpecialties()) {
                if (s.getValue() == nextTest.getType().getValue()) crewForTest.add(crewMember);
            }
        });
        return crewForTest;
    }

    private static void characterLevelUp() {
        player.getCurrentCrew().forEach(crewMember -> {
                    crewMember.getSkills().forEach((skill, integer) -> {
                        crewMember.getSkills().put(skill, integer + 1);
                    });
        });
        availableHeist.getSuccesfullTests().forEach( container -> {
            container.getCrew().forEach( crew -> {
                crew.getSkills().forEach( (skill, integer) -> {
                    if (container.getTest().getType().equals(skill)) {
                        crew.getSkills().put(skill, integer + 4);
                    }
                });
            });
        });
    }


}
