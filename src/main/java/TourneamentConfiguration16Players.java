public class TourneamentConfiguration16Players implements TourneamentConfiguration {

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
                new AIPlayer("Lorenzo", new LookAheadStrategy()),
                new AIPlayer("Lisa", new LookAheadStrategy()),
                new AIPlayer("Ciro Leone", new CenterAndLookAheadStrategy()),
                new AIPlayer("Carmen Laura", new CenterAndLookAheadStrategy()),
                new AIPlayer("Walter", new WholeMoveLookAheadStrategy()),
                new AIPlayer("Wilma", new WholeMoveLookAheadStrategy()),
                new AIPlayer("Giovanni", new ExaustiveSearchStrategy()),
                new AIPlayer("Giulia", new ExaustiveSearchStrategy()),
        };
        return players;
    }

    @Override
    public PairingSystem pairingSystem() {
        return new PairingSystemRandom();
    }
}
