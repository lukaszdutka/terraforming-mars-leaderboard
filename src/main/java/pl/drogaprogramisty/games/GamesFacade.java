package pl.drogaprogramisty.games;

import lombok.RequiredArgsConstructor;
import pl.drogaprogramisty.games.dto.GameResultDto;
import pl.drogaprogramisty.games.dto.RatingDto;
import pl.drogaprogramisty.ratings.RatingFacade;
import pl.drogaprogramisty.ratings.dto.GameResult;

import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class GamesFacade {

    private final RatingFacade ratings;
    private final GamesRepository repository;

    public void addGame(GameResultDto dto) {
        repository.save(GameResult.of(dto));
    }

    public List<RatingDto> getRatings() {
        List<GameResult> results = repository.findAll();

        Map<String, Double> ratings = this.ratings.calculateRatings(results);
        return ratings.entrySet().stream()
                .map(e -> new RatingDto(e.getKey(), e.getValue()))
                .toList();
    }
}
