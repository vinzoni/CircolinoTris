import java.util.ArrayList;
import java.util.List;

public class Board  implements Cloneable {
    public static final String NULL_MOVE = "99";
    public enum Status { RUNNING, DRAW, X_WON, O_WON }
    public static enum PlayerToMove { FIRST_PLAYER, SECOND_PLAYER }
    private Status status = Status.RUNNING;

    private String[][] squares = new String[3][3];
    private String[] winningTriplet = new String[3];
    private int moveCount = 0;

    public void evaluate() {
        String[] triplets = new String[8];
        triplets[0] = squares[0][0] + squares[0][1] + squares[0][2];
        triplets[1] = squares[1][0] + squares[1][1] + squares[1][2];
        triplets[2] = squares[2][0] + squares[2][1] + squares[2][2];

        triplets[3] = squares[0][0] + squares[1][0] + squares[2][0];
        triplets[4] = squares[0][1] + squares[1][1] + squares[2][1];
        triplets[5] = squares[0][2] + squares[1][2] + squares[2][2];

        triplets[6] = squares[0][0] + squares[1][1] + squares[2][2];
        triplets[7] = squares[0][2] + squares[1][1] + squares[2][0];

        String[][] lines = {
            { "00", "01", "02"},
            { "10", "11", "12"},
            { "20", "21", "22"},
            { "00", "10", "20"},
            { "01", "11", "21"},
            { "02", "12", "22"},
            { "00", "11", "22"},
            { "02", "11", "20"}
        };

        for (int i=0; i<8; ++i) {
            if (triplets[i].equals("XXX")) {
                status = Status.X_WON;
                winningTriplet = lines[i];
                return;
            }
            if (triplets[i].equals("000")) {
                status = Status.O_WON;
                winningTriplet = lines[i];
                return;
            }
        }

        if (moveCount == 9)
            status = Status.DRAW;
        else
            status = Status.RUNNING;
    }

    public Status status() { return status; }

    public void update(String move) {
        moveCount ++;
        if (move.equals(NULL_MOVE)) return;

        int xCoord = move.charAt(0) - '0';
        int yCoord = move.charAt(1) - '0';
        squares[xCoord][yCoord] = moveCount % 2 == 0 ? "0" : "X";
        evaluate();
    }

    public List<String> freeSquares() {
        ArrayList<String> freeSquares = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (squares[i][j] == null)
                    freeSquares.add(i + "" + j);
            }
        }
        return freeSquares;
    }

    public int moveCount() { return moveCount; }

//    public Player playerToMove(Player p1, Player p2) {
//        return moveCount() % 2 == 0 ? p1 : p2;
//    }
    public PlayerToMove playerToMove() {
        return moveCount() % 2 == 0 ? PlayerToMove.FIRST_PLAYER : PlayerToMove.SECOND_PLAYER;
    }

    public boolean winningStatus() { return status() == Board.Status.X_WON || status() == Status.O_WON; }

    public boolean winningSquare(String square) {
        return square.equals(winningTriplet[0]) ||
                square.equals(winningTriplet[1]) ||
                square.equals(winningTriplet[2]);
    }

    public Board clone() throws CloneNotSupportedException {
        Board clonedBoard = (Board) super.clone();

        clonedBoard.squares = new String[3][3];

        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                clonedBoard.squares[i][j] = this.squares[i][j];

        clonedBoard.moveCount = this.moveCount;
        return clonedBoard;
    }

    public void display() {
        System.out.println();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++)
                if (this.squares[i][j] == null)
                    System.out.print(" .");
                else
                    System.out.print(" " + this.squares[i][j]);
            System.out.println();
        }
    }
}
