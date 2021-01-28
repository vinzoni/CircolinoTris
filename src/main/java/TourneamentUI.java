import javax.swing.*;
import java.awt.*;

public class TourneamentUI {
    Tourneament tourneament;
    private final JLabel tourneamentInfoLabel = new JLabel();
    private final RatingListUI ratingListUI = new RatingListUI();
    private final TourneamentRoundUI tourneamentRoundUI = new TourneamentRoundUI();
    private final ClockUI clockUI = new ClockUI();
    private boolean tourneamentRunning =  false;

    public TourneamentUI(Tourneament tourneament) {
        this.tourneament = tourneament;
        tourneament.setUI_View(this);

        JPanel gamesContainerPanel = tourneamentRoundUI.panel();

        JFrame frame = new JFrame();
        frame.setLayout(new java.awt.GridBagLayout());

        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.weightx = 0.40;
        c.gridx = 0;
        c.gridy = 0;
        JPanel commandPanel = createCommandPanel();
        frame.add(commandPanel, c);

        c = new GridBagConstraints();
        c.fill = GridBagConstraints.BOTH;
        c.gridx = 1;
        c.gridy = 0;
        JPanel clockPanel = clockUI.panel();
        frame.add(clockPanel, c);

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.WEST;
        c.weightx = 0.40;
        c.gridx = 0;
        c.gridy = 1;
        frame.add(gamesContainerPanel, c);

        c = new GridBagConstraints();
        c.anchor = GridBagConstraints.NORTH;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weightx = 0.60;
        c.gridx = 4;
        c.gridy = 1;
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
        JPanel commandPanel = new JPanel();
        final JButton newGameButton = new JButton();
        final JButton lastGameButton = new JButton();

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
        commandPanel.add(newGameButton);
        lastGameButton.setText("Stop Games");
        lastGameButton.setEnabled(false);
        lastGameButton.addActionListener(
                e -> {
                    newGameButton.setEnabled(true);
                    lastGameButton.setEnabled(false);
                    stopGames();
                });

        tourneamentInfoLabel.setFont(Configuration.UI_NORMAL_TEXT_FONT);
        tourneamentInfoLabel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 100));
        redraw(1);
        commandPanel.add(tourneamentInfoLabel);
        commandPanel.add(lastGameButton);
        return commandPanel;
    }

    private void stopGames() {
        tourneament.stopGames();
        clockUI.stop();
    }

    private void startGames() throws InterruptedException {
        Logger.log.logTourneamentStart(Configuration.MAX_NUMBER_OF_ROUNDS, Configuration.players.length);
        Thread thread = new Thread(tourneament);
        thread.start();
        clockUI.start();
        tourneamentRunning = true;
    }

    public void redraw(int roundNumber) {
        String infoRunningRound =
                Configuration.MAX_NUMBER_OF_ROUNDS == 0
                        ?
                        "" + roundNumber
                        :
                        "" + roundNumber + " di "  + Configuration.MAX_NUMBER_OF_ROUNDS;
        tourneamentInfoLabel.setText(Configuration.TOURNEAMENT_NAME + " - Turno " + infoRunningRound);

        ratingListUI.redraw();
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
