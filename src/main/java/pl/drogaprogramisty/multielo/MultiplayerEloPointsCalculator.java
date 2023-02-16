package pl.drogaprogramisty.multielo;

import java.util.stream.IntStream;

public class MultiplayerEloPointsCalculator {

    private static final double MAX_RATING_JUMP = 32.0;
    private static final double ELO_TO_WIN_WEIGHT = 400.0;
    private final ScoreFunction scoreFunction = new ByPointsScoreFunction();

    // first rating should be of a winner player. Then next, then next. last index of max loser
    public double[] calculateNewRatings(double[] ratingsInWinOrder, int[] scoresInWinOrder) {
        double[] scores = scoreFunction.scores(scoresInWinOrder);

        return IntStream.range(0, ratingsInWinOrder.length)
                .mapToDouble(place -> calculateNewRating(place, ratingsInWinOrder, scores[place]))
                .toArray();
    }

    private double calculateNewRating(int place, double[] playerRatings, double scoreForPlace) {
        double oldRating = playerRatings[place];
        int playerCount = playerRatings.length;
        double finalOutcomeMultiplier = scoreForPlace - expectedOutcome(place, playerRatings);

        return oldRating + MAX_RATING_JUMP * (playerCount - 1) * finalOutcomeMultiplier;
    }

    private double expectedOutcome(int place, double[] playerRatings) {
        double playerRating = playerRatings[place];
        int players = playerRatings.length;

        double expectedOutcomeSum = IntStream.range(0, players)
                .mapToDouble(otherPlace -> otherPlace != place ? calculatePartialExpectedOutcome(playerRating, playerRatings[otherPlace]) : 0)
                .sum();
        return expectedOutcomeSum / (players * (players - 1) / 2.0);
    }

    private double calculatePartialExpectedOutcome(double consideredPlayerRating, double otherPlayerRating) {
        return 1.0 / (1.0 + Math.pow(10, ((otherPlayerRating - consideredPlayerRating) / ELO_TO_WIN_WEIGHT)));
    }
}