package pl.drogaprogramisty.multielo;

import java.util.Arrays;

import static pl.drogaprogramisty.utils.MathUtils.minimum;
import static pl.drogaprogramisty.utils.MathUtils.sum;

class ByPointsScoreFunction implements ScoreFunction {

    // TODO: 12/02/2023 jak rozstrzygnąc remisy?
    @Override
    public double[] scores(int[] points) {
        int sum = sum(points);
        int min = minimum(points);

        return Arrays.stream(points)
                .mapToDouble(p -> 1.0 * (p - min) / (sum - points.length * min))
                .toArray();
    }

}