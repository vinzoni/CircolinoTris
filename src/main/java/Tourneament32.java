public class Tourneament32 implements TourneamentConfiguration {

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
            new AIPlayer("Ramona", new RandomStrategy()),
            new AIPlayer("Rolando", new RandomStrategy()),
            new AIPlayer("Rosita", new RandomStrategy()),
            new AIPlayer("Riccardo", new RandomStrategy()),
            new AIPlayer("Rossana", new RandomStrategy()),
            new AIPlayer("Rinaldo", new RandomStrategy()),
            new AIPlayer("Rosa", new RandomStrategy()),
            new AIPlayer("Raimondo", new RandomStrategy()),
            new AIPlayer("Romina", new RandomStrategy()),
            new AIPlayer("Ronaldo", new RandomStrategy()),
            new AIPlayer("Rebecca", new RandomStrategy()),
            new AIPlayer("Remigio", new RandomStrategy()),
            new AIPlayer("Rachele", new RandomStrategy()),
            new AIPlayer("Rino", new RandomStrategy()),
            new AIPlayer("Rita", new RandomStrategy()),
            new AIPlayer("Roy", new RandomStrategy()),
        };
        return players;
    }

    @Override
    public PairingSystem pairingSystem() {
        return new PairingSystemRandom();
    }
}
