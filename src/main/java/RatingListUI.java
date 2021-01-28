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

        rankingContainerPanel.setLayout(new BoxLayout(rankingContainerPanel, BoxLayout.Y_AXIS));

        JPanel panelForTitle = new JPanel();
        JLabel title = new JLabel("RATING LIST");
        title.setFont(Configuration.UI_NORMAL_TEXT_FONT);
        title.setBorder(BorderFactory.createEmptyBorder(10, 0, 10, 0));
        panelForTitle.add(title);
        rankingContainerPanel.add(panelForTitle);

        JPanel panelForList = new JPanel();
        panelForList.setLayout(new GridBagLayout());
        panelForList.setBorder(BorderFactory.createEmptyBorder(0, 10, 5, 10));

        createLabel(panelForList, "#", 0.10, 0, 0);
        createLabel(panelForList, "Giocatore", 0.50, 1, 0);
        createLabel(panelForList, "Punti", 0.10, 6, 0);
        createLabel(panelForList, "+", 0.10, 7, 0);
        createLabel(panelForList, "=", 0.10, 8, 0);
        createLabel(panelForList, "-", 0.10, 9, 0);

        for (int i=0; i<Configuration.players.length; ++i) {
            createLabel(panelForList, ""+ (i+1) + ".", 0.10, 0, i+1);
            players[i] = createLabel(panelForList, Configuration.players[i].name(), 0.50, 1, i+1);
            ratings[i] = createLabel(panelForList, "" + Configuration.players[i].eloRating(), 0.10, 6, i+1);
            wins[i] = createLabel(panelForList, "" + Configuration.players[i].wins, 0.10, 7, i+1);
            draws[i] = createLabel(panelForList, "" + Configuration.players[i].draws, 0.10, 8, i+1);
            losses[i] = createLabel(panelForList, "" + Configuration.players[i].losses, 0.10, 9, i+1);
        }
        rankingContainerPanel.add(panelForList);

        JPanel panelForBottomExtraSpace = new JPanel();
        panelForBottomExtraSpace.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 0));
        rankingContainerPanel.add(panelForBottomExtraSpace);
    }

    private JLabel createLabel(JPanel panel, String text, double weightx, int gridx, int gridy) {
        GridBagConstraints constraints = new GridBagConstraints();
        JLabel label = new JLabel(text);
        label.setFont(Configuration.UI_NORMAL_TEXT_FONT);
        if (gridy == 0)
            label.setBorder(BorderFactory.createEmptyBorder(0, 0, 10, 0));
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.weightx = weightx;
        constraints.gridx = gridx;
        constraints.gridy = gridy;
        panel.add(label, constraints);
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
