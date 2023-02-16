package pl.drogaprogramisty.multielo;

import org.assertj.core.data.Offset;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static pl.drogaprogramisty.utils.MathUtils.sum;

class ByPointsScoreFunctionTest {
    private final ScoreFunction scoreFunction = new ByPointsScoreFunction();

    @Test
    void sumOfScoreFunctionResultsIsEqualToOne() {
        int[] points = {70, 50, 30, 15};

        double[] scores = scoreFunction.scores(points);

        assertThat(sum(scores)).isEqualTo(1, Offset.offset(0.000001));
    }

    @Test
    void lastPlaceHasValueEqualToZero() {
        int[] points = {50, 30};

        double[] scores = scoreFunction.scores(points);

        assertThat(scores[1]).isEqualTo(0);
    }

    @Test
    void whenTwoPlayersFirstOneGotOne() {
        int[] points = {50, 30};

        double[] scores = scoreFunction.scores(points);

        assertThat(scores[0]).isEqualTo(1);
    }
}