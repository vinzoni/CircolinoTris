import java.util.List;

public interface PairingSystem {
    class PlayersPair {
        public Player player1;
        public Player player2;

        public PlayersPair(Player p1, Player p2) {
            this.player1 = p1;
            this.player2 = p2;
        }
    }

    List<PlayersPair> pairings(int roundNumber);
}
