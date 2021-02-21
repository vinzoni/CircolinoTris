import javax.swing.*;
import java.awt.*;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class RatingListUI {
    private final JPanel rankingContainerPanel = new JPanel();
    private final JLabel[] players = new JLabel[Configuration.players.length];
    private final JLabel[] ratings = new JLabel[Configuration.players.length];
    private final JLabel[] wins = new JLabel[Configuration.players.length];
    private final JLabel[] draws = new JLabel[Configuration.players.length];
    private final JLabel[] losses = new JLabel[Configuration.players.length];

    public RatingListUI() {

        rankingContainerPanel.setLayout(new GridBagLayout());
        rankingContainerPanel.setPreferredSize(new Dimension(500, 0));
        rankingContainerPanel.setBorder(BorderFactory.createEmptyBorder(0, 100, 0, 0));

        createLabel(rankingContainerPanel, "RATING LIST", 1.0, 1, 0);

        createLabel(rankingContainerPanel, "#", 0.10, 0, 1);
        createLabel(rankingContainerPanel, "Giocatore", 0.50, 1, 1);
        createLabel(rankingContainerPanel, "Punti", 0.10, 6, 1);
        createLabel(rankingContainerPanel, "+", 0.10, 7, 1);
        createLabel(rankingContainerPanel, "=", 0.10, 8, 1);
        createLabel(rankingContainerPanel, "-", 0.10, 9, 1);

        for (int i=0; i<Configuration.players.length; ++i) {
            createLabel(rankingContainerPanel, ""+ (i+1) + ".", 0.10, 0, i+2);
            players[i] = createLabel(rankingContainerPanel, Configuration.players[i].name(), 0.50, 1, i+2);
            ratings[i] = createLabel(rankingContainerPanel, "" + Configuration.players[i].eloRating(), 0.10, 6, i+2);
            wins[i] = createLabel(rankingContainerPanel, "" + Configuration.players[i].wins, 0.10, 7, i+2);
            draws[i] = createLabel(rankingContainerPanel, "" + Configuration.players[i].draws, 0.10, 8, i+2);
            losses[i] = createLabel(rankingContainerPanel, "" + Configuration.players[i].losses, 0.10, 9, i+2);
        }

    }

    private JLabel createLabel(JPanel panel, String text, double weightx, int gridx, int gridy) {
        JLabel label = new JLabel(text);
        label.setHorizontalAlignment(JLabel.LEFT);
        label.setFont(Configuration.UI_RATING_LIST_FONT);

        if (gridy == 0)
            label.setBorder(BorderFactory.createEmptyBorder(20, 0, 10, 0));

        if (gridy == 1)
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));

        panel.add(label, new GridBagConstraints(gridx, gridy, 1, 1, weightx, 1.0,
                GridBagConstraints.NORTH, GridBagConstraints.HORIZONTAL,
                new Insets(0, 0, 0, 0), 0, 0));
        return label;
    }

    public JPanel panel() {
        return rankingContainerPanel;
    }

    public void redraw() {
        List<Player> playersSortedByElo = Arrays.asList(Configuration.players);
        playersSortedByElo.sort(Comparator.comparing(Player::eloRating).reversed());

        for (int i=0; i<playersSortedByElo.size(); ++i) {
            players[i].setText(playersSortedByElo.get(i).name());
            ratings[i].setText(""+playersSortedByElo.get(i).eloRating());
            wins[i].setText(""+playersSortedByElo.get(i).wins);
            draws[i].setText(""+playersSortedByElo.get(i).draws);
            losses[i].setText(""+playersSortedByElo.get(i).losses);
        }
    }

    public List<Player> ratingList () {
        List<Player> playersSortedByElo = Arrays.asList(Configuration.players);
        playersSortedByElo.sort(Comparator.comparing(Player::eloRating).reversed());
        return playersSortedByElo;
    }
}
