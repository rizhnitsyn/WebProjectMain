package entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class Forecast {
    private Long id;
    private int firstTeamForecast;
    private int secondTeamForecast;
    private Long userId;
    private Match match;

    public Forecast(Integer firstTeamForecast, Integer secondTeamForecast, Long userId, Match match) {
        this.firstTeamForecast = firstTeamForecast;
        this.secondTeamForecast = secondTeamForecast;
        this.userId = userId;
        this.match = match;
    }
}
