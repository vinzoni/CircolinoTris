import java.util.List;
import java.util.Random;

public class WholeMoveLookAheadStrategy implements Strategy {
    public String findBestMove(Board board) {
        List<String> candidateMoves = board.freeSquares();
        String defensiveMove = null;
        try {
            for (String move: candidateMoves) {
                if (thisMoveWinsInstantly(board, move))
                    return move;
                if (thisMovePreventsOpponentWin(board, move))
                    defensiveMove = move;
            }
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        if (defensiveMove != null) return defensiveMove;

        return playARandomMove(candidateMoves);
    }

    private boolean thisMoveWinsInstantly(Board board, String move) throws CloneNotSupportedException {
        Board b = board.clone();
        b.update(move);
        return b.winningStatus();
    }

    private String playARandomMove(List<String> candidateMoves) {
        Random rand = new Random();
        return candidateMoves.get(rand.nextInt(candidateMoves.size()));
    }

    private boolean thisMovePreventsOpponentWin(Board board, String move) throws CloneNotSupportedException {
        Board b = board.clone();
        b.update(Board.NULL_MOVE);

        return thisMoveWinsInstantly(b, move);
    }

}
