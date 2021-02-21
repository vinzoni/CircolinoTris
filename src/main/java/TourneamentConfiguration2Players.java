public class TourneamentConfiguration2Players implements TourneamentConfiguration {

    @Override
    public String name() {
        return "CAMPIONATO DEL CIRCOLINO";
    }

    @Override
    public int rounds() {
        return 4;
    }

    @Override
    public Player[] players() {
        Player[] players= {
                new AIPlayer("Rosanna", new RandomStrategy()),
                new AIPlayer("Ruggero", new RandomStrategy()),
        };
        return players;
    }

    @Override
    public PairingSystem pairingSystem() {
        return new PairingSystemRandom();
    }
}
