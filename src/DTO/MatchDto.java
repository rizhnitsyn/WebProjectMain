package DTO;

import entities.Forecast;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class MatchDto {
    private Long id;
    private Integer firstTeamResult = null;
    private Integer secondTeamResult = null;
    private Date matchDateTime;
    private int matchState;
    private int matchType;
    private int firstTeamId;
    private int secondTeamId;
    private Long tournamentId;
    private int forecastsCount;
    private Set<Forecast> forecasts;
    private Forecast currentUserForecast;
    private int firstTeamWinCount;
    private int secondTeamWinCount;
    private int drawCount;
}
