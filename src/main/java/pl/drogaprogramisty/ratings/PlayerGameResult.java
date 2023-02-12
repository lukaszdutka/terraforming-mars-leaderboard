package pl.drogaprogramisty.ratings;

public record PlayerGameResult(String playerName,
                               int score,
                               int secondScore //needed if two players have same score
) {
    public PlayerGameResult(String playerName, int score) {
        this(playerName, score, 0);
    }
}
