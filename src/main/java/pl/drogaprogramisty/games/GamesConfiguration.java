package pl.drogaprogramisty.games;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import pl.drogaprogramisty.ratings.RatingFacade;

@Configuration
public class GamesConfiguration {
    @Bean
    public GamesFacade gamesFacade(GamesRepository repository) {
        RatingFacade ratings = new RatingFacade();
        return new GamesFacade(ratings, repository);
    }
}
