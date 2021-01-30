import javax.swing.*;

public class TourneamentRoundUI {
    private final GameUI[] gamesUI = new GameUI[Configuration.UI_NUMBER_OF_BOARDS];
    private final JPanel gamesContainerPanel = new JPanel();

    public TourneamentRoundUI()  {

        gamesContainerPanel.setLayout(new java.awt.GridLayout(Configuration.UI_BOARD_GRID_ROWS, Configuration.UI_BOARD_GRID_COLS));
        for (int i = 0; i<Configuration.UI_NUMBER_OF_BOARDS; ++i) {
            gamesUI[i] = new GameUI();
            gamesContainerPanel.add(gamesUI[i].panel());
        }

        TourneamentRound tr = TourneamentRound.create(1);
        tr.setUIView(this);
    }

    public GameUI[] gamesUI() { return gamesUI; }
    public JPanel panel() { return gamesContainerPanel; }
}
