import java.util.List;
import java.util.Random;

public class CenterAndLookAheadStrategy implements Strategy {
    public String findBestMove(Board board) {
        List<String> candidateMoves = board.freeSquares();

        String cmove = tryMoveInTheCenter(candidateMoves);
        if (cmove != null) return cmove;

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

    private String tryMoveInTheCenter(List<String> candidateMoves) {
        return candidateMoves.contains("11") ? "11" : null;
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
