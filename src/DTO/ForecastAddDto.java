package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ForecastAddDto {
    int firstTemResult;
    int secondTeamResult;
    Long userId;
    Long matchId;
}
