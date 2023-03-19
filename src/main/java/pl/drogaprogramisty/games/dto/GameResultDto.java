package pl.drogaprogramisty.games.dto;

import java.time.LocalDate;
import java.util.List;

public record GameResultDto(List<PlayerGameResultDto> playerResults,
                            LocalDate date) {
}
