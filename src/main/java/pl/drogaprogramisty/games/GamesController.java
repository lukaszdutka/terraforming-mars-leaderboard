package pl.drogaprogramisty.games;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import pl.drogaprogramisty.games.dto.GameResultDto;
import pl.drogaprogramisty.games.dto.RatingDto;
import pl.drogaprogramisty.ratings.dto.GameResult;

import java.util.List;

@CrossOrigin(originPatterns = "*")
@RestController
@RequiredArgsConstructor
class GamesController {

    private final GamesFacade gamesFacade;

    @GetMapping("/ratings")
    public List<RatingDto> getRatings() {
        return gamesFacade.getRatings();
    }

    @PostMapping("/games")
    public void addGame(@RequestBody GameResultDto dto) {
        gamesFacade.addGame(dto);
    }

    @GetMapping("/games")
    public List<GameResultDto> getGames() {
        return gamesFacade.getGames().stream().map(GameResult::dto).toList();
    }
}
