package pl.drogaprogramisty.ratings;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;

class RatingFacadeTest {

    public static final RatingFacade ratingFacade = new RatingFacade();

    @Test
    void name() {
        GameResult lukaszWon = new GameResult(
                List.of(
                        new PlayerGameResult("Łukasz", 70),
                        new PlayerGameResult("Wera", 62),
                        new PlayerGameResult("Kasia", 59),
                        new PlayerGameResult("Paweł", 54),
                        new PlayerGameResult("Marta", 51)
                )
        );

        Map<String, Double> ratings = ratingFacade.calculateRatings(List.of(
                lukaszWon
        ));

        RatingsPrinter.print(ratings);
    }
}