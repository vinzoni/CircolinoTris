public class TourneamentConfiguration8Players implements TourneamentConfiguration {

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
                new AIPlayer("Roberta", new RandomStrategy()),
                new AIPlayer("Rodolfo", new RandomStrategy()),
                new AIPlayer("Corinna", new CenterStrategy()),
                new AIPlayer("Carmelo", new CenterStrategy()),
                new AIPlayer("Carla", new CenterStrategy()),
                new AIPlayer("Corrado", new CenterStrategy()),
        };
        return players;
    }

    @Override
    public PairingSystem pairingSystem() {
        return new PairingSystemRandom();
    }
}
