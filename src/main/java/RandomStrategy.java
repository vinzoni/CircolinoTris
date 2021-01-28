import java.util.List;
import java.util.Random;

public class RandomStrategy implements Strategy {

    public String findBestMove(Board board) {
        List<String> candidateMoves = board.freeSquares();
        return playARandomMove(candidateMoves);
    }

    private String playARandomMove(List<String> candidateMoves) {
        Random rand = new Random();
        return candidateMoves.get(rand.nextInt(candidateMoves.size()));
    }

}
