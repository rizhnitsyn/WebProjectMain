package entities;

import DTO.UserCreateDto;
import DTO.UserViewDto;
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
    private String login;
    private String password;
    private Set<Tournament> tournaments = new HashSet<>();
    private Set<Forecast> forecasts = new HashSet<>();

    public User(Long id, String password) {
        this.id = id;
        this.password = password;
    }

    public User(Long id) {
        this.id = id;
    }

    public User(String firstName, String secondName, String email) {
        this.firstName = firstName;
        this.secondName = secondName;
        this.email = email;
    }

    public User(UserCreateDto dto) {
        this(dto.getFirstName(), dto.getSecondName(), dto.getEmail());
        this.login = dto.getLogin();
        this.password = dto.getPassword();
    }

    public User(Long id, String firstName, String secondName, String email, int userState) {
        this(firstName, secondName, email);
        this.id = id;
        this.userState = userState;
    }

    public User(UserViewDto dto, int userState) {
        this(dto.getId(), dto.getFirstName(), dto.getSecondName(), dto.getEmail(), userState);
    }

    public void addTournament(Tournament tournament) {
        tournaments.add(tournament);
    }

    public void addForecast(Forecast forecast) {
        forecasts.add(forecast);
    }
}
