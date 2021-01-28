import java.util.*;

public class PairingSystemRandom implements PairingSystem {

    @Override
    public List<PlayersPair> pairings(int roundNumber) {

        List<Player> playersRandomlyShuffled = Arrays.asList(Configuration.players);
        Collections.shuffle(playersRandomlyShuffled);

        List<PlayersPair> pairings = new ArrayList<>();
        for (int i=0; i<playersRandomlyShuffled.size(); i+=2) {
            Player p1;
            Player p2;
            if ((roundNumber + pairings.size()) % 2  == 0) {
                p1 = playersRandomlyShuffled.get(i);
                p2 = playersRandomlyShuffled.get(i + 1);
            }
            else {
                p1 = playersRandomlyShuffled.get(i + 1);
                p2 = playersRandomlyShuffled.get(i);
            }
            pairings.add(new PlayersPair(p1, p2));
        }

        // Highest ratings players should play on first boards
        pairings.sort(Comparator.comparing(pair -> Math.max(pair.player1.eloRating, pair.player2.eloRating)));
        Collections.reverse(pairings);

        return pairings;
    }
}
