import java.awt.*;

public class Configuration {
    public static final String TOURNEAMENT_NAME = "CAMPIONATO SOCIALE";

    public static final int MAX_NUMBER_OF_ROUNDS = 200; // 0 --> infinite

    public static final Player [] players = {
            new AIPlayer("Rosanna", new RandomStrategy()),
            new AIPlayer("Ruggero", new RandomStrategy()),
            new AIPlayer("Roberta", new RandomStrategy()),
            new AIPlayer("Rodolfo", new RandomStrategy()),
            new AIPlayer("Corinna", new CenterStrategy()),
            new AIPlayer("Carmelo", new CenterStrategy()),
            new AIPlayer("Carla", new CenterStrategy()),
            new AIPlayer("Corrado", new CenterStrategy()),
            new AIPlayer("Lorenzo", new LookAheadStrategy()),
            new AIPlayer("Lisa", new LookAheadStrategy()),
            new AIPlayer("Ciro Leone", new CenterAndLookAheadStrategy()),
            new AIPlayer("Carmen Laura", new CenterAndLookAheadStrategy()),
            new AIPlayer("Walter", new WholeMoveLookAheadStrategy()),
            new AIPlayer("Wilma", new WholeMoveLookAheadStrategy()),
            new AIPlayer("Giovanni", new ExaustiveSearchStrategy()),
            new AIPlayer("Giulia", new ExaustiveSearchStrategy()),
    };

    public static final PairingSystem paringSystem = new PairingSystemRandom();

    public static int MIN_MILLIS_INTERVAL_BEETWEEN_AI_MOVES = 1000;
    public static int MIN_MILLIS_INTERVAL_BEETWEEN_ROUNDS = 5000;

    public static final int UI_BOARD_GRID_ROWS = 2;
    public static final int UI_BOARD_GRID_COLS = 4;
    public static final int UI_NUMBER_OF_BOARDS = UI_BOARD_GRID_ROWS * UI_BOARD_GRID_COLS;
    public static final int UI_FRAME_WIDTH = 1400;
    public static final int UI_FRAME_HEIGHT = 900;
    public static final int UI_BOARD_SIZE = 180;
    public static final Font UI_BOARD_BUTTON_FONT = new Font("Arial",Font.PLAIN, 30);
    public static final Font UI_NORMAL_TEXT_FONT = new Font("Arial",Font.BOLD, 14);
    public static final Font UI_SMALL_TEXT_FONT = new Font("Arial",Font.PLAIN, 12);
    public static final Font UI_CLOCK_FONT = new Font("Arial",Font.BOLD, 20);

    public static final String LOG_FILENAME = "./data/Torneo_<date>.log";
}
