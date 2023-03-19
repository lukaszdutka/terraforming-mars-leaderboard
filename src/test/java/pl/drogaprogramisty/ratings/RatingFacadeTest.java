package pl.drogaprogramisty.ratings;

import org.junit.jupiter.api.Test;
import pl.drogaprogramisty.ratings.dto.GameResult;
import pl.drogaprogramisty.ratings.dto.PlayerGameResult;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

class RatingFacadeTest {

    public static final RatingFacade ratingFacade = new RatingFacade();

    @Test
    void name() {
        GameResult game1 = new GameResult(
                List.of(
                        new PlayerGameResult("Łukasz", 70),
                        new PlayerGameResult("Wera", 62),
                        new PlayerGameResult("Kasia", 59),
                        new PlayerGameResult("Paweł", 54),
                        new PlayerGameResult("Marta", 51)
                ),
                LocalDate.of(2023, 2, 11)
        );
        GameResult game2 = new GameResult(
                List.of(
                        new PlayerGameResult("Łukasz", 98),
                        new PlayerGameResult("Wera", 81),
                        new PlayerGameResult("Natalia", 81)
                ),
                LocalDate.of(2023, 3, 19)
        );

        Map<String, Double> ratings = ratingFacade.calculateRatings(List.of(
                game1, game2
        ));

        RatingsPrinter.print(ratings);
        assertThat(ratings.get("Łukasz")).isGreaterThan(1000);
        assertThat(ratings.get("Marta")).isLessThan(1000);
    }
}