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
    private User user;
    private Match match;

    public Forecast(Long id, int firstTeamForecast, int secondTeamForecast, Match match) {
        this.id = id;
        this.firstTeamForecast = firstTeamForecast;
        this.secondTeamForecast = secondTeamForecast;
        this.match = match;
    }

    public Forecast(Long id, int firstTeamForecast, int secondTeamForecast, User user) {
        this.id = id;
        this.firstTeamForecast = firstTeamForecast;
        this.secondTeamForecast = secondTeamForecast;
        this.user = user;
    }
}
