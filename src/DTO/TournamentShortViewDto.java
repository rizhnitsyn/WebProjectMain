package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class TournamentShortViewDto {
    private Long id;
    private String name;
    private int stateId;
}
