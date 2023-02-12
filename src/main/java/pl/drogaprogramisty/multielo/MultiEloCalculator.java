package pl.drogaprogramisty.multielo;

public class MultiEloCalculator {

    private static final double MAX_RATING_JUMP = 32.0;
    private static final double ELO_TO_WIN_WEIGHT = 400.0;
    private static final double ALPHA = 2;

    // first rating should be of a winner player. Then next, then next. last index of max loser
    public double[] newRating(double[] ratingsInWinOrder) {
        double[] newRatings = new double[ratingsInWinOrder.length];

        for (int i = 0; i < ratingsInWinOrder.length; i++) {
            newRatings[i] = newRating(i + 1, ratingsInWinOrder);
        }

        return newRatings;
    }

    private static double newRating(int place, double[] playerRatings) {
        double oldRating = playerRatings[place - 1];
        int playerCount = playerRatings.length;
        double finalOutcomeMultiplier = finalOutcomeWeight(place, playerRatings);

        return oldRating + MAX_RATING_JUMP * (playerCount - 1) * finalOutcomeMultiplier;
    }

    private static double finalOutcomeWeight(int place, double[] playerRatings) {
        double scoreForPlace = scoreForPlace(place, playerRatings.length);
        double expectedOutcome = expectedOutcome(place - 1, playerRatings);

        return scoreForPlace - expectedOutcome;
    }

    private static double calculateSingularExpectedOutcome(double consideredPlayerRating, double otherPlayerRating) {
        return 1.0 / (1.0 + Math.pow(10, (otherPlayerRating - consideredPlayerRating) / ELO_TO_WIN_WEIGHT));
    }

    private static double expectedOutcome(int playerIndex, double... playerRatings) {
        double expectedOutcomeSum = 0;
        double playerRating = playerRatings[playerIndex];
        int players = playerRatings.length;

        for (int i = 0; i < players; i++) {
            if (playerIndex != i) {
                expectedOutcomeSum += calculateSingularExpectedOutcome(playerRating, playerRatings[i]);
            }
        }
        return expectedOutcomeSum / (players * (players - 1) / 2.0);
    }

    private static double scoreForPlace(int place, int players) {
        double sum = 0;
        for (int i = 1; i < players; i++) {
            sum += Math.pow(ALPHA, players - i) - 1;
        }
        return (Math.pow(ALPHA, players - place) - 1) / sum;
    }
}
