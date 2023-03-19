package pl.drogaprogramisty.ratings.dto;

import pl.drogaprogramisty.games.dto.PlayerGameResultDto;

public record PlayerGameResult(String playerName,
                               int score
) {
    public static PlayerGameResult of(PlayerGameResultDto r) {
        return new PlayerGameResult(r.playerName(), r.score());
    }
}
