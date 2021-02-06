public interface TourneamentConfiguration {
    String name();

    int rounds();

    Player[] players();

    PairingSystem pairingSystem();
}
