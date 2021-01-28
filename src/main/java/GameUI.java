import javax.swing.*;
import java.awt.*;

public class GameUI {
    private final BoardUI boardUI;
    private Game game;
    private Player player1;
    private Player player2;
    private final JLabel gameInfoLabel = new JLabel("Game not started");
    private final JPanel gamePanel = new JPanel();
    private final JLabel player1Label = new JLabel();
    private final JLabel player2Label = new JLabel();

    public GameUI() {

        gamePanel.setLayout(new java.awt.GridBagLayout());
        gamePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        player1Label.setFont(Configuration.UI_NORMAL_TEXT_FONT);
        player1Label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        GridBagConstraints c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.10;
        c.gridx = 0;
        c.gridy = 0;
        gamePanel.add(player1Label, c);

        boardUI = new BoardUI();
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.70;
        c.gridx = 0;
        c.gridy = 1;
        gamePanel.add(boardUI.panel(), c);

        player2Label.setFont(Configuration.UI_NORMAL_TEXT_FONT);
        player2Label.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.10;
        c.gridx = 0;
        c.gridy = 8;
        gamePanel.add(player2Label, c);

        gameInfoLabel.setFont(Configuration.UI_SMALL_TEXT_FONT);
        c = new GridBagConstraints();
        c.fill = GridBagConstraints.HORIZONTAL;
        c.weighty = 0.10;
        c.gridx = 0;
        c.gridy = 9;
        gamePanel.add(gameInfoLabel, c);

        gamePanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
    }

    public void redraw(String move) {
        boardUI.redraw(move);
        redraw();
    }

    public void redraw() {
        redrawGameStatus();
        redrawPlayers();
    }

    private void redrawPlayers() {
        player1Label.setText(player1.name() + " (" + player1.eloRating() + ")");
        player1Label.setForeground(Color.blue);

        player2Label.setText(player2.name() + " (" + player2.eloRating() + ")");
        player2Label.setForeground(Color.red);

        switch (game.status()) {
            case X_WON -> player2Label.setEnabled(false);
            case O_WON -> player1Label.setEnabled(false);
            default -> {
                player1Label.setEnabled(true);
                player2Label.setEnabled(true);
            }
        }
    }

    private void redrawGameStatus() {
        switch (game.status()) {
            case RUNNING -> gameInfoLabel.setText("Partita " + game.gameNumber() + " ...");
            case DRAW -> gameInfoLabel.setText("Partita " + game.gameNumber() + ": patta");
            case X_WON -> gameInfoLabel.setText("Partita "  + game.gameNumber() + ": " + player1.name());
            case O_WON -> gameInfoLabel.setText("Partita "  + game.gameNumber() + ": " + player2.name());
            default -> throw new RuntimeException("Stato partita invalido");
        }
    }

    public void setGame(Game game) {
        this.game = game;
        this.player1 = game.player1();
        this.player2 = game.player2();
        boardUI.setGame(game);
        redraw();
    }

    public JPanel panel() {
        return gamePanel;
    }
}
