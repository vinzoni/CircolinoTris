import java.util.concurrent.TimeUnit;

public class Tourneament implements Runnable {

    private TourneamentUI tourneamentUI;
    private boolean stopGames = false;
//    private int gameNumber = 0;
    private int roundNumber = 0;

    public void setUI_View(TourneamentUI tourneamentUI) {
        this.tourneamentUI = tourneamentUI;
    }

    @Override
    public void run() {

        this.stopGames = false;
        while (!this.stopGames) {
            try {
                roundNumber++;
                tourneamentUI.redraw(roundNumber);

                if (roundNumber == Configuration.MAX_NUMBER_OF_ROUNDS) this.stopGames = true;

                TourneamentRound tr = new TourneamentRound(roundNumber);
                tr.setUIView(tourneamentUI.tourneamentRoundUI());
                tr.run();

                tourneamentUI.redraw(roundNumber);
                Logger.log.logTourneamentRound(roundNumber, tr.games());

                TimeUnit.MILLISECONDS.sleep(Configuration.MIN_MILLIS_INTERVAL_BEETWEEN_ROUNDS);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        tourneamentUI.redraw(roundNumber);
        if (roundNumber == Configuration.MAX_NUMBER_OF_ROUNDS)
            tourneamentUI.closeTourneament(roundNumber);
    }

    public void stopGames() { this.stopGames = true; }

    public int roundNumber() { return roundNumber; }

}
