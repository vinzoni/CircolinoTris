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

//            new AIPlayer("Ramona", new RandomStrategy()),
//            new AIPlayer("Rolando", new RandomStrategy()),
//            new AIPlayer("Rosita", new RandomStrategy()),
//            new AIPlayer("Riccardo", new RandomStrategy()),
//            new AIPlayer("Rossana", new RandomStrategy()),
//            new AIPlayer("Rinaldo", new RandomStrategy()),
//            new AIPlayer("Rosa", new RandomStrategy()),
//            new AIPlayer("Raimondo", new RandomStrategy()),
//            new AIPlayer("Romina", new RandomStrategy()),
//            new AIPlayer("Ronaldo", new RandomStrategy()),
//            new AIPlayer("Rebecca", new RandomStrategy()),
//            new AIPlayer("Remigio", new RandomStrategy()),
//            new AIPlayer("Rachele", new RandomStrategy()),
//            new AIPlayer("Rino", new RandomStrategy()),
//            new AIPlayer("Rita", new RandomStrategy()),
//            new AIPlayer("Roy", new RandomStrategy()),
    };

    public static int MIN_MILLIS_INTERVAL_BEETWEEN_AI_MOVES = 1000;
    public static int MIN_MILLIS_INTERVAL_BEETWEEN_ROUNDS = 5000;

    public static final int UI_FRAME_WIDTH = 1800;
    public static final int UI_FRAME_HEIGHT = 1000;
    public static final int UI_NUMBER_OF_BOARDS = players.length / 2;

    public static final Font UI_BOARD_BUTTON_FONT = new Font("Arial",Font.PLAIN, 30);
    public static final Font UI_NORMAL_TEXT_FONT = new Font("Arial",Font.BOLD, 14);
    public static final Font UI_SMALL_TEXT_FONT = new Font("Arial",Font.PLAIN, 12);
    public static final Font UI_CLOCK_FONT = new Font("Arial",Font.BOLD, 20);

    public static int UI_BOARD_GRID_ROWS;
    public static int UI_BOARD_GRID_COLS;
    public static int UI_BOARD_SIZE;

    public static final PairingSystem paringSystem = new PairingSystemRandom();

    static {
        switch(UI_NUMBER_OF_BOARDS) {
            case 1:
            case 2:
            case 3:
            case 4:
                UI_BOARD_GRID_ROWS = 1;
                UI_BOARD_GRID_COLS = 4;
                UI_BOARD_SIZE = 200;
                break;
            case 5:
            case 6:
            case 7:
            case 8:
                UI_BOARD_GRID_ROWS = 2;
                UI_BOARD_GRID_COLS = 4;
                UI_BOARD_SIZE = 200;
                break;
            case 9:
            case 10:
            case 11:
            case 12:
                UI_BOARD_GRID_ROWS = 2;
                UI_BOARD_GRID_COLS = 6;
                UI_BOARD_SIZE = 160;
                break;
            case 13:
            case 14:
            case 15:
            case 16:
            case 17:
            case 18:
                UI_BOARD_GRID_ROWS = 3;
                UI_BOARD_GRID_COLS = 6;
                UI_BOARD_SIZE = 160;
                break;
            default:
                if (players.length > 36)
                    throw new RuntimeException("Too many players (max 36)");
        }
    }

    //
    // Logging
    //
    public static final String LOG_FILENAME = "./data/Torneo_<date>.log";
}
