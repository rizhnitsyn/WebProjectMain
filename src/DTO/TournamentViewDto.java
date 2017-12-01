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
    private String message;
    private String redirect;

    public TournamentViewDto(String message) {
        this.message = message;
    }

    public TournamentViewDto(Long id, String name, String teamName, LocalDate startDate, String state) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.startDate = startDate;
        this.state = state;
    }

    public TournamentViewDto(Long id, String name, String teamName, LocalDate startDate, String state, String redirect) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.startDate = startDate;
        this.state = state;
        this.redirect = redirect;
    }

    public TournamentViewDto(Long id, String name, String teamName, LocalDate startDate, String state, Long userId) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.startDate = startDate;
        this.state = state;
        this.userId = userId;
    }

}
