package pl.drogaprogramisty.multielo;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MultiEloCalculatorTest {

    private final MultiEloCalculator facade = new MultiEloCalculator();

    @Test
    void newRating() {
        double[] ratings = {1000, 1000};

        double[] newRatings = facade.newRating(ratings);

        assertThat(newRatings).isEqualTo(new double[]{1016, 984});
    }
}