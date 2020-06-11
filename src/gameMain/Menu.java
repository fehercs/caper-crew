package gameMain;

import java.io.IOException;

public class Menu {
    private static final StringBuilder SB = new StringBuilder();
    private static final int TIMING = 400;
    private static boolean run = true;

    public static void renderMenu() throws InterruptedException {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    int read = System.in.read(new byte[2]);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                run = false;
            }
        });
        t.start();
        while (run) {
            clearConsole();
            renderNE();
            Thread.sleep(GameMain.getRandomInteger(800, 1500));
            clearConsole();
            renderSE();
            Thread.sleep(100);
            clearConsole();
            renderNE();
            Thread.sleep(GameMain.getRandomInteger(300, 800));
            clearConsole();
            renderSE();
            Thread.sleep(100);
            clearConsole();
            renderNE();
            Thread.sleep(GameMain.getRandomInteger(200, 800));
        }
    }

    private static void renderNE() {
        SB.setLength(0);
        SB
                .append("\n" +
                        "  /$$$$$$                                                 /$$$$$$                                   \n" +
                        " /$$__  $$                                               /$$__  $$                                  \n" +
                        "| $$  \\__/  /$$$$$$   /$$$$$$   /$$$$$$   /$$$$$$       | $$  \\__/  /$$$$$$   /$$$$$$  /$$  /$$  /$$\n" +
                        "| $$       |____  $$ /$$__  $$ /$$__  $$ /$$__  $$      | $$       /$$__  $$ /$$__  $$| $$ | $$ | $$\n" +
                        "| $$        /$$$$$$$| $$  \\ $$| $$$$$$$$| $$  \\__/      | $$      | $$  \\__/| $$$$$$$$| $$ | $$ | $$\n" +
                        "| $$    $$ /$$__  $$| $$  | $$| $$_____/| $$            | $$    $$| $$      | $$_____/| $$ | $$ | $$\n" +
                        "|  $$$$$$/|  $$$$$$$| $$$$$$$/|  $$$$$$$| $$            |  $$$$$$/| $$      |  $$$$$$$|  $$$$$/$$$$/\n" +
                        " \\______/  \\_______/| $$____/  \\_______/|__/             \\______/ |__/       \\_______/ \\_____/\\___/ \n" +
                        "                    | $$                                                                            \n" +
                        "                    | $$                                                                            \n" +
                        "                    |__/                                                                            \n\n" +
                        "\t\t\t\t\tPress ENTER to continue!\n")
                ;
        System.out.println(SB.toString());
    }

    private static void renderNW(){
        SB.setLength(0);
        SB.append("\n" +
                " $$$$$$\\                                                 $$$$$$\\                                    \n" +
                "$$  __$$\\                                               $$  __$$\\                                   \n" +
                "$$ /  \\__| $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\        $$ /  \\__| $$$$$$\\   $$$$$$\\  $$\\  $$\\  $$\\ \n" +
                "$$ |       \\____$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\       $$ |      $$  __$$\\ $$  __$$\\ $$ | $$ | $$ |\n" +
                "$$ |       $$$$$$$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|      $$ |      $$ |  \\__|$$$$$$$$ |$$ | $$ | $$ |\n" +
                "$$ |  $$\\ $$  __$$ |$$ |  $$ |$$   ____|$$ |            $$ |  $$\\ $$ |      $$   ____|$$ | $$ | $$ |\n" +
                "\\$$$$$$  |\\$$$$$$$ |$$$$$$$  |\\$$$$$$$\\ $$ |            \\$$$$$$  |$$ |      \\$$$$$$$\\ \\$$$$$\\$$$$  |\n" +
                " \\______/  \\_______|$$  ____/  \\_______|\\__|             \\______/ \\__|       \\_______| \\_____\\____/ \n" +
                "                    $$ |                                                                            \n" +
                "                    $$ |                                                                            \n" +
                "                    \\__|                                                                            \n")
                ;
        System.out.println(SB.toString());
    }

    private static void clearConsole() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void renderSE() {
        SB.setLength(0);
        SB
                .append("\n" +
                        "  ______                                                  ______                                    \n" +
                        " /      \\                                                /      \\                                   \n" +
                        "|  $$$$$$\\  ______    ______    ______    ______        |  $$$$$$\\  ______    ______   __   __   __ \n" +
                        "| $$   \\$$ |      \\  /      \\  /      \\  /      \\       | $$   \\$$ /      \\  /      \\ |  \\ |  \\ |  \\\n" +
                        "| $$        \\$$$$$$\\|  $$$$$$\\|  $$$$$$\\|  $$$$$$\\      | $$      |  $$$$$$\\|  $$$$$$\\| $$ | $$ | $$\n" +
                        "| $$   __  /      $$| $$  | $$| $$    $$| $$   \\$$      | $$   __ | $$   \\$$| $$    $$| $$ | $$ | $$\n" +
                        "| $$__/  \\|  $$$$$$$| $$__/ $$| $$$$$$$$| $$            | $$__/  \\| $$      | $$$$$$$$| $$_/ $$_/ $$\n" +
                        " \\$$    $$ \\$$    $$| $$    $$ \\$$     \\| $$             \\$$    $$| $$       \\$$     \\ \\$$   $$   $$\n" +
                        "  \\$$$$$$   \\$$$$$$$| $$$$$$$   \\$$$$$$$ \\$$              \\$$$$$$  \\$$        \\$$$$$$$  \\$$$$$\\$$$$ \n" +
                        "                    | $$                                                                            \n" +
                        "                    | $$                                                                            \n" +
                        "                     \\$$                                                                            \n" +
                        "\t\t\t\t\tPress ENTER to continue!\n")
        ;
        System.out.println(SB.toString());
    }

    private static void renderSW() {
        SB.setLength(0);
        SB
                .append("\n" +
                        "  ______                                                  ______                                    \n" +
                        " /      \\                                                /      \\                                   \n" +
                        "/$$$$$$  |  ______    ______    ______    ______        /$$$$$$  |  ______    ______   __   __   __ \n" +
                        "$$ |  $$/  /      \\  /      \\  /      \\  /      \\       $$ |  $$/  /      \\  /      \\ /  | /  | /  |\n" +
                        "$$ |       $$$$$$  |/$$$$$$  |/$$$$$$  |/$$$$$$  |      $$ |      /$$$$$$  |/$$$$$$  |$$ | $$ | $$ |\n" +
                        "$$ |   __  /    $$ |$$ |  $$ |$$    $$ |$$ |  $$/       $$ |   __ $$ |  $$/ $$    $$ |$$ | $$ | $$ |\n" +
                        "$$ \\__/  |/$$$$$$$ |$$ |__$$ |$$$$$$$$/ $$ |            $$ \\__/  |$$ |      $$$$$$$$/ $$ \\_$$ \\_$$ |\n" +
                        "$$    $$/ $$    $$ |$$    $$/ $$       |$$ |            $$    $$/ $$ |      $$       |$$   $$   $$/ \n" +
                        " $$$$$$/   $$$$$$$/ $$$$$$$/   $$$$$$$/ $$/              $$$$$$/  $$/        $$$$$$$/  $$$$$/$$$$/  \n" +
                        "                    $$ |                                                                            \n" +
                        "                    $$ |                                                                            \n" +
                        "                    $$/                                                                             \n" +
                        "\t\t\t\t\tPress ENTER to continue!\n")
        ;
        System.out.println(SB.toString());
    }

    private static void renderMoney() {
        SB.setLength(0);
        SB.append("\t                                              ▓▓▓▓                        \n" +
                "\t                                            ▓▓    ▓▓▓▓                    \n" +
                "\t                                          ▓▓  ░░░░    ▓▓▓▓                \n" +
                "\t                                        ▓▓  ░░▒▒▒▒▒▒▒▒    ▓▓▓▓            \n" +
                "\t                                    ▓▓▓▓  ░░░░▒▒░░░░▒▒▒▒▒▒    ▓▓▓▓        \n" +
                "\t                                ▒▒▒▒░░░░▒▒▒▒░░▒▒▒▒▒▒░░░░▒▒░░░░    ▓▓▓▓    \n" +
                "\t                            ▒▒▒▒░░░░░░░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒░░░░  ▓▓▓▓      \n" +
                "\t                        ▓▓▓▓░░░░░░░░░░░░░░░░░░░░▓▓▒▒▒▒░░░░░░  ▓▓▓▓▓▓      \n" +
                "\t                  ▓▓▓▓▓▓░░░░▓▓▓▓░░░░░░░░░░░░░░░░    ▒▒▒▒░░  ▓▓▓▓▓▓▓▓      \n" +
                "\t            ▓▓▓▓▓▓  ░░░░▒▒▒▒▓▓▒▒▒▒▓▓░░░░░░░░          ░░▓▓▓▓▓▓▓▓▓▓▓▓      \n" +
                "\t        ▓▓▓▓      ░░▒▒▒▒▒▒▒▒▓▓▓▓▓▓▒▒▒▒▓▓▒▒▒▒        ▒▒▓▓▓▓▓▓▒▒▓▓▓▓▓▓▓▓    \n" +
                "\t        ▓▓▒▒▓▓▓▓    ░░▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▒▒▒▒    ▒▒▒▒▒▒▓▓▒▒▒▒▓▓▓▓▓▓▓▓      \n" +
                "\t      ▒▒▒▒▓▓▒▒▒▒▓▓▓▓    ░░▒▒▒▒▒▒▒▒▒▒▒▒░░░░░░▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓▓▓▓▓▓▓        \n" +
                "\t    ▒▒    ▓▓▒▒▒▒▒▒▒▒▓▓▓▓    ░░░░    ░░▓▓▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓          \n" +
                "\t  ▒▒  ░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓    ▓▓▓▓▓▓▓▓▓▓▓▓▒▒▒▒▒▒            ▒▒          \n" +
                "\t▒▒░░░░░░▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▓▓▓▓██████▓▓▓▓██▒▒▒▒▒▒      ▒▒▒▒▒▒▒▒▒▒        \n" +
                "\t▒▒▒▒▒▒▒▒▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒▒████████████████▒▒▒▒▒▒                ▒▒      \n" +
                "\t▒▒▒▒        ▓▓▓▓▒▒▒▒▒▒▒▒▒▒▒▒████████████▓▓▓▓▒▒▒▒▒▒            ▒▒▒▒▒▒▒▒▒▒  \n" +
                "\t                ▓▓▓▓▒▒▒▒▒▒▒▒████████████████      ▒▒                ░░░░▒▒\n" +
                "\t                    ▓▓▓▓▒▒▒▒████████████          ▒▒            ▒▒▒▒▒▒▒▒▒▒\n" +
                "\t                        ▓▓▓▓████████              ▒▒          ░░░░░░░░░░▒▒\n" +
                "\t                            ████                    ▒▒    ░░░░░░▒▒▒▒▒▒▒▒▒▒\n" +
                "\t                                                      ▒▒░░░░░░░░░░░░░░░░▒▒\n" +
                "\t                                                        ▒▒░░░░░░░░▒▒▒▒▒▒▒▒\n" +
                "\t                                                          ▒▒░░░░░░░░▒▒▒▒  \n" +
                "\t                                                          ▒▒░░░░░░▒▒░░░░▒▒\n" +
                "\t                                                            ▒▒░░░░░░▒▒▒▒▒▒\n" +
                "\t                                                            ▒▒░░▒▒░░░░▒▒  \n" +
                "\t                                                            ▒▒░░░░▒▒▒▒▒▒  \n" +
                "\t                                                              ▒▒░░▒▒      \n" +
                "\t                                                              ▒▒░░▒▒      \n" +
                "\t                                                                ▒▒        \n")
                ;
        System.out.println(SB.toString());
    }

    public static void renderGameOver() {
        if (Game.player.getMoney().isPresent()) {
            int money = Game.player.getMoney().get();
            if (money >= Game.player.getMONEY_GOAL()) {
                clearConsole();
                System.out.println("Congratulations! You WON!");
                renderMoney();
            }
        }
        if (Game.player.getHeistsLeft() <= 0) {
            clearConsole();
            System.out.println("Game Over!");
        }
    }



    private static void promptEnterKey(){
        System.out.println("\t\t\t\t\tPress ENTER to continue!");
        try {
            int read = System.in.read(new byte[2]);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


