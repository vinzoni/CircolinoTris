import java.util.ArrayList;
import java.util.List;

public class TourneamentRound {

    public static int GAMES_PER_ROUND = Configuration.players.length / 2;
    private static int nextGameNumber = 1;
    private static final TourneamentRound firstRound; // Primo turno precalcolato a beneficio dell'inizializzazione della UI.

    static {
        firstRound = new TourneamentRound(1);
    }

    private final Game[] games = new Game[GAMES_PER_ROUND];

    public static TourneamentRound create(int roundNumber) {
        return roundNumber == 1 ? firstRound : new TourneamentRound(roundNumber);
    }

    private TourneamentRound(int roundNumber) {

        List<PairingSystem.PlayersPair> pairings = Configuration.paringSystem.pairings(roundNumber);

        for (int i=0; i<GAMES_PER_ROUND; ++i ) {
            Player player1 = pairings.get(i).player1;
            Player player2 = pairings.get(i).player2;
            Game g = new Game(player1, player2, nextGameNumber);
            nextGameNumber++;
            this.games[i] = g;
        }
    }

    public Game[] games() { return games; }

    public void setUIView(TourneamentRoundUI tourneamentRoundUI) {

       GameUI[] gamesUI = tourneamentRoundUI.gamesUI();

       for (int i=0; i<TourneamentRound.GAMES_PER_ROUND; ++i) {
           gamesUI[i].setGame(this.games[i]);
           this.games[i].setGameUI(gamesUI[i]);
        }
    }

    public void run() throws InterruptedException {

        List<Thread> threads = new ArrayList<>();
        for (int i=0; i<TourneamentRound.GAMES_PER_ROUND; ++i) {
            Thread t = new Thread(games[i]);
            threads.add(t);
        }

        for (Thread t: threads) t.start();

        for (Thread t: threads) t.join();

    }
}
