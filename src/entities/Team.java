package entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Team {
    public Team(Long id) {
        this.id = id;
    }

    private Long id;
    private String teamName;
}
