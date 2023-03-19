package pl.drogaprogramisty.ratings;

import java.util.List;
import java.util.Map;

class RatingsPrinter {

    static void print(Map<String, Double> ratings) {
        List<Map.Entry<String, Double>> entries = ratings.entrySet().stream()
                .sorted((a, b) -> (int) (1000 * b.getValue() - 1000 * a.getValue()))
                .toList();

        System.out.format("%-15s| %-15s%n", "Gracz", "Ranking");
        System.out.println("-".repeat(35));
        for (final Map.Entry<String, Double> entry : entries) {
            System.out.format("%-15s| %d", entry.getKey(), Math.round(entry.getValue()));
            System.out.println();
        }
    }
}
