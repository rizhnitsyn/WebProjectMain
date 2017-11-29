package DTO;

import entities.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class TournamentCreateUpdateDto {
    private String name;
    private Long organizerId;
    private LocalDate startDate;
    private int stateId;

    public TournamentCreateUpdateDto(Tournament tournament) {
        this.name = tournament.getName();
        this.organizerId = tournament.getOrganizer().getId();
        this.startDate = tournament.getStartDate();
        this.stateId = tournament.getStateId();
    }
}
