public class Game implements Runnable{

    private GameUI gameUI = null;
    private final int gameNumber;
    private final Board board = new Board();
    private final Player player1;
    private final Player player2;
    private String scoresheet = "";

    public Game(Player player1, Player player2, int gameNumber) {
        this.player1 = player1;
        this.player2 = player2;
        this.gameNumber = gameNumber;
    }

    public void setGameUI (GameUI gameUI) {
        this.gameUI = gameUI;
    }

    @Override
    public void run() {
        while (board.status() == Board.Status.RUNNING) {
//            Player playerToMove = board.playerToMove(player1, player2);
            Player playerToMove = board.playerToMove() == Board.PlayerToMove.FIRST_PLAYER ? player1 : player2;
            try {
                String move = playerToMove.playAMove(board);
                board.update(move);
                updateScoresheet(playerToMove, board.moveCount()/2 + 1, move);
                gameUI.redraw(move);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        updatePlayersRating();
    }

    private void updateScoresheet(Player playerToMove, int moveCount, String move) {
        if (playerToMove.equals(player1))
            scoresheet += "" + moveCount + ".";
        scoresheet += move + " ";
    }

    private void updatePlayersRating() {
        int variazioneElo;
        switch (status()) {
            case DRAW -> {
                player1.setScore(Player.Score.DRAW);
                player2.setScore(Player.Score.DRAW);
                variazioneElo = EloRatingCalculator.calcolaVariazionePrimoGiocatore(player1.eloRating(), player2.eloRating(), Player.Score.DRAW);
            }
            case X_WON -> {
                player1.setScore(Player.Score.WIN);
                player2.setScore(Player.Score.LOSE);
                variazioneElo = EloRatingCalculator.calcolaVariazionePrimoGiocatore(player1.eloRating(), player2.eloRating(), Player.Score.WIN);
            }
            case O_WON -> {
                player1.setScore(Player.Score.LOSE);
                player2.setScore(Player.Score.WIN);
                variazioneElo = EloRatingCalculator.calcolaVariazionePrimoGiocatore(player1.eloRating(), player2.eloRating(), Player.Score.LOSE);
            }
            default -> throw new RuntimeException("Impossibile aggiornare score. Partita non terminata?");
        }
        player1.updateEloRating(variazioneElo);
        player2.updateEloRating(-variazioneElo);
    }


    //    public int moveCount() { return board.moveCount(); }
    public Board board() { return board; }
    public Board.Status status() { return board.status(); }
    public Player player1() { return player1; }
    public Player player2() { return player2; }
    public int gameNumber() { return gameNumber; }
    public String scoresheet() { return scoresheet; }
}
