package pl.drogaprogramisty.ratings;

import pl.drogaprogramisty.multielo.MultiEloCalculator;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class RatingFacade {

    private final MultiEloCalculator eloCalculator = new MultiEloCalculator();

    public Map<String, Double> calculateRatings(List<GameResult> gamesResults) {
        Map<String, Double> playerToRating = gamesResults.stream()
                .flatMap(gameResult -> gameResult.playerResults().stream())
                .map(PlayerGameResult::playerName)
                .distinct()
                .collect(Collectors.toMap(
                        p -> p,
                        p -> 1000.0
                ));


        for (GameResult gamesResult : gamesResults) {
            List<PlayerGameResult> sortedResults = gamesResult.playerResults().stream()
                    .sorted(RatingFacade::compareByScoreThenSecondScore)
                    .toList();
            double[] ratings = sortedResults.stream()
                    .mapToDouble(result -> playerToRating.get(result.playerName()))
                    .toArray();

            ratings = eloCalculator.newRating(ratings);

            for (int i = 0; i < sortedResults.size(); i++) {
                playerToRating.put(sortedResults.get(i).playerName(), ratings[i]);
            }
        }
        return playerToRating;
    }

    private static int compareByScoreThenSecondScore(PlayerGameResult first, PlayerGameResult second) {
        if (first.score() == second.score()) {
            return second.secondScore() - first.secondScore();
        }
        return second.score() - first.score();
    }
}

