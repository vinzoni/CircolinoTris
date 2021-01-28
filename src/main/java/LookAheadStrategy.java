import java.util.List;
import java.util.Random;

public class LookAheadStrategy implements Strategy {

    public String findBestMove(Board board) {
        List<String> candidateMoves = board.freeSquares();

        try {
            for (String move: candidateMoves) {
                if (thisMoveWinsInstantly(board, move))
                    return move;
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return playARandomMove(candidateMoves);
    }

    private String playARandomMove(List<String> candidateMoves) {
        Random rand = new Random();
        return candidateMoves.get(rand.nextInt(candidateMoves.size()));
    }

    private boolean thisMoveWinsInstantly(Board board, String move) throws CloneNotSupportedException {
        Board b = board.clone();
        b.update(move);
        return b.winningStatus();
    }

}
