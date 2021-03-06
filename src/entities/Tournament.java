package entities;

import DTO.TournamentCreateUpdateDto;
import lombok.*;
import utils.StaticContent;

import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

import static utils.StaticContent.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"matches", "users", "stateId", "organizer", "startDate"})
public class Tournament {
    private Long id;
    private String name;
    private Team organizer;
    private LocalDate startDate;
    private int stateId;
    private Set<Match> matches = new HashSet<>();
    private Set<User> users = new HashSet<>();

    public Tournament(Long id) {
        this.id = id;
    }

    public Tournament(Long id, String name, int stateId) {
        this.id = id;
        this.name = name;
        this.stateId =stateId;
    }

    public Tournament(Long id, String name, LocalDate startDate, int stateId) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.stateId = stateId;
    }

    public Tournament(String name, Team organizer, LocalDate startDate, int stateId) {
        this.name = name;
        this.organizer = organizer;
        this.startDate = startDate;
        this.stateId = stateId;
    }

    public Tournament(Long id, String name, Team organizer, LocalDate startDate, int stateId) {
        this(name, organizer, startDate, stateId);
        this.id = id;
    }

    public void addFootballMatch(Match match) {
        matches.add(match);
    }

    public void addUser(User user) {
        users.add(user);
    }

}
