package pl.drogaprogramisty.games;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.drogaprogramisty.games.dto.GameResultDto;
import pl.drogaprogramisty.games.dto.RatingDto;

import java.util.List;

@RestController
@RequiredArgsConstructor
class GamesController {

    private final GamesFacade gamesFacade;

    @GetMapping("/games")
    public List<RatingDto> getRatings() {
        return gamesFacade.getRatings();
    }

    @PostMapping("/games")
    public void addGame(@RequestBody GameResultDto dto) {
        gamesFacade.addGame(dto);
    }
}
