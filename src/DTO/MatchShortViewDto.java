package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class MatchShortViewDto {
    private Long id;
    private LocalDateTime matchDateTime;
    private String firstTeam;
    private String secondTeam;
    private Long tournamentId;
}
