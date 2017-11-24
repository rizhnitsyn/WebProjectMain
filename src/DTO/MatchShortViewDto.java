package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class MatchShortViewDto {
    private Long id;
    private Date matchDateTime;
    private String firstTeam;
    private String secondTeam;
    //int state
//    Integer firstteamrResult
//    Integer secondTeamResult
}
