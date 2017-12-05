package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TournamentViewDto {
    private Long id;
    private String name;
    private String teamName;
    private LocalDate startDate;
    private String state;
    private Long userId;
    private boolean error;
    private String message;
    private String redirectPath;
    private int stateId;
    private int matchesCount;
    private String strStartDate;

    public TournamentViewDto(boolean error, String message) {
        this.message = message;
        this.error = error;
    }

    public TournamentViewDto(Long id, String name, String teamName, LocalDate startDate, String state) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.startDate = startDate;
        this.state = state;
    }

    public TournamentViewDto(Long id, String name, String teamName, LocalDate startDate, String state, int stateId, String strStartDate ) {
        this(id, name, teamName, startDate, state);
        this.stateId = stateId;
        this.strStartDate = strStartDate;
    }

    public TournamentViewDto(Long id, String name, String teamName, LocalDate startDate, String state, int stateId, int matchesCount, String strStartDate) {
        this(id, name, teamName, startDate, state, stateId, strStartDate);
        this.matchesCount = matchesCount;
    }

    public TournamentViewDto(boolean error, Long id, String name, String teamName, LocalDate startDate, String state) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.startDate = startDate;
        this.state = state;
        this.error = error;
    }

    public TournamentViewDto(Long id, String name, String teamName, LocalDate startDate, String state, Long userId, String strStartDate) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.startDate = startDate;
        this.state = state;
        this.userId = userId;
        this.strStartDate = strStartDate;
    }


}
