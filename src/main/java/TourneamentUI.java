import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class TourneamentUI {
    Tourneament tourneament;
    private final JLabel tourneamentInfoLabel = new JLabel();
    private final RatingListUI ratingListUI = new RatingListUI();
    private final TourneamentRoundUI tourneamentRoundUI = new TourneamentRoundUI();
    private final ClockUI clockUI = new ClockUI();
    private final JButton newGameButton = new JButton();
    private final JButton lastGameButton = new JButton();
    private boolean tourneamentRunning =  false;

    public TourneamentUI(Tourneament tourneament) {
        this.tourneament = tourneament;
        tourneament.setUI_View(this);

        JFrame frame = new JFrame();
        frame.setLayout(new java.awt.GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 0;
        c.gridy = 0;
        JPanel commandPanel = createCommandPanel();
//        commandPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        frame.add(commandPanel, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 4;
        c.gridy = 0;
        JPanel clockPanel = clockUI.panel();
//        clockPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        frame.add(clockPanel, c);

        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        JPanel gamesContainerPanel = tourneamentRoundUI.panel();
//        gamesContainerPanel.setBorder(BorderFactory.createLineBorder(Color.blue));
        frame.add(gamesContainerPanel, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 4;
        c.gridy = 1;
//        ratingListUI.panel().setBorder(BorderFactory.createLineBorder(Color.blue));
        frame.add(ratingListUI.panel(), c);

        frame.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                tourneament.stopGames();
                closeTourneament(tourneament.roundNumber());
            }
        });
        frame.setSize(Configuration.UI_FRAME_WIDTH, Configuration.UI_FRAME_HEIGHT);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    private JPanel createCommandPanel() {

        newGameButton.setText("Start Games");
        newGameButton.addActionListener(
                e -> {
                    try {
                        newGameButton.setEnabled(false);
                        lastGameButton.setEnabled(true);
                        startGames();
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                });
        lastGameButton.setText("Stop Games");
        lastGameButton.setEnabled(false);
        lastGameButton.addActionListener(
                e -> {
                    newGameButton.setEnabled(true);
                    lastGameButton.setEnabled(false);
                    stopGames();
                });

        tourneamentInfoLabel.setFont(Configuration.UI_NORMAL_TEXT_FONT);
        tourneamentInfoLabel.setBorder(new EmptyBorder(0, 20, 0, 20));
        redraw(1);

        JPanel commandPanel = new JPanel();
        commandPanel.setLayout(new GridBagLayout());

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.weightx = 0.20;
        commandPanel.add(newGameButton, constraints);

        constraints = new GridBagConstraints();
        constraints.weightx = 0.60;
        commandPanel.add(tourneamentInfoLabel, constraints);

        constraints = new GridBagConstraints();
        constraints.weightx = 0.20;
        commandPanel.add(lastGameButton, constraints);

        return commandPanel;
    }

    private void stopGames() {
        tourneament.stopGames();
        clockUI.stop();
    }

    private void startGames() throws InterruptedException {
        Logger.log.logTourneamentStart(Configuration.tourneamentRounds, Configuration.players.length);
        Thread thread = new Thread(tourneament);
        thread.start();
        clockUI.start();
        tourneamentRunning = true;
    }

    public void redraw(int roundNumber) {
        String infoRunningRound =
                Configuration.tourneamentRounds == 0
                        ?
                        "" + roundNumber
                        :
                        "" + roundNumber + " di "  + Configuration.tourneamentRounds;
        tourneamentInfoLabel.setText(Configuration.tourneamentName + " - Turno " + infoRunningRound);

        ratingListUI.redraw();

        if (roundNumber == Configuration.tourneamentRounds) newGameButton.setEnabled(false);

    }

    public void closeTourneament(int roundNumber) {
        if (! tourneamentRunning) return;

        clockUI.stop();
        Logger.log.logTourneamentEnd(roundNumber, clockUI.toString());
        Logger.log.logRatingList(ratingListUI.ratingList());
        tourneamentRunning =  false;
    }

    public TourneamentRoundUI tourneamentRoundUI() { return tourneamentRoundUI; }
}
