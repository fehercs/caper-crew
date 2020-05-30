import javax.swing.*;

public class CaperCrewGameGUI extends JFrame {
    private JProgressBar collectedProgressBar;
    private JTabbedPane tabbedPane1;
    private JButton skipButton;
    private JButton acceptButton;
    private JLabel heistCounter;
    private JLabel collectedLabel;
    private JPanel mainPanel;

    public CaperCrewGameGUI(String title) {
        super(title);

        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setContentPane(mainPanel);
        this.pack();
    }
}
