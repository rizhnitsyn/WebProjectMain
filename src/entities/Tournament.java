package entities;

import lombok.*;

import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(exclude = {"matches", "users", "stateId"})
public class Tournament {
    private Long id;
    private String name;
    private int organizerId;
    private Date startDate;
    private int stateId;
    private Set<Match> matches = new HashSet<>();
    private Set<User> users = new HashSet<>();

    public Tournament(Long id) {
        this.id = id;
    }

    public Tournament(String name, int organizerId, Date startDate, int stateId) {
        this.name = name;
        this.organizerId = organizerId;
        this.startDate = startDate;

        this.stateId = stateId;
    }

    public Tournament(Long id, String name, int organizerId, Date startDate, int stateId) {
        this(name, organizerId, startDate, stateId);
        this.id = id;
    }

    public void addFootballMatch(Match match) {
        matches.add(match);
    }

    public void addUser(User user) {
        users.add(user);
    }

}
