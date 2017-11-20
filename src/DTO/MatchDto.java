package DTO;

import entities.Forecast;
import entities.Match;
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
    private int guessedWinnersCount;
    private int guessedDiffInResultsCount;
    private int guessedResultsCount;
    private Integer currentUserPoints;

    public MatchDto(Match match) {
        this.id = match.getId();
        this.firstTeamResult = match.getFirstTeamResult();
        this.secondTeamResult = match.getSecondTeamResult();
        this.matchDateTime = match.getMatchDateTime();
        this.matchState = match.getMatchState();
        this.matchType = match.getMatchType();
        this.firstTeamId = match.getFirstTeamId();
        this.secondTeamId = match.getSecondTeamId();
        this.tournamentId = match.getTournament().getId();
    }
}
