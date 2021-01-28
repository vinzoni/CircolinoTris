import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CenterStrategy implements Strategy{
    public String findBestMove(Board board) {
        List<String> candidateMoves = board.freeSquares();

        String move = tryMoveInTheCenter(candidateMoves);
        if (move != null) return move;

        return playARandomMove(candidateMoves);
    }

    private String tryMoveInTheCenter(List<String> candidateMoves) {
        return candidateMoves.contains("11") ? "11" : null;
    }

    private String playARandomMove(List<String> candidateMoves) {
        Random rand = new Random();
        return candidateMoves.get(rand.nextInt(candidateMoves.size()));
    }

}
