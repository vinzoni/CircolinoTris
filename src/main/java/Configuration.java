import java.awt.*;

public class Configuration {
    public static final String TOURNEAMENT_NAME = "CAMPIONATO DEL CIRCOLINO";

    public static final int MAX_NUMBER_OF_ROUNDS = 4; // 0 --> infinite

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

            new AIPlayer("Ramona", new RandomStrategy()),
            new AIPlayer("Rolando", new RandomStrategy()),
            new AIPlayer("Rosita", new RandomStrategy()),
            new AIPlayer("Riccardo", new RandomStrategy()),
            new AIPlayer("Rossana", new RandomStrategy()),
            new AIPlayer("Rinaldo", new RandomStrategy()),
            new AIPlayer("Rosa", new RandomStrategy()),
            new AIPlayer("Raimondo", new RandomStrategy()),
            new AIPlayer("Romina", new RandomStrategy()),
            new AIPlayer("Ronaldo", new RandomStrategy()),
            new AIPlayer("Rebecca", new RandomStrategy()),
            new AIPlayer("Remigio", new RandomStrategy()),
            new AIPlayer("Rachele", new RandomStrategy()),
            new AIPlayer("Rino", new RandomStrategy()),
            new AIPlayer("Rita", new RandomStrategy()),
            new AIPlayer("Roy", new RandomStrategy()),
    };

    public static final PairingSystem paringSystem = new PairingSystemRandom();

    public static int MIN_MILLIS_INTERVAL_BEETWEEN_AI_MOVES = 1000;
    public static int MIN_MILLIS_INTERVAL_BEETWEEN_ROUNDS = 5000;

    //
    // UI Configuration for 32 players tourneament
    //
    public static final int UI_FRAME_WIDTH = 1800;
    public static final int UI_FRAME_HEIGHT = 1000;
    public static final int UI_BOARD_GRID_ROWS = 3;
    public static final int UI_BOARD_GRID_COLS = 6;
    public static final int UI_NUMBER_OF_BOARDS = UI_BOARD_GRID_ROWS * UI_BOARD_GRID_COLS;
    public static final int UI_BOARD_SIZE = 160;
    public static final Font UI_BOARD_BUTTON_FONT = new Font("Arial",Font.PLAIN, 30);
    public static final Font UI_NORMAL_TEXT_FONT = new Font("Arial",Font.BOLD, 14);
    public static final Font UI_SMALL_TEXT_FONT = new Font("Arial",Font.PLAIN, 12);
    public static final Font UI_CLOCK_FONT = new Font("Arial",Font.BOLD, 20);


    //
    // UI Configuration for 16 players tourneament
    //
//    public static final int UI_FRAME_WIDTH = 1400;
//    public static final int UI_FRAME_HEIGHT = 900;
//    public static final int UI_BOARD_GRID_ROWS = 2;
//    public static final int UI_BOARD_GRID_COLS = 4;
//    public static final int UI_NUMBER_OF_BOARDS = UI_BOARD_GRID_ROWS * UI_BOARD_GRID_COLS;
//    public static final int UI_BOARD_SIZE = 180;
//    public static final Font UI_BOARD_BUTTON_FONT = new Font("Arial",Font.PLAIN, 30);
//    public static final Font UI_NORMAL_TEXT_FONT = new Font("Arial",Font.BOLD, 14);
//    public static final Font UI_SMALL_TEXT_FONT = new Font("Arial",Font.PLAIN, 12);
//    public static final Font UI_CLOCK_FONT = new Font("Arial",Font.BOLD, 20);

    //
    // Logging
    //
    public static final String LOG_FILENAME = "./data/Torneo_<date>.log";
}
