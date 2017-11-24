package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class TournamentViewDto {
    private Long id;
    private String name;
    private String teamName;
    private Date startDate;
    private String state;
    private Long userId;

    public TournamentViewDto(Long id, String name, String teamName, Date startDate, String state) {
        this.id = id;
        this.name = name;
        this.teamName = teamName;
        this.startDate = startDate;
        this.state = state;
    }
}
