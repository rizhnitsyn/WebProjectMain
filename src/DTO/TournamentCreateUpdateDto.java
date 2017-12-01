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
    private String startDate;
    private int stateId;

}
