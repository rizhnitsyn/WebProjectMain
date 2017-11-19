package entities;

import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tournament", "matchState", "matchType", "firstTeamResult", "secondTeamResult", "forecasts"})
public class Match {
    private Long id;
    private Integer firstTeamResult = null;
    private Integer secondTeamResult = null;
    private Date matchDateTime;
    private int matchState;
    private int matchType;
    private int firstTeamId;
    private int secondTeamId;
    private Tournament tournament;
    private Set<Forecast> forecasts  = new HashSet<>();

    public Match(Date matchDateTime, int matchState, int matchType, int firstTeamId, int secondTeamId, Tournament tournament) {
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;
        this.matchType = matchType;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
        this.tournament = tournament;
    }

    public Match(Long id, Date matchDateTime, int matchState, int matchType, int firstTeamId, int secondTeamId, Tournament tournament) {
        this(matchDateTime, matchState, matchType, firstTeamId, secondTeamId, tournament);
        this.id = id;
    }

    public Match(Long id, Integer firstTeamResult, Integer secondTeamResult, Date matchDateTime, int matchState, int matchType, int firstTeamId, int secondTeamId, Tournament tournament) {
        this(id, matchDateTime, matchState, matchType, firstTeamId, secondTeamId, tournament);
        this.firstTeamResult = firstTeamResult;
        this.secondTeamResult = secondTeamResult;
    }

    public Match(Long id, Integer firstTeamResult, Integer secondTeamResult, Date matchDateTime, int matchState, int matchType, int firstTeamId, int secondTeamId) {
        this.id = id;
        this.firstTeamResult = firstTeamResult;
        this.secondTeamResult = secondTeamResult;
        this.matchDateTime = matchDateTime;
        this.matchState = matchState;
        this.matchType = matchType;
        this.firstTeamId = firstTeamId;
        this.secondTeamId = secondTeamId;
    }







    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }
}
