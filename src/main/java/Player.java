import javax.swing.*;

public abstract class Player {
    public enum Score { DRAW, WIN, LOSE }

    private final String name;
    int wins = 0;
    int draws = 0;
    int losses = 0;
    int eloRating = 2000;

    protected Player(String name) {
        this.name = name;
    }

    abstract String playAMove(Board board) throws InterruptedException;
    abstract boolean waitingForAMove();
    abstract void acceptMove(JButton button);
    public String name() { return name; }
    public void setScore(Score score) {
        switch (score) {
            case WIN -> wins++;
            case DRAW -> draws++;
            case LOSE -> losses++;
        }
    }

    public int eloRating() { return eloRating; }

    public void updateEloRating(int variazione) {
        eloRating += variazione;
    }
}
