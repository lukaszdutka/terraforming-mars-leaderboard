package pl.drogaprogramisty.ratings.dto;

import org.springframework.data.mongodb.core.mapping.Document;
import pl.drogaprogramisty.games.dto.GameResultDto;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Document(collection = "games")
public final class GameResult {
    private final List<PlayerGameResult> playerResults;
    private final LocalDate date;

    public GameResult(List<PlayerGameResult> playerResults, LocalDate date) {
        this.playerResults = playerResults;
        this.date = date;
    }

    public static GameResult of(GameResultDto dto) {
        return new GameResult(dto.playerResults().stream().map(PlayerGameResult::of).toList(), dto.date());
    }

    public List<PlayerGameResult> playerResults() {
        return playerResults;
    }

    public LocalDate date() {
        return date;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (GameResult) obj;
        return Objects.equals(this.playerResults, that.playerResults) &&
                Objects.equals(this.date, that.date);
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerResults, date);
    }

    @Override
    public String toString() {
        return "GameResult[" +
                "playerResults=" + playerResults + ", " +
                "date=" + date + ']';
    }

}
