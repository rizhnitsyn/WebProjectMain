package DTO;

import entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserDto {
    private Long id;
    private String firstName;
    private String secondName;
    private String email;
    private int userState;

    public UserDto(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.secondName = user.getSecondName();
        this.email = user.getEmail();
        this.userState = user.getUserState();
    }
}
