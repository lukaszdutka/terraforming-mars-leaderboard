package pl.drogaprogramisty.utils;

import java.util.Arrays;

public class MathUtils {
    public static int minimum(int[] ints) {
        return Arrays.stream(ints)
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    public static int sum(int[] ints) {
        return Arrays.stream(ints).sum();
    }

    public static double minimum(double[] doubles) {
        return Arrays.stream(doubles)
                .min()
                .orElse(Integer.MAX_VALUE);
    }

    public static double sum(double[] doubles) {
        return Arrays.stream(doubles).sum();
    }
}
