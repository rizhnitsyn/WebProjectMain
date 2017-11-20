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
    private Long matchId;

    public Forecast(int firstTeamForecast, int secondTeamForecast, Long userId, Long matchId) {
        this.firstTeamForecast = firstTeamForecast;
        this.secondTeamForecast = secondTeamForecast;
        this.userId = userId;
        this.matchId = matchId;
    }
}
