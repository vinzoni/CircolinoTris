import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class PairingSystemSimilarRating implements PairingSystem {

    @Override
    public List<PairingSystem.PlayersPair> pairings(int roundNumber) {

        List<Player> playersSortedByElo = Arrays.asList(Configuration.players);
        playersSortedByElo.sort(Comparator.comparing(Player::eloRating).reversed());

        List<PairingSystem.PlayersPair> pairings = new ArrayList<>();
        for (int i=0; i<playersSortedByElo.size(); i+=2) {
            Player p1;
            Player p2;
            if ((roundNumber + pairings.size()) % 2  == 0) {
                p1 = playersSortedByElo.get(i);
                p2 = playersSortedByElo.get(i + 1);
            }
            else {
                p1 = playersSortedByElo.get(i + 1);
                p2 = playersSortedByElo.get(i);
            }
            pairings.add(new PairingSystem.PlayersPair(p1, p2));
        }

        return pairings;
    }
}
