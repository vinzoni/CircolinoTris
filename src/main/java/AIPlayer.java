import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class AIPlayer extends Player {

    private final Strategy strategy;

    public AIPlayer(String name, Strategy strategy) {
        super(name);
        this.strategy = strategy;
    }

    public String playAMove(Board board) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(Configuration.MIN_MILLIS_INTERVAL_BEETWEEN_AI_MOVES);

        return strategy.findBestMove(board);
    }

    public boolean waitingForAMove() { return false; }
    public void acceptMove(JButton button) { }
}
