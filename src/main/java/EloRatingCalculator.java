
public class EloRatingCalculator {
    static public int calcolaVariazionePrimoGiocatore(int eloPlayer1, int eloPlayer2, Player.Score score) {
        double r1 = Math.pow(10, (double) eloPlayer1 / 400);
        double r2 = Math.pow(10, (double) eloPlayer2 / 400);

        double expectedScorePlayer1 = r1 / (r1 + r2);
        int k = 30;

        int variazione = switch (score) {
            case WIN -> (int) (k * (1 - expectedScorePlayer1));
            case DRAW -> (int) (k * (0.5 - expectedScorePlayer1));
            case LOSE -> (int) (k * (0 - expectedScorePlayer1));
        };
        return variazione;
    }
}
