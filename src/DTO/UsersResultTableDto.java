package DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.sql.Date;

@Getter
@Setter
@AllArgsConstructor
public class UsersResultTableDto {
    private Long userId;
    private String firstName;
    private String secondName;
    private int totalPoints;
    private int guessedResultCount;
    private int guessedWinnersCount;
    private int guessedDiffInResultsCount;
    private int guessedDrawCount;
}
