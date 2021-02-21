import java.awt.*;

public class Configuration {

    // TOURNEAMENT PARAMETERS - FEEL FREE TO MODIFY THEM
    public static final TourneamentConfiguration tourneament = new TourneamentConfiguration16Players();

    // USER INTERFACE PARAMETERS - FEEL FREE TO MODIFY THEM
    public static int MIN_MILLIS_INTERVAL_BEETWEEN_AI_MOVES = 1000;
    public static int MIN_MILLIS_INTERVAL_BEETWEEN_ROUNDS = 5000;

    public static final Font UI_BOARD_BUTTON_FONT = new Font("Arial",Font.PLAIN, 30);
    public static final Font UI_RATING_LIST_FONT = new Font("Arial",Font.BOLD, 16);
    public static final Font UI_NORMAL_TEXT_FONT = new Font("Arial",Font.BOLD, 14);
    public static final Font UI_SMALL_TEXT_FONT = new Font("Arial",Font.PLAIN, 12);
    public static final Font UI_CLOCK_FONT = new Font("Arial",Font.BOLD, 20);

    // TOURNEAMENT DERIVED PARAMETERS - CHANGE THEM AT YOUR OWN RISK
    public static final String tourneamentName = tourneament.name();
    public static final int tourneamentRounds = tourneament.rounds(); // 0 --> infinite
    public static final Player [] players = tourneament.players();
    public static final PairingSystem paringSystem = tourneament.pairingSystem();

    // USER INTERFACE DERIVED PARAMETERS - CHANGE THEM AT YOUR OWN RISK

    public static int UI_FRAME_WIDTH;
    public static int UI_FRAME_HEIGHT;
    public static int UI_BOARD_GRID_ROWS;
    public static int UI_BOARD_GRID_COLS;
    public static int UI_BOARD_SIZE;

    public static final int UI_NUMBER_OF_BOARDS = players.length / 2;

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
        UI_FRAME_WIDTH = 600 + 250 * UI_BOARD_GRID_COLS;
        UI_FRAME_HEIGHT = 400 + 100 * UI_BOARD_GRID_COLS;
    }
}
