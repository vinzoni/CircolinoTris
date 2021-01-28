import javax.swing.*;
import java.util.concurrent.TimeUnit;

public class HumanPlayer extends Player {

    private boolean waitingForAMove = false;
    String move = null;

    public HumanPlayer(String name)
    {
        super(name);
    }

    public String playAMove(Board board) throws InterruptedException {
        waitingForAMove = true;
        while (waitingForAMove) {
            TimeUnit.MILLISECONDS.sleep(100);
        }
        waitingForAMove = false;
        return move;
    }

    public void acceptMove(JButton button) {
        move = button.getName();
        waitingForAMove = false;
    }

    public boolean waitingForAMove() { return waitingForAMove; }

}
