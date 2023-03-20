package pl.drogaprogramisty.games;

import lombok.RequiredArgsConstructor;
import pl.drogaprogramisty.games.dto.GameResultDto;
import pl.drogaprogramisty.games.dto.RatingDto;
import pl.drogaprogramisty.ratings.RatingFacade;
import pl.drogaprogramisty.ratings.dto.GameResult;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GamesFacade {

    private final RatingFacade ratings;
    private final GamesRepository repository;

    public void addGame(GameResultDto dto) {
        if (dto.playerResults().size() < 2) {
            throw new RuntimeException("Game should contain at least two players");
        }
        dto.playerResults().forEach(pr -> {
            if (pr.playerName() == null || pr.playerName().isEmpty() || pr.score() <= 0) {
                throw new RuntimeException("Invalid players data");
            }
        });
        if (dto.date().isAfter(LocalDate.now())) {
            throw new RuntimeException("Date cannot be in the future");
        }

        repository.save(GameResult.of(dto));
    }

    public List<RatingDto> getRatings() {
        List<GameResult> results = repository.findAll();

        Map<String, Double> ratings = this.ratings.calculateRatings(results);
        return ratings.entrySet().stream()
                .map(e -> new RatingDto(e.getKey(), Math.round(e.getValue())))
                .toList();
    }

    public List<GameResult> getGames() {
        List<GameResult> games = repository.findAll();
        return games.stream()
                .sorted(Comparator.comparing(GameResult::date))
                .toList();
    }
}
