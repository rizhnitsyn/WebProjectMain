package DTO;

import entities.Forecast;
import entities.Match;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
public class MatchViewDto {
    private Long id;
    private Integer firstTeamResult;
    private Integer secondTeamResult;
    private LocalDateTime matchDateTime;
    private String matchState;
    private String matchType;
    private String firstTeam;
    private String secondTeam;
    private String tournamentName;
    private int forecastsCount;
    private Forecast currentUserForecast;
    private int firstTeamWinCount;
    private int secondTeamWinCount;
    private int drawCount;
    private int guessedWinnersCount;
    private int guessedDiffInResultsCount;
    private int guessedResultsCount;
    private Integer currentUserPoints;

    public MatchViewDto(Match match) {
        this.id = match.getId();
        this.firstTeamResult = match.getFirstTeamResult();
        this.secondTeamResult = match.getSecondTeamResult();
        this.matchDateTime = match.getMatchDateTime();
        this.firstTeam = match.getFirstTeam().getTeamName();
        this.secondTeam = match.getSecondTeam().getTeamName();
        this.tournamentName = match.getTournament().getName();
    }
}
