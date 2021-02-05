import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.List;


public class Logger {
    public static Logger log = new Logger();

    BufferedWriter writer;

    public Logger() {
        try {
            Date date = new Date();
            String filename = "./data/Torneo_" + date.toString() + ".log";
            this.writer = new BufferedWriter(new FileWriter(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logTourneamentStart(int rounds, int players) {
        try {
            this.writer.write("TORNEO SOCIALE - Start\n");
            this.writer.write("Turni  previsti    : " + rounds + "\n");
            this.writer.write("Pairing system     : " + Configuration.paringSystem.getClass().toString() + "\n");
            this.writer.write("Giocatori          : " + players + "\n");
            this.writer.write("Data               : " + new Date().toString() + "\n");
            this.writer.write("\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logTourneamentEnd(int rounds, String elapsedTime) {
        try {
            this.writer.write("TORNEO SOCIALE  - End\n");
            this.writer.write("Turni effettivi    : " + rounds + "\n");
            this.writer.write("Elapsed time       : " + elapsedTime + "\n");
            this.writer.write("Data               : " + new Date().toString() + "\n");
            this.writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void logRatingList(List<Player> ratingList) {
        try {
            String fmt = "%4s%30s%5s%5s%5s%5s";
            String header = String.format(fmt, "#", "Giocatore", "Punti", "+", "=", "-");
            this.writer.write(header + "\n");
            this.writer.write("\n");

            for (int i=0; i<ratingList.size(); ++i) {
                Player p = ratingList.get(i);
                String row = String.format(fmt, "" + (i+1) + ".", p.name(), p.eloRating(), p.wins, p.draws, p.losses);
                this.writer.write(row + "\n");
            }
            this.writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void logTourneamentRound(int roundNumber, Game[] games) {
        try {
            this.writer.write("TURNO " + "" + roundNumber + "\n");
            this.writer.write("\n");

            for (Game g: games) {
                logTourneamentGameResult(g);
            }
            this.writer.write("\n");
            for (Game g: games) {
                logTourneamentGameScoresheet(g);
            }
            this.writer.write("\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void logTourneamentGameResult(Game g) throws IOException {
        String result = switch (g.status()) {
            case X_WON -> "1-0";
            case O_WON -> "0-1";
            case DRAW -> "1/2-1/2";
            default -> "...";
        };
        this.writer.write(    g.player1().name() + " (" + g.player1().eloRating() + ")"
                + " - "
                + g.player2().name() + " (" + g.player2().eloRating() +  ") : "
                + result + "\n");
    }

    private void logTourneamentGameScoresheet(Game g) throws IOException {
        this.writer.write(    g.player1().name() + " (" + g.player1().eloRating() + ")"
                + " - "
                + g.player2().name() + " (" + g.player2().eloRating() +  ")" + "\n");

        String result = switch (g.status()) {
            case X_WON -> "1-0";
            case O_WON -> "0-1";
            case DRAW -> "1/2-1/2";
            default -> "...";
        };

        this.writer.write(    g.scoresheet() + result + "\n");
    }
}
