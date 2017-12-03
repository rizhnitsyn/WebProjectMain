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
    private String matchDateTime;
    private int matchType;
    private Long firstTeamId;
    private Long secondTeamId;
    private Long tournamentId;
}
