package entities;

import DTO.UserDto;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"tournaments", "forecasts"})
public class User {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private int userState;
    private Set<Tournament> tournaments = new HashSet<>();
    private Set<Forecast> forecasts = new HashSet<>();


    public User(String firstName, String secondName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }


    public User(Long id, String firstName, String secondName, String email, int userState) {
        this(firstName, secondName, email);
        this.id = id;
        this.userState = userState;
    }

    public User(UserDto userDto) {
        this(userDto.getId(), userDto.getFirstName(), userDto.getSecondName(), userDto.getEmail(), userDto.getUserState());
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }
}
