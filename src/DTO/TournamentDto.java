package DTO;

import entities.Tournament;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class TournamentDto {
    private Long id;
    private String name;
    private int organizerId;
    private Date startDate;
    private int stateId;

    public TournamentDto(Tournament tournament) {
        this.id = tournament.getId();
        this.name = tournament.getName();
        this.organizerId = tournament.getOrganizerId();
        this.startDate = tournament.getStartDate();
        this.stateId = tournament.getStateId();
    }
}
