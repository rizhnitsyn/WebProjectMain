package entities;

import lombok.*;

import java.sql.Date;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tournament", "matchState", "matchType", "firstTeamResult", "secondTeamResult", "forecasts"})
public class Match {
    private Long id;
    private Integer firstTeamResult;
    private Integer secondTeamResult;
    private LocalDateTime matchDateTime;
    private int matchState;
    private int matchType;
    private Team firstTeam;
    private Team secondTeam;
    private Tournament tournament;
    private Set<Forecast> forecasts  = new HashSet<>();

    public Match(Long id) {
        this.id = id;
    }

    public Match(Long id, Integer firstTeamResult, Integer secondTeamResult) {
        this(id);
        this.firstTeamResult = firstTeamResult;
        this.secondTeamResult = secondTeamResult;
    }

    public Match(Long id, LocalDateTime matchDateTime, int matchState, int matchType) {
        this.id = id;
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;
        this.matchType = matchType;
    }

    public Match(LocalDateTime matchDateTime, int matchState, int matchType, Team firstTeam, Team secondTeam, Tournament tournament) {
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;
        this.matchType = matchType;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.tournament = tournament;
    }

    public Match(Long id, Integer firstTeamResult, Integer secondTeamResult, LocalDateTime matchDateTime, int matchState, int matchType, Team firstTeam, Team secondTeam) {
        this.id = id;
        this.firstTeamResult = firstTeamResult;
        this.secondTeamResult = secondTeamResult;
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;
        this.matchType = matchType;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
    }

    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }
}
