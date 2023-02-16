package pl.drogaprogramisty.multielo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MultiplayerEloPointsCalculatorTest {

    private final MultiplayerEloPointsCalculator facade = new MultiplayerEloPointsCalculator();

    @Test
    void newRating() {
        double[] ratings = {1000, 1000};
        int[] points = {50, 30};

        double[] newRatings = facade.calculateNewRatings(ratings, points);

        assertThat(newRatings).isEqualTo(new double[]{1016, 984});
    }
}