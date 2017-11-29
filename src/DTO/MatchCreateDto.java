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
public class MatchCreateDto {
    private LocalDateTime matchDateTime;
    private int matchState;
    private int matchType;
    private Long firstTeam;
    private Long secondTeam;
    private Long tournamentId;
}
