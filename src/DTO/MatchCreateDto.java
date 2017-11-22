package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;


@Getter
@Setter
@AllArgsConstructor
public class MatchCreateDto {
    private Date matchDateTime;
    private int matchState;
    private int matchType;
    private Long firstTeam;
    private Long secondTeam;
    private Long tournamentId;
}
