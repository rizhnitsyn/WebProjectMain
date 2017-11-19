package DTO;

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
}
