package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class MatchDto {
    private Long id;
    private Integer firstTeamResult = null;
    private Integer secondTeamResult = null;
    private Date matchDateTime;
    private int matchState;
    private int matchType;
    private int firstTeamId;
    private int secondTeamId;
    private Long tournamentId;
}
