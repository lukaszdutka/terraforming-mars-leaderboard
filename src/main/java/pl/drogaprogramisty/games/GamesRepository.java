package pl.drogaprogramisty.games;

import org.springframework.data.mongodb.repository.MongoRepository;
import pl.drogaprogramisty.ratings.dto.GameResult;

public interface GamesRepository extends MongoRepository<GameResult, String> {
}
