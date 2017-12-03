package DTO;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MatchShortViewDto {
    private Long id;
    private LocalDateTime matchDateTime;
    private String firstTeam;
    private String secondTeam;
    private Integer firstTeamResult;
    private Integer secondTeamResult;
    private Long tournamentId;
    private boolean error;
    private String message;
    private String redirectPath;

    public MatchShortViewDto(Long id, LocalDateTime matchDateTime, String firstTeam, String secondTeam, Long tournamentId) {
        this.id = id;
        this.matchDateTime = matchDateTime;
        this.firstTeam = firstTeam;
        this.secondTeam = secondTeam;
        this.tournamentId = tournamentId;
    }

    public MatchShortViewDto(Long id, LocalDateTime matchDateTime, String firstTeam, String secondTeam, Long tournamentId,
                             Integer firstTeamResult, Integer secondTeamResult) {
        this(id, matchDateTime, firstTeam, secondTeam, tournamentId);
        this.firstTeamResult = firstTeamResult;
        this.secondTeamResult = secondTeamResult;
    }

    public MatchShortViewDto(boolean error, String message) {
        this.error = error;
        this.message = message;
    }

    public MatchShortViewDto(boolean error, Long id) {
        this.id = id;
        this.error = error;
    }
}
