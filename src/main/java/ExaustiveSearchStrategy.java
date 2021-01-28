import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ExaustiveSearchStrategy implements Strategy {

    private static class EvaluatedMove {

        public final int evaluation;
        public final String move;

        public EvaluatedMove(String move, int evaluation) {
            this.evaluation = evaluation;
            this.move = move;
        }
    }

    public String findBestMove(Board board) {
        try {
            Board.PlayerToMove player = board.playerToMove();
            int evaluationTreeDepth = 0;
            List<String> legalMoves = board.freeSquares();
            List<EvaluatedMove> movesEvaluated = new ArrayList<>();

            for (String move : legalMoves) {
                Board b = board.clone();
                b.update(move);
                int value = minimaxEvaluation(b, evaluationTreeDepth, player);
                movesEvaluated.add(new EvaluatedMove(move, value));
            }

            return Collections.max(movesEvaluated, Comparator.comparing(x -> x.evaluation)).move;

        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return board.freeSquares().get(0);
    }

    private int minimaxEvaluation(Board board, int evaluationTreeDepth, Board.PlayerToMove player) throws CloneNotSupportedException {

        switch (board.status()) {
            case DRAW -> { return 0; }
            case X_WON -> {
                if (player == Board.PlayerToMove.FIRST_PLAYER)
                    return 100;
                else
                    return -100;
            }
            case O_WON -> {
                if (player == Board.PlayerToMove.FIRST_PLAYER)
                    return -100;
                else
                    return 100;
            }
            case RUNNING -> {
                List<String> legalMoves = board.freeSquares();
                List<EvaluatedMove> movesEvaluated = new ArrayList<>();

                for (String move : legalMoves) {
                    Board b = board.clone();
                    b.update(move);
                    int value = minimaxEvaluation(b, evaluationTreeDepth + 1, player);
                    movesEvaluated.add(new EvaluatedMove(move, value));
                }
                if (evaluationTreeDepth % 2 == 0)
                    return Collections.min(movesEvaluated, Comparator.comparing(x -> x.evaluation)).evaluation;
                else
                    return Collections.max(movesEvaluated, Comparator.comparing(x -> x.evaluation)).evaluation;
            }
        }
        return 0; // should never get here
    }
}
